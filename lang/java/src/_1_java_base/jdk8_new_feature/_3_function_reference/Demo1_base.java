package _1_java_base.jdk8_new_feature._3_function_reference;

/*
 * 方法引用是为了解决；lambda表达式冗余问题
 * 就行写一个方法可以调用多次一样，lambda写一个lambda表达式也可以调用多次
 * 迄今为止转变：实现接口的类->匿名类->lambda表达式->方法引用，后三者在同一个位置可以相互替换
 */
@FunctionalInterface
interface printInterface {
    void printMethod(String s);
}

public class Demo1_base {
    // 定义一个方法，参数为函数式接口，对字符串进行打印
    public static void print(String s, printInterface p) {
        p.printMethod(s);
    }

    public static void main(String[] args) {
        // 调用方法，通过lambda表达式进行输出
        print("Hello World", s -> System.out.println(s));// 接口中抽象方法已经指定了参数类型

        /*
         * 分析： 通过lambda表达式，把参数s传给对象System.out,对象System.out调用Println方法 要求：对象和方法都已经存在
         * 此时可以通过方法引用来优化lambda表达式
         */
        print("Hello World", System.out::println);
        /*
         * 双冒号::为引用运算符，而它所在的表达式被称为方法引用。如果Lambda要表达的函数方案已经存在于某个方法的实现中，
         * 那么则可以通过双冒号来引用该方法作为Lambda的替代者，参数可以省略
         */

        /*
         * 语义分析 ：
         * 例如上例中，System.out对象中有一个重载的println(String)方法恰好就是我们所需要的。
         * 那么对于printString方法的函数式接口参数，对比下面两种写法，完全等效：
         * Lambda表达式写法：s ->System.out.println(s);
         * 方法引用写法：System.out::println
         * 第一种语义是指：拿到参数之后经Lambda之手，继而传递给System.out.println方法去处理。
         * 第二种等效写法的语义是指：直接让System.out中的println方法来取代Lambda。
         * 两种写法的执行效果完全一样，而第二种方法引用的写法复用了已有方案，更加简洁。注:Lambda
         * 中传递的参数一定是方法引用中的那个方法可以接收的类型,否则会抛出异常
         */
    }
}