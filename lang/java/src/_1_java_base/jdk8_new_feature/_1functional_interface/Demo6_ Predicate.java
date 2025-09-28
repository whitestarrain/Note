package _1_java_base.jdk8_new_feature._1functional_interface;

import java.util.ArrayList;
import java.util.function.Predicate;

/*
 * 有时候我们需要对某种类型的数据进行判断，从而得到一个boolean值结果。
 * 这时可以使用java.util.function.Predicate<T>接口
 * 
 * boolean test(T t)方法：
 * 用来对指定数据类型进行判断的方法，
 * 符合条件返回true，否则返回false
 */
class PredicateDemo1 {
    public static boolean CheckString(String s, Predicate<String> p) {
        return p.test(s);
    }

    public static void main(String[] args) {
        String s = "aaaaa";
        boolean b = CheckString(s, str -> str.length() == 3);// 返回字符串的长度是否等于3（lambda表达式能省略就多省略些。）
        System.out.println("长度是否等于3：" + b);
    }
}

/*
 *  and()方法：
 * 相当于&&符号，用于判断两个条件
 * 源码在pdf上
 */
class PredicateDemo2{
    private static boolean checkString(String s,Predicate<String> p1,Predicate<String> p2) {
        return p1.and(p2).test(s);
        //等价于p1.test()&&p2.test()
    }
    /*
    定义一个方法，一个字符串，两个接口，用于判断：
    1. 字符串是长度是否大于5
    2. 字符串中是否有a
    两个条件是否同时存在。
    */
    public static void main(String[] args) {
        String s="adfadfdf";
       boolean b= checkString(s,str->str.length()>5,str->str.contains("a"));//lambda表达式中不能与local variable 名称相同
       System.out.println(b);
    }
}

/* 
与and的“与”类似，默认方法or实现逻辑关系中的“或”
这里就不再演示，把上面的and改成or就行了
 */

/* 
    negate方法：取反
    使用方法：p1.negate().test(s);
    等价于 !p1.test(s);
 */


 /* 
 一个小应用
  */
 class DemoPredicate{
    public static ArrayList<String> filter(String arr[],Predicate<String> p1,Predicate<String> p2) {
        ArrayList<String> list=new ArrayList<String>();
        for(String s:arr){
            if(p1.and(p2).test(s)){
                list.add(s);
            }
        }
        return list;
    }
     public static void main(String[] args) {
        String[] array= { "迪丽热巴,女", "古力娜扎,女", "马尔扎哈,男", "赵丽颖,女" };
       ArrayList<String> a= filter(array,s->{
        String temp=s.split(",")[0];
        return temp.length()>=4;
       },s->{
        String temp=s.split(",")[1];
        return temp.equals("女");
       });
       for(String s:a){
           System.out.println(s);
       }
     }
 }