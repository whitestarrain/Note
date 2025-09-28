package _1_java_base.jdk8_new_feature._1functional_interface;
//两个练习

import java.util.Arrays;
import java.util.Comparator;
//1，Runable

/*
    例如java.lang.Runnable接口就是一个函数式接口，
    假设有一个startThread方法使用该接口作为参数，那么就可以使用Lambda进行传参。
    这种情况其实和Thread类的构造方法参数为Runnable没有本质区别。
 */
class RunnableDemo1 {
    // 定义一个方法startThread,方法的参数使用函数式接口Runnable
    public static void startThread(Runnable run) {
        // 开启多线程
        new Thread(run).start();
    }

    public static void main(String[] args) {
        // 调用startThread方法,方法的参数是一个接口,那么我们可以传递这个接口的匿名内部类
        startThread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "-->" + "线程启动了");
            }
        });

        // 调用startThread方法,方法的参数是一个函数式接口,所以可以传递Lambda表达式
        startThread(() -> {
            System.out.println(Thread.currentThread().getName() + "-->" + "线程启动了");
        });

        // 优化Lambda表达式
        startThread(() -> System.out.println(Thread.currentThread().getName() + "-->" + "线程启动了"));
    }
}

// 2,comparable

class ComparatorDemo {
    public static Comparator<String> getComparator() {
        return new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }

        };
    }

    // 优化：
    public static Comparator<String> getComparator2() {

        // 因为这里要返回函数式接口，所以这里也可以使用lambda表达式，不只是函数式接口作为参数时可以用，
        // 每当使用匿名内部类时，就要考虑使用lambda表达式
        return (String o1, String o2) -> {
            return o2.length() - o1.length();
        };
    }

    // 优化labmda表达式
    public static Comparator<String> getComparator3() {
        return (o1, o2) -> o2.length() - o1.length();// 参数类型可以省略（是因为泛型的原因，已经明确了类型），只有一行代码，所以return，大括号，分号省略（当没有大括号以及分号时，就时默认返回该句的值）
    }

    public static void main(String[] args) {
        String[] arr = { "adsf", "asfefef", "a" };
        Arrays.sort(arr, getComparator3());// 使用lambda表达式做出的比较器来进行排序
        System.out.println(Arrays.toString(arr));
    }
}