
import java.util.Objects;


public class TestMethods {


    @Before
        //проверяет, что класс работает
    void setUp() {
        Task task=new Task();
        Task.task(3,4,5);

    }
    @Test()
        //проверяет, что рассчитывает верно
    void assertThat() {

        try{
            double b=5,c=3, a=8;
            double result = 121;
            Task task=new Task();
            Objects.equals(result, Task.task(a, b, c));

        } catch(Throwable ex)
        {
            System.out.println(ex.getCause());
        }


    }
    //заведомо провальный тест
    @Test()
    void assertThat2() {
        try {
            double b=2,c=3, a=8;

            double result = 100;
            Task task2=new Task();
            result=Task.task2(a,b,c);
            Objects.equals(result, Task.task2(a, b, c));}
        catch(Throwable ex)
        {
            System.out.println(ex.getCause());
        }
    }
    @After
        //выводит информацию об завершении
    void results() {
        System.out.println("Завершено");

    }

}
