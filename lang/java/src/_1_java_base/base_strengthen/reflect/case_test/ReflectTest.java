package _1_java_base.base_strengthen.reflect.case_test;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/*
框架类
在不改变该类任意代码的前提下，但可以创建任意类对象并执行任意方法
*/
public class ReflectTest {
    public static void main(String[] args) throws Exception{
        //1 加载配置文件,创建Properties对象
        Properties pro=new Properties();//配置文件要放在src目录下
        ClassLoader classloder=ReflectTest.class.getClassLoader();//获得类加载器（将ReflectTest加载进内存的类加载器）
        //ClassLoder在学到web时会仔细讲
        //因为ClassLoder能找到class文件，也就是说能找到对应目录以及文件
        //getResource用来获取路径 getResourceAsStream用来获取字节流
        InputStream in=classloder.getResourceAsStream("pro.properties");//获得资源的字节流
        pro.load(in);
        System.out.println(pro);
        //2 获取配置文件中的数据   //因此只要改动配置文件，就可以创建任意对象以及方法
        String className=pro.getProperty("className");
        String methodName=pro.getProperty("methodName");
        //3 加载该类进内存
        Class cls=Class.forName(className);//通过className找到对应Class对象
        //4 创建对象
        Object obj=cls.newInstance();
        //5 获取方法对象
        Method method=cls.getMethod(methodName);
        //6.方法运行
        method.invoke(obj);
    }
}

/* 
修改代码可以达到修改配置文件同样效果但是这样需要重新编译，测试和上线，比修改配置文件麻烦得多。
修改配置文件也会使扩展性更强
 */