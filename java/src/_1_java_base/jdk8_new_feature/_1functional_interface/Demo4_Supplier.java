package _1_java_base.jdk8_new_feature._1functional_interface;

import java.util.function.Supplier;

/*
 * 常用函数式接口
 * 位于java.util.function下
 * 此处只说主要几个常用的。
 */

//1.Supplier
/* 
java.util.function.Supplier<T>接口仅包含一个无参的方法：T get()。
用来获取一个泛型参数指定类型的对象数据。
由于这是一个函数式接口，这也就意味着对应的Lambda表达式需要“对外提供”一个符合泛型类型的对象数据。
被称为生产形接口，指定接口的泛型是什么类型，接口中的get类型就会生产什么类型的数据
*/
class SupplierDemo1 {
    // 定义一个方法，参数传递Supplier<String>接口，泛型执行get()会返回一个String
    private static String getString(Supplier<String> s) {
        return s.get();
    }

    public static void main(String[] args) {
        System.out.println(getString(() -> "sadfsaf"));// 已经简化过，过程看前面笔记。

    }
}

/*
 * 求数组中最大值，使用Supplier接口
 */
class SupplierDemo2 {
    /*
     * 练习：求数组元素最大值 使用Supplier接口作为方法参数类型，通过Lambda表达式求出int数组中的最大值。
     * 提示：接口的泛型请使用java.lang.Integer类。
     */

    // 定义一个方法,用于获取int类型数组中元素的最大值,方法的参数传递Supplier接口,泛型使用Integer
    public static int getMax(Supplier<Integer> sup) {
        return sup.get();
    }

    public static void main(String[] args) {
        // 定义一个int类型的数组,并赋值
        int[] arr = { 100, 0, -50, 880, 99, 33, -30 };
        // 调用getMax方法,方法的参数Supplier是一个函数式接口,所以可以传递Lambda表达式
        int maxValue = getMax(() -> {
            // 获取数组的最大值,并返回
            // 定义一个变量,把数组中的第一个元素赋值给该变量,记录数组中元素的最大值
            int max = arr[0];
            // 遍历数组,获取数组中的其他元素
            for (int i : arr) {
                // 使用其他的元素和最大值比较
                if (i > max) {
                    // 如果i大于max,则替换max作为最大值
                    max = i;
                }
            }
            // 返回最大值
            return max;
        });
        System.out.println("数组中元素的最大值是:" + maxValue);
    }
}
