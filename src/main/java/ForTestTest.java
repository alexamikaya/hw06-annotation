import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ForTestTest {
    Class<TestMethods> testmethods=TestMethods.class;
    int fail = 0;
    int all = 0;
    int success = 0;
    public static void main(String[] args) throws Exception {
//Рефлекция, изучаем класс и его методы отмеченные аннотациями

        ForTestTest forTestTest = new ForTestTest();
        before(forTestTest);
        test(forTestTest);
        after(forTestTest);
        System.out.println("Результаты тестирования ");
        System.out.println("Всего было тестов "+forTestTest.all);
        System.out.println("Успешных тестов "+forTestTest.success);
        System.out.println("Количество упавшивших тестов "+forTestTest.fail);


    }
private static void before (ForTestTest forTestTest) {
    for (Method method : forTestTest.testmethods.getDeclaredMethods()){
        Annotation annotation = method.getAnnotation(Before.class);
        Before before = (Before) annotation;
        if (before!=null){
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
private static void after(ForTestTest forTestTest){
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
}