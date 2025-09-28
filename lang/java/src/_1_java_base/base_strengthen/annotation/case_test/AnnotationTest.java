package _1_java_base.base_strengthen.annotation.case_test;

import java.lang.reflect.Method;

/*
框架类
在不改变该类任意代码的前提下，但可以创建任意类对象并执行任意方法
*/
@ProAnnotation(className = " _1_java_base.base_strengthen.annotation.case_test.Person", methodName = "eat")
public class AnnotationTest {
    public static void main(String[] args) throws Exception {
        // 1 解析注解
        // 获取该类的字节码文件对象
        Class<AnnotationTest> annotationTestClsss = AnnotationTest.class;

        // 2获取上边的注解对象
        ProAnnotation pro = annotationTestClsss.getAnnotation(ProAnnotation.class);// 其实就是在内存中区生成了一个该注解接口的子类实现对相关
        /* 
        相当于：
        public class proImp1 implements ProAnnotation{
            public String className(){
                return "_1_java_base.base_strengthen.annotation.case_test.Person";
            }
            public String methodName(){
                return "eat";
            }
        }
        也就是说在调用getAnnotation时，就会把上面的那个类创建一个对象病返回给你，在通过接口接收
         */


        // 3调用注解对象中定义的抽象方法（也就是属性）获取返回值
        String className = pro.className();
        String methodName = pro.methodName();

        System.out.println("className:"+className+"\nmethodName:"+methodName);



         //4 加载该类进内存
         Class cls=Class.forName(className);//通过className找到对应Class对象
         //5 创建对象
         Object obj=cls.newInstance();//用@Deprecated标注，为过时方法
         //6 获取方法对象
         Method method=cls.getMethod(methodName);
         //7.方法运行
         method.invoke(obj);
    }
}

/*
 * 修改代码可以达到修改配置文件同样效果但是这样需要重新编译，测试和上线，比修改配置文件麻烦得多。 修改配置文件也会使扩展性更强
 */