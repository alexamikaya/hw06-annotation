package otus;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Demo {

    private Demo() {
    }

    static ClassInterface newClass() {
        InvocationHandler handler = new DemoInvocationHandler(new Task());//создаем экземпляр оригинального класса
        return (ClassInterface) java.lang.reflect.Proxy.newProxyInstance(Demo.class.getClassLoader(), //применяем механизм dynamic proxy
                new Class<?>[]{ClassInterface.class}, handler);//массив интерфейсов, которые нужно имплементировать
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final ClassInterface classInterface;

        DemoInvocationHandler(ClassInterface classInterface) {
            this.classInterface = classInterface;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable { //логируем факт вызова

            if (method != null) {
                Annotation annotation = method.getAnnotation(TestMethod.class);
                TestMethod test = (TestMethod) annotation;
                otus.ForTestTest forTestTest = new otus.ForTestTest();
                if (test != null) {
                    forTestTest.info(forTestTest, method.invoke(this.classInterface, args));
                    forTestTest.displayInfo(forTestTest, method);
                }
            }
            return method.invoke(classInterface, args);


        }
        @Override
        public String toString() {
            return "DemoInvocationHandler{" +
                    "myClass=" + classInterface +
                    '}';
        }

    }
}


