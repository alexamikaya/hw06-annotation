
public class TestMethods {
    private Task task;

    @Before
        //проверяет, что класс работает
    void setUp() {
        task=new Task();
        if (task==null) {
    throw new NullPointerException("Task не существует");
        }
    }
    @Test()
        //проверяет, что рассчитывает верно
    void assertThat() {


            double result = -71;
            final var taskresult = Task.task(8, 5, 3);
            if (result!=taskresult){
                throw new AssertionError("Дикриминант посчитан неверно");//ошибка нарушения утверждения
            }

    }
    //заведомо провальный тест
    @Test()
    void assertThat2() {
        double result = -71;
        final var taskresult = Task.task2(8, 5, 3);
        if (result!=taskresult){
            throw new AssertionError("Дикриминант посчитан неверно");//ошибка нарушения утверждения
        }
    }
    @After
        //выводит информацию об завершении
    void results() {
        task = null;
        System.out.println("Завершено");

    }

}
