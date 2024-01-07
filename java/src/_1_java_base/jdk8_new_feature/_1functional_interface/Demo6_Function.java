package _1_java_base.jdk8_new_feature._1functional_interface;

import java.util.function.Function;

/*
 * java.util.function.Function<T,R>接口用来根据一个类型的数据得到另一个类型的数据，前者称为前置条件，后者称为后置条件
 * 把T类型转换为R类型
 * 当然如何操作都可以，并不一定是单纯的转换，所谓Function嘛,也能是Function<String,String> ，用来对字符串进行操作
 */

/* 
基本使用
 */
class FunctionDemo1 {
    /*
     * 方法参数传递一个整数的String以及一个Function<String,Integer>， 将String转换为Integer
     */
    public static Integer change(String str, Function<String, Integer> fun) {
        Integer in = fun.apply(str);
        return in;
    }

    public static void main(String[] args) {
        String str = "1234";
        Integer i = change(str, s -> Integer.parseInt(s));
        System.out.println(i);
    }
}

/*
 * andThen默认方法 也就是进行两次操作
 * 
 */
class FunctionDemo2 {
    public static void change(String s, Function<String, Integer> fun1, Function<Integer, String> fun2) {
        String ss = fun1.andThen(fun2).apply(s);
        System.out.println(ss);
    }

    public static void main(String[] args) {
        String str = "1111";
        //先转换为integer加2，再转换为字符串加上另一个字符串
        change(str, s -> Integer.parseInt(s)+2, i -> i + "" + "aaaa");
    }
}