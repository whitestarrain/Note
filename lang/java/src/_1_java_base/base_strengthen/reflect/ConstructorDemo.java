package _1_java_base.base_strengthen.reflect;

import java.lang.reflect.Constructor;

@SuppressWarnings("all")//压制警告
public class ConstructorDemo{
    public static void main(String[] args) throws Exception {
        Class personClass=Person.class;
        Constructor constructor=personClass.getConstructor(String.class,int.class);//这里传入参数类型对应class对象，得到构造器。
        //通过参数的种类以及数量可以指定是哪一个构造函数

        System.out.println(constructor);
        //通过构造器创建对象
        Object obj=constructor.newInstance("张十三",23);
        System.out.println(obj);

        Constructor constructor2=personClass.getConstructor();
        System.out.println(constructor2.newInstance());//通过空参数列表来获得实例对象
        //通过Class对象中方法
        System.out.println(personClass.newInstance());//内部访问的就是构造器的空参构造

        //其他方法同Field，不再一一演示。如setAccessible，getDeclaredConstructor等
    }
}