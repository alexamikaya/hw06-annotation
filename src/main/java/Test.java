import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//Сохраняем аннотацию в .class-файлах и делаем доступной во время выполнения программы
public @interface Test {

}
