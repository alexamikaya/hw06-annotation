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
    static Class<?> testmethods = null;
    static List<Class> classes = null;

    int fail = 0;
    int all = 0;
    int success = 0;
    static String task = null;
    static String task2 = null;

    public static void main(String[] args) throws Exception {
//Рефлекция, изучаем классы и методы отмеченные аннотациями
        allClasses("otus");

        //displayInfo();
        ClassInterface classInterface = Demo.newClass();
        task = classInterface.task(8, 5, 3);
        task2 = classInterface.task2(4, 5, 7);

    }


    public static void before(ForTestTest forTestTest, Object o,Method methods) {
        for (Class clazz : ForTestTest.classes) {

            ForTestTest.testmethods = clazz;
            for (Method method : forTestTest.testmethods.getDeclaredMethods()) {
                Annotation annotation = method.getAnnotation(Before.class);
                Before before = (Before) annotation;
                if (before != null) {

                    try {
                        method.invoke(forTestTest.testmethods.getDeclaredConstructor().newInstance(),o);

                        test(forTestTest, o, methods);
                    } catch (Throwable ex) {
                        System.out.println(ex.getCause());
                        System.out.println("Не пройден тест " + method);
                        forTestTest.fail++;
                        return;

                    }
                }
            }
        }
    }

    private static void test(ForTestTest forTestTest, Object o, Method method) {
       // for (Method method : forTestTest.testmethods.getDeclaredMethods()) {
            Annotation annotation = method.getAnnotation(Test.class);
            Test test = (Test) annotation;
            if (test != null) {

                try {
                    method.invoke(forTestTest.testmethods.getDeclaredConstructor().newInstance(), o);
                    int fail = 0;
                    after(forTestTest, fail);

                } catch (Throwable ex) {
                    System.out.println(ex.getCause());
                    int fail = 1;
                    System.out.println("Не пройден тест " + method);
                    after(forTestTest, fail);

                }
            }
       // }
    }

    public static void after(ForTestTest forTestTest, int fail) {

        for (Method method : forTestTest.testmethods.getDeclaredMethods()) {
            Annotation annotation = method.getAnnotation(After.class);
            After after = (After) annotation;
            if (after != null) {

                try {
                    method.invoke(forTestTest.testmethods.getDeclaredConstructor().newInstance());
                    if (fail == 0) {
                        forTestTest.success++;
                    } else {
                        forTestTest.fail++;
                    }
                } catch (Throwable ex) {
                    System.out.println(ex.getCause());
                    forTestTest.fail++;
                    System.out.println("Не пройден тест " + method);
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


 void info(ForTestTest forTestTest, Object o){
        for (Class clazz : ForTestTest.classes) {

            ForTestTest.testmethods = clazz;

            for (Method method : forTestTest.testmethods.getDeclaredMethods()) {
                Annotation annotation = method.getAnnotation(Test.class);
                Test test = (Test) annotation;
                if (test != null) {
                    forTestTest.all++;
                    ForTestTest.before(forTestTest,o,method);
                }
            }
        }}
    void displayInfo(ForTestTest forTestTest, Method o){

        System.out.println("Результаты тестирования метода " + o);
        System.out.println("Всего было тестов " + forTestTest.all);
        System.out.println("Успешных тестов " + forTestTest.success);
        System.out.println("Количество упавшивших тестов " + forTestTest.fail);}
}