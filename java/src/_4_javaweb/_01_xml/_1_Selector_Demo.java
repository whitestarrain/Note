package _4_javaweb._01_xml;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * _1_Selector_Demo
 */
class _1_Selector_Demo {
    public static void main(String[] args) throws IOException {
        String path = _1_Selector_Demo.class.getClassLoader().getResource("c3p0-config.xml").getPath();
        path = path.replace("%20", " ");
        Document doc = Jsoup.parse(new File(path), "utf-8");
        Elements elements = doc.getElementsByTag("property");
        System.out.println(doc.select("property[name=\"driverClass\"]"));
    }

}