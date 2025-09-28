package _1_java_base.base_strengthen.annotation;

public @interface myAnnotation {//定义同接口
    int age();
    String name() default "张三"; //默认张三
   /*  public String show2();
    PeopleEnum per(); */
}