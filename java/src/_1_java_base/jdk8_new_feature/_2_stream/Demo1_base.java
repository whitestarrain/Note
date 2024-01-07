package _1_java_base.jdk8_new_feature._2_stream;

import java.util.ArrayList;
import java.util.List;

/*
 * 说到Stream便容易想到I/O Stream，而实际上，谁规定“流”就一定是“IO流”呢？
 * 在Java8中，得益于Lambda所带来的函数式编程，引入了一个全新的Stream概念，用于解决已有集合类库既有的弊端。
 */
class Normal {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("张强");
        list.add("张三丰");
        for (String tempString : list) {
            if (tempString.startsWith("张")) {
                System.out.println(tempString);
            }
        }
        for (String tempString : list) {
            if (tempString.length() >= 3) {
                System.out.println(tempString);
            }
        }
    }
}

/*
 * Stream是jdk1.8之后出现的，关注做什么，而不是怎么做 期中很多方法调用函数式接口
 */

class ByStream {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("张强");
        list.add("张三丰");

        // 集合中新方法 Steam。返回一个Stream<T>,Stream可以通过filter传入Predicate（此处直接使用的lambda表达式）来进行筛选
        list.stream().filter(name -> name.startsWith("张")).filter(name -> name.length() >= 3)// 此处进行内部迭代，增强for循环，Iterator都是外部迭代
                .forEach(name -> System.out.println(name));
        ;
    }
}

/*
 * 看pdf!!!!!!!!!!!!!!!!!!!!!，了解详细概念
 */