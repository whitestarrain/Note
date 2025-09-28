package _1_java_base.jdk8_new_feature._1functional_interface;

/* 
函数式编程：

1 lambda表达式延迟执行，提高效率
性能浪费案例：看pdf
当使用showlog方法时，会先拼接字符串，再传递两个参数，导致当level为2时，字符串就白拼接了，造成了性能浪费。
在这里可以通过lambda表达式进行优化。
*/

interface MessageBuildeer {
    public abstract String messageBuilder();// 返回拼接后的字符串
}

class showLog02 {
    // 旧版
    public static void showLog(int level, String message) {
        /*
         * if (level==1) { System.out.println(message); }
         */
    }

    // 优化：

    public static void showLog2(int level, MessageBuildeer mb) {
        if (level == 1) {
            // 日志等级如果是1级，那么就输出字符串。
            System.out.println(mb.messageBuilder());
        }
    }

    public static void main(String[] args) {
        String str1 = "Hello", str2 = "world";

        showLog(1, str1 + str2);

        showLog2(1, () -> {
            System.out.println("满足后执行，不满足条件不执行1");
            // 返回一个拼接好的字符串。
            return str1 + str2;
        });
        showLog2(2, () -> {
            System.out.println("满足后执行，不满足条件不执行2");// 该句不会打印
            // 返回一个拼接好的字符串。
            return str1 + str2;
        });
        /*
         * 使用lambda表达式作为参数传递，仅仅把参数传递到shoulog2中方法中，
         * 只有满足条件时（即日志等级是1级），才会调用接口MessageBuilder接口， 执行
         * messageBuilder(),进行字符串的拼接，不会造成性能的浪费。
         */

    }
}