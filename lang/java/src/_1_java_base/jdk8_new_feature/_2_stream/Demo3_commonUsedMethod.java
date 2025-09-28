package _1_java_base.jdk8_new_feature._2_stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/*
 * 延迟方法：返回值类型仍然是Stream接口自身类型的方法，因此支持链式调用。（除了终结方法外，其余方法均为延迟方法。）
 * 终结方法：返回值类型不再是Stream接口自身类型的方法，因此不再支持类似StringBuilder那样的链式调用。本小节中，终结方法包括count和forEach方法。
 */
class commonUsedMethodDemo1 {
        public static void main(String[] args) {
                List<String> list = new ArrayList<>();
                list.add("n");
                list.add("m");
                list.add("s");
                list.add("l");
                list.add("l");
                // ————————————————————————————————————————————————————————————————————
                /*
                 * foreach: void forEach(Consumer<? super T> action); 终结方法
                 * 用来遍历Stream中的数据，传入参数的是Consumer接口
                 */
                list.stream().forEach(name -> System.out.println(name));// 此处不用加参数类型是因为List中已经明确类型。
                // ————————————————————————————————————————————————————————————————————

                /*
                 * filter 过滤，将一个流转化为另一个子集流 参数为接口Predicate
                 */
                Stream<String> s2 = list.stream().filter(name -> name.length() >= 3);
                s2.forEach(name -> System.out.println(name));

                // ————————————————————————————————————————————————————————————————————
                /*
                 *注意点：
                 * Stream属于管倒流，只能被消费一次 延迟方法中 第一个Stream调用完方法，数据就会流转到下一个流上，
                 * 第一个Stream就会关闭，就不能再调用方法了，否则会抛出异常
                 */
                // ————————————————————————————————————————————————————————————————————
                /*
                 * Map() 映射 把一个数据映射到为另一个数据，本质上就是Function接口 参数为接口Function
                 */
                Stream<String> ss = Stream.of("111", "222", "333");
                ss.map(str -> Integer.parseInt(str) + 1).forEach(i -> System.out.println(i));
                // ————————————————————————————————————————————————————————————————————
                /*
                 * count方法： 统计个数，返回Long类型整数 终结方法， 似Collection中的size方法，用来统计流中数据的个数
                 */

                Long l = list.stream().count();
                System.out.println(l);
                // ————————————————————————————————————————————————————————————————————
                /*
                 * limit(Long l)方法，对流中前n个元素进行截取，返回新的流 延迟方法
                 */
                list.stream().limit(3).forEach(s -> System.out.println(s));
                // ————————————————————————————————————————————————————————————————————

                /*
                 * skip(Long l) 跳过前n个元素,只保留之后的元素，返回新的流 n过大会返回一个空流 延迟方法
                 */
                list.stream().skip(3).forEach(s -> System.out.println(s));
                // ————————————————————————————————————————————————————————————————————
                /*
                 * concat(Stream s1,Stream s2)方法，Stream接口中的静态方法 用于连接两个流
                 */
                Stream<String> stream1 = list.stream();
                Stream<String> stream2 = Stream.of("a", "w", "s", "l");
                Stream stream3 = Stream.concat(stream1, stream2);
                stream3.forEach(s->System.out.println(s));

        }
}