package _1_java_base.base_strengthen.annotation.case_new_test;

public class Calculator {
    @Check
    public int add(int a,int b){
        return a+b;
    }
    @Check
    public int sub(int a,int b){
        return a-b;
    }
    @Check
    public int mul(int a,int b){
        String str=null;
        System.out.println(str.toString());
        return a*b;
    }
    @Check
    public int div(int a,int b){
        return a/b;
    }
    public void show(){
        System.out.println("无bug");
    }
}