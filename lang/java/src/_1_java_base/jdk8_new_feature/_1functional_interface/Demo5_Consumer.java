package _1_java_base.jdk8_new_feature._1functional_interface;

import java.util.function.Consumer;

//Consumer接口
class ConsumerDemo1 {
    /*
     * java.util.function.Consumer<T>接口则正好与Supplier接口相反，它不是生产一个数据，而是消费一个数据，
     * 其数据类型由泛型决定。 Consumer接口中包含抽象方法void accept(T t)，意为消费一个指定泛型的数据。 消费方式自己定义
     */

    /*
     * 定义一个方法， 参数为String资源以及一个Consumer<String>接口 通过lambda表达式
     */
    public static void methodConsumer(String name, Consumer<String> c) {
        c.accept(name);
    }

    public static void main(String[] args) {
        methodConsumer("测试资源", (name) -> {
            // 消费方式：输出长度
            System.out.println("字符串长度为：" + name.length());
        });
    }
}

class ConsumerDemo2 {
    /*
     * 如果一个方法的参数和返回值全都是Consumer类型，那么就可以实现效果： 消费数据的时候，首先做一个操作，然后再做一个操作，实现组合。
     * 而这个方法就是Consumer接口中的default方法
     * 
     * 
     * Consumer<String> con1,con2; String s="aaa";
     * 
     * con1.accept(s); con2.accept(s); 等同于：
     * con1.andThen(con2).accept(s);//andThen用来连接连个接口，消费顺序从左到右。
     */

    public static void method(String s, Consumer<String> c1, Consumer<String> c2) {
        c1.andThen(c2).accept(s);
    }

    // 在这里一个c1用来将字符串变为大写，c2用来将字符串倒置，并输出
    public static void main(String[] args) {
        method("abcdefg", (s) -> {
            s = s.toUpperCase();
            s = s + "1";
            System.out.println(s);
        }, (s) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(s);
            sb.reverse();
            s = sb.toString();
            System.out.println(s);
        });// lambda表达式中，如果只有一句，那么不加分号就相当于返回那句表达式的值
    }

    /*
     * ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！。。
     * 执行结果： ABCDEFG1 gfedcba
     * 原因： 这里指的消费资源，一直是指的是消费给定的一个资源，即使该资源在一个accept中该改变，accept消费的也是在改变前的资源
     */
}