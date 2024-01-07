package _1_java_base.jdk8_new_feature._3_function_reference;

/*
    通过现有对象以及方法进行引用
    通过类中的静态方法进行引用
 */
//
class printClass{
    public void print(String s){
        System.out.println(s);
    }
    public static void printtoUpcase(String s){
        System.out.println(s.toUpperCase());
    }
}
public class Demo2_objAndStaticMethod {
    public static void mainPrint(String s,printInterface p) {
        p.printMethod(s);
    }
    public static void main(String[] args) {
        mainPrint("Hello World",new printClass()::print);//现有对象中的现有方法
        mainPrint("Hello World", printClass::printtoUpcase);//通过类中的静态方法
    }
}
