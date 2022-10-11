package otus;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ForTestTest {
    static Class<?> testmethods=null;
    static List<Class> classes = null;

    int fail = 0;
    int all = 0;
    int success = 0;

    public static void main(String[] args) throws Exception {
//Рефлекция, изучаем классы и методы отмеченные аннотациями
    allClasses("otus");
        TestA testing = new TestA();
        testing.info();
        testing.displayInfo();
    }


    public static void before (ForTestTest forTestTest) {

        for (Method method : forTestTest.testmethods.getDeclaredMethods()){
            Annotation annotation = method.getAnnotation(Before.class);
            Before before = (Before) annotation;
            if (before!=null){
                forTestTest.all++;
                try{
                    method.invoke(forTestTest.testmethods.getDeclaredConstructor().newInstance());
                    forTestTest.success++;
                    test(forTestTest);
                } catch(Throwable ex)
                {
                    System.out.println(ex.getCause());
                    forTestTest.fail++;
                }
            }
        }
    }
    private static void test(ForTestTest forTestTest){
        for (Method method : forTestTest.testmethods.getDeclaredMethods()){
            Annotation annotation = method.getAnnotation(Test.class);
            Test test = (Test) annotation;
            if (test!=null){
                forTestTest.all++;
                try{
                    method.invoke(forTestTest.testmethods.getDeclaredConstructor().newInstance());
                    forTestTest.success++;
                } catch(Throwable ex)
                {
                    System.out.println(ex.getCause());
                    forTestTest.fail++;
                }
            }
        }
    }
    public static void after(ForTestTest forTestTest){

        for (Method method : forTestTest.testmethods.getDeclaredMethods()){
            Annotation annotation = method.getAnnotation(After.class);
            After after = (After) annotation;
            if (after!=null){
                forTestTest.all++;
                try{
                    method.invoke(forTestTest.testmethods.getDeclaredConstructor().newInstance());
                    forTestTest.success++;
                } catch(Throwable ex)
                {
                    System.out.println(ex.getCause());
                    forTestTest.fail++;
                }
            }
        }
    }

    //метод ищет все классы в переданном пакете
    private static Iterable<Class> allClasses(String packageName) throws ClassNotFoundException, IOException, URISyntaxException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> pathClass = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            URI uri = new URI(resource.toString());
            pathClass.add(new File(uri.getPath()));//записываем пути ко всем классам
        }
        classes = new ArrayList<Class>();
        for (File directory : pathClass) {
            classes.addAll(findClasses(directory, packageName));//обращаемся к методу поиска классов и записываем названия всех классов в данной папке
        }

        return classes;
    }

    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

}
class TestA{
    ForTestTest forTestTest = new ForTestTest();
    void info(){
        for (Class clazz : ForTestTest.classes) {

            ForTestTest.testmethods = clazz;

            for (Method method : forTestTest.testmethods.getDeclaredMethods()) {
                Annotation annotation = method.getAnnotation(Test.class);
                Test test = (Test) annotation;
                if (test != null) {

                    ForTestTest.before(forTestTest);
                    ForTestTest.after(forTestTest);

                }
            }
        }}
    void displayInfo(){
        System.out.println("Результаты тестирования ");
        System.out.println("Всего было тестов " + forTestTest.all);
        System.out.println("Успешных тестов " + forTestTest.success);
        System.out.println("Количество упавшивших тестов " + forTestTest.fail);}
}