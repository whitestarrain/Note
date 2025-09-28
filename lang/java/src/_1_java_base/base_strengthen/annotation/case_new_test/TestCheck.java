package _1_java_base.base_strengthen.annotation.case_new_test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;

/* 简单测试框架，
当主方法执行后，会自动执行被检测的所有方法（加了@Check注解的方法，测试是否有异常，并记录到文件中）
*/
public class TestCheck {
    public static void main(String[] args) {
        int number = 0;
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter("bug.txt", true));
        } catch (Exception e) {
            System.out.println("输出流错误");
            throw new RuntimeException();
        }
        // 1.创建字节码文件对象
        Calculator c = new Calculator();
        // 2.获取字节码文件
        Class<Calculator> cclass = Calculator.class;
        // 3.获取所有的方法
        Method[] m = cclass.getDeclaredMethods();
        // 4.判断方法上是否有@Check注解
        for (Method mtemp : m) {
            if (mtemp.isAnnotationPresent(Check.class)) {
                mtemp.setAccessible(true);//暴力反射
                // 5.有的话就执行，测试是否有异常
                try {
                    mtemp.invoke(c, 1, 0);
                } catch (Exception e) {
                    // 6.捕获异常。
                    // 记录到文件中
                    number++;
                    try {
                        bw.write("出现异常");
                        bw.newLine();
                        bw.write("异常名称" + e.getCause().getClass().getSimpleName());
                        bw.newLine();
                        bw.write("异常原因：" + e.getCause().getMessage());
                        bw.newLine();
                        bw.flush();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
        try {
            bw.write("本次测试一共出现" + number + "次异常");
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            System.out.println("输出流关闭错误");
        }
    }
}