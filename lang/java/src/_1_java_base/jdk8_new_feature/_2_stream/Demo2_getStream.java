package _1_java_base.jdk8_new_feature._2_stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/*
java.util.stream.Stream<T>是Java 8新加入的最常用的流接口。（这并不是一个函数式接口。）
    获取一个流非常简单，有以下几种常用的方式：
    所有的Collection集合都可以通过stream默认方法获取流；(单列集合，map没有这个方法)
    Stream接口的静态方法of可以获取数组对应的流 
 */
class getStreamDemo1 {
    public static void main(String[] args) {
        // 把集合转换为Stream流
        List list = new ArrayList<String>();
        Stream s = list.stream();

        Set<String> set = new HashSet<>();
        Stream s2 = set.stream();

        Map<String, String> m = new HashMap<String, String>();
        Set<Map.Entry<String, String>> se = m.entrySet();
        Stream s3 = se.stream();

        // Stream接口 of函数,参数为可变参数 public static<T> Stream<T> of(T... values)，传输数组或者多个T参数
        Stream<Integer> s4 = Stream.of(1, 2, 3, 4);
        Integer[] intarr={1,2,3,4};
        Stream<Integer> s5=Stream.of(intarr);
    }
}