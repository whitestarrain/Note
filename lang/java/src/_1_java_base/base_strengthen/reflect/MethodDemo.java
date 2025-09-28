package _1_java_base.base_strengthen.reflect;

import java.lang.reflect.Method;

public class MethodDemo {
    public static void main(String[] args)throws Exception {
        Class personClass =Person.class;
        Method eat_method=personClass.getMethod("eat");//获得方法，通过方法名称以及参数列表获得
        System.out.println(eat_method);
        //执行方法
        eat_method.invoke(new Person("aaa"));//这里没有传入参数

        Method eat_Method2=personClass.getMethod("eat", String.class);
        eat_Method2.invoke(new Person("bbb"), "香蕉");//传入一个参数

        System.out.println();
        System.out.println("所有public方法，其中包括从Object中继承的");
        for (Method m : personClass.getMethods()) {
            System.out.println(m);
        }

        System.out.println("所有方法（通过getDeclareMethods获得的所有方法不包括从Object种继承的）：");
        for(Method m:personClass.getDeclaredMethods()){
            System.out.println(m);
        }
        //非public成员函数也可以通过暴力反射（setAccessible(true);）来使用

        System.out.println(eat_Method2.getName());//获取方法名

        //获取类名(全类名)：
        System.out.println(personClass.getName());
    }
}