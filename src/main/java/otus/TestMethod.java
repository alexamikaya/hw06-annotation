package otus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})//использование аннотации методом
@Retention(RetentionPolicy.RUNTIME)
public @interface TestMethod {

}
