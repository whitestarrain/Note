package _1_java_base.jdk8_new_feature._1functional_interface;

/*
函数式接口：有且只有一个抽象方法的接口，称之为函数式接口        lambda表达式就是重写抽象方法的。
当然接口中可以包含其他的方法（默认，静态，私有等非抽象接口） !!!!!!!!!!!!!!!!!!!!记住，
jdk8新特性，接口中可以有这些，但默认方法必须要用default修饰
 */

/* 一、java8新特性
    1、函数式接口
        当接口里面只有一个抽象方法的时候，就是函数式接口，可以使用注解强制约束接口只能有一个抽象方法。
        注解：从java5开始引入注解，对字节码文件进行一些说明。
        @FunctionalInterface注解的作用：在编译时告诉编译器该接口只能有一个抽象方法。
        注：
        1、该注解在java.lang包下，不用引入
        2、Lambda表达式只能针对函数式接口使用

    2、接口里面的静态方法
    从java8开始，接口里面可以有静态方法，和普通类的静态方法类似，使用static修饰 ，但接口里面只能是public的
        [public] static <返回值类型><方法名>[形参列表]{
            //方法体
        }

    3、接口里面的默认方法
            从java8开始，除了可以在接口里面写静态方法，还可以写非静态方法，但是必须用 default 修饰。
        默认方法可以被继承。
        
        注：
        1、default方法和static方法均只能被public修饰
        2、如果继承了多个父接口，有重复的默认方法被继承到子接口，必须使用super引用明确指定调用哪个接口的默认方法。
        在子接口必须重写重复的方法，并使用下面的语法。
        <父接口名>.super.<重复方法名>([参数]);
        3、同样地，对于一个类同时实现了多个接口，而这些接口均含有同样的默认方法的情况和2类似。
        4、父接口的抽象方法，在子接口里面可以使用默认方法实现，这样实现类里面就无需再实现了。
              如果实现类再去实现的话就是方法的覆盖了。
        5、如果父接口有一个默认方法，在子接口里可以重写为抽象方法(去掉父接口的行为)。
         */

@FunctionalInterface // 当不是抽象方法时编译失败
interface MyFunctionalInterface {
    public abstract void method();
}

/*
 * 函数式接口的使用：一般可以作为方法的参数和返回值类型
 */
public class Demo1 {
    public static void show(MyFunctionalInterface myinterface) {
        myinterface.method();
    }

    public static void main(String[] args) {
        // 调用show方法，方法的参数是一个接口，所以可以传递接口的实现类的对象（此处直接通过匿名内部类来写了）
        show(new MyFunctionalInterface() {
            @Override
            public void method() {
                System.out.println("使用匿名内部类重写函数式接口中的方法");
            }
        });
        // 调用show方法，参数是一个函数式接口，所以可以传递lambda表达式
        show(() -> {
            System.out.println("使用lambda表达式，重写method方法");
        });

        // 简化lambda表达式
        show(() -> System.out.println("使用lambda表达式，重写method方法"));// 有且只有一条语句的话，大括号，分号省略

        /*
         * 匿名内部类和lambda表达式区别： 在这里匿名内部类会生成一个FunctionalInterfaceDemo1$1.class文件
         * 而lambda表达式不会。效率更高些。
         * 
         * 
         * 只要有函数式接口作为参数，就可以使用lambda表达式 lambda表达式其实就是在重写接口中唯一的方法
         */
    }
}