package _4_javaweb._01_xml;

/**
 * * Jsoup快速入门
 */


import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Jsoup_Demo0
 */
public class _0_Jsoup_Document_Demo {
    public static void main(String[] args) throws IOException {

        // 1. 导入jar包
        // 2. 获取Document对象，根据xml文件获取
        // 2.1获得xml的path
        String path = _0_Jsoup_Document_Demo.class.getClassLoader().getResource("c3p0-config.xml").getPath();
        // 这种查找的路径会把空格变成 %20，因此：
        path = path.replace("%20", " ");
        // 2.2 解析xml，加载进内存，获取dom树-->document
        Document doc = Jsoup.parse(new File(path), "utf-8");
        // 获得元素对象 Element
        Elements elements = doc.getElementsByTag("property");
        /*
         * System.out.println(doc); System.out.println("//---------------------------");
         * System.out.println(elements);
         * System.out.println("//---------------------------");
         * System.out.println(elements.size());
         * System.out.println("//---------------------------");
         * System.out.println(doc.getElementsByAttribute("name"));
         * System.out.println("//---------------------------");
         * System.out.println(doc.getElementsByAttributeValue("name", "user"));
         * System.out.println("//---------------------------");
         */
        System.out.println(doc.getElementsByAttribute("name").get(0).text());
        System.out.println(doc.getElementsByAttribute("name").get(0).html());

    }

}