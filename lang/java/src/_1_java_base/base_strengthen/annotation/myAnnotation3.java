package _1_java_base.base_strengthen.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = { ElementType.TYPE, ElementType.FIELD }) // 表示该myAnnotation3注解只能作用在类上
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface myAnnotation3 {

}