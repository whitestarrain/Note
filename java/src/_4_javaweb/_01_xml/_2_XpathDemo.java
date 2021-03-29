package _4_javaweb._01_xml;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;

/**
 * _2_XpathDemo
 */
class _2_XpathDemo {
    public static void main(String[] args) throws IOException, XpathSyntaxErrorException {
        String path = _0_Jsoup_Document_Demo.class.getClassLoader().getResource("c3p0-config.xml").getPath();
        path = path.replace("%20", " ");
        Document doc = Jsoup.parse(new File(path), "utf-8");

        // 根据Document对象，创建JXDocument对象
        JXDocument jxd = new JXDocument(doc);
        // 调用jxd的sel和selN方法。返回值类型不同，一个用JXNode，一个用Object收，都是用List装
        List<JXNode> selN = jxd.selN("//property");
        for (JXNode temp : selN) {
            System.out.println(temp);
        }
    }

}