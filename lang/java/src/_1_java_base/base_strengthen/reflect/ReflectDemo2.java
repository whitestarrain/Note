package _1_java_base.base_strengthen.reflect;

import java.lang.reflect.Field;

public class ReflectDemo2 {
    public static void main(String[] args) throws Exception{
        Class personClass=Person.class;   
        Field field_a=personClass.getField("a");//获得类Person中的成员 a 的Field对象
        //在这里，field_a对象可以通过指定用来获取与设置所有Person对象的a成员
        Person p=new Person();
        System.out.println(field_a.get(p)); //获得指定对象中成员a的值
        field_a.set(p, "zhangsan");//设置制定对象中成员a的值
        System.out.println(field_a.get(p));

        System.out.println();
        //获取所有成员方法
        for (Field a:personClass.getDeclaredFields()){
            System.out.println(a);
        }

        System.out.println();
        Field field_name=personClass.getDeclaredField("name");
        System.out.println(field_name);//获得name对应成员变量

        //下一行执行的话会报错，会有非法访问异常，因为name是非public的，尽管能得到成员变量对应对象但是无法使用
        //field_name.get(p);
        //所以要忽略权限修饰符的安全检查
        field_name.setAccessible(true);//暴力反射
        System.out.println(field_name.get(p));
    }
}