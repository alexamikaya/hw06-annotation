import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})//использование аннотации методом
@Retention(RetentionPolicy.RUNTIME)//Сохраняем аннотацию в .class-файлах и делаем доступной во время выполнения программы
public @interface After {
}
