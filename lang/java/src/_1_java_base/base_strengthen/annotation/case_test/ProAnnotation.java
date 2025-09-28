package _1_java_base.base_strengthen.annotation.case_test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)//作用在类上
@Retention(RetentionPolicy.RUNTIME)//保留到运行阶段
public @interface ProAnnotation {
    String className();
    String methodName();
}