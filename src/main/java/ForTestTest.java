import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class ForTestTest {
    public static void main(String[] args) throws Exception {

        Class<TestMethods> testmethods=TestMethods.class;
        for (Method method : testmethods.getDeclaredMethods()){
            Annotation annotation = method.getAnnotation(Before.class);
            Before before = (Before) annotation;
            if (before!=null){
                try{
                    method.invoke(testmethods.getDeclaredConstructor().newInstance());

                } catch(Throwable ex)
                {
                    System.out.println(ex.getCause());
                }
            }
        }

        for (Method method : testmethods.getDeclaredMethods()){
            Annotation annotation = method.getAnnotation(Test.class);
            Test test = (Test) annotation;
            if (test!=null){
                try{
                    method.invoke(testmethods.getDeclaredConstructor().newInstance());

                } catch(Throwable ex)
                {
                    System.out.println(ex.getCause());
                }
            }
        }

        for (Method method : testmethods.getDeclaredMethods()){
            Annotation annotation = method.getAnnotation(After.class);
            After after = (After) annotation;
            if (after!=null){
                try{
                    method.invoke(testmethods.getDeclaredConstructor().newInstance());

                } catch(Throwable ex)
                {
                    System.out.println(ex.getCause());
                }
            }
        }


    }
}