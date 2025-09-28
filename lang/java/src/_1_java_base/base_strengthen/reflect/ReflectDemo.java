package _1_java_base.base_strengthen.reflect;

public class ReflectDemo {
    public static void main(String[] args) throws Exception {
        //1 Class.forname("全；类名")
        Class c1 = Class.forName("_1_java_base.base_strengthen.reflect.Person");
        System.out.println(c1);

        //2 类名.class
        Class c2=Person.class;
        System.out.println(c2);

        //3对象.getClass
        Class c3=new Person().getClass();
        System.out.println(c3);

        //比较
        System.out.println(String.valueOf(c1==c2)+"  "+String.valueOf(c2==c3));

    }
}