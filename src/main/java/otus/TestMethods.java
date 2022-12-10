package otus;


public class TestMethods {




@Before
        //проверяет, что класс работает
    void setUp(Object o) {

        if (o.equals(null)==true) {
            throw new NullPointerException(" не существует");
        }


    }
@Test
        //проверяет, что рассчитывает верно
    void assertThat(Object o) {


        String testresult = "-71.0";
        String result = o.toString();
        if (testresult.equals(result)==false){
            throw new AssertionError("Дикриминант посчитан неверно");//ошибка нарушения утверждения
        }

    }
    //заведомо провальный тест
@Test
    void assertThat2(Object o) {
    String testresult = "60.0";
    String result = o.toString();
    if (testresult.equals(result)==false){
            throw new AssertionError("Дикриминант посчитан неверно");//ошибка нарушения утверждения
        }

    }
@After
        //выводит информацию об завершении
    void results() {

        System.out.println("Завершено");

    }

}
