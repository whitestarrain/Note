package _1_java_base.jdk8_new_feature._3_function_reference;

/*
    类中的lambda表达式，如果有继承关系，那么可以使用super引用来代替lambda
    如果使用调用本类方法，那么可以通过this引用来代替lambda
 */
@FunctionalInterface
interface greeAble {
    void greet();
}

class Father {
    public void sayHello() {
        System.out.println("Father:hello");
    }

    public void sayHello2(greeAble g) {
        g.greet();
    }
}

class son extends Father {
    public void sayHello1Son1(){
        sayHello2(()->new Father().sayHello());//使用lambda表达式
    }
    //通过super进行引用
    public void sayHello2Son() {
        sayHello2(super::sayHello);
    }



    public void sayHello(){
        System.out.println("Son:Hello");
    }
    public void sayHello(greeAble g){
        g.greet();
    }
    public void autoSayHello(){
        sayHello(this::sayHello);
    }
}

public class Demo3_SuperAndThis {
    public static void main(String[] args) {
        new son().sayHello1Son1();
        new son().sayHello2Son();
        new son().autoSayHello();
    }
}