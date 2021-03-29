package _0_old.net;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * * 关于url封装类 uri代表的范围要比url大，相关区别看pdf
 * 
 */
// *获取信息方法
// String getAuthority()
// 获取此 URL 的授权部分。
// Object getContent()
// 获取此 URL 的内容。
// Object getContent(Class[] classes)
// 获取此 URL 的内容。
// int getDefaultPort()
// 获取与此 URL 关联协议的默认端口号。
// String getFile()
// 获取此 URL 的文件名。
// String getHost()
// 获取此 URL 的主机名（如果适用）。
// String getPath()
// 获取此 URL 的路径部分。
// int getPort()
// 获取此 URL 的端口号。
// String getProtocol()
// 获取此 URL 的协议名称。
// String getQuery()
// 获取此 URL 的查询部分。
// String getRef()
// 获取此 URL 的锚点（也称为“引用”）。
// String getUserInfo()
// 获取此 URL 的 userInfo 部分。

// *其他重要对象URLConnection
// URLConnection openConnection()
// 返回一个 URLConnection
// 对象（URLConnection是接口，返回的实际对象是实现它的一个子类HttpURLConnection），它表示到 URL 所引用的远程对象的连接。
// socket在传输层，而HttpUrlConnection在应用层。该对象是在应用层，内部已经进行连接了。并且没有断开连接的方法。（内部自己断？）
// *
// 内部方法:getinputStream():（并非socket的getInputStream（展示所有数据），获取的打印流用来可获取http/https协议数据并且会自动去消息头）
// *作为代替，可以用方法来获取消息头的值
// getContentEncoding()
// 返回 content-encoding 头字段的值。
// int getContentLength()
// 返回 content-length 头字段的值。
// String getContentType()
// 返回 content-type 头字段的值。
// long getDate()
// 返回 date 头字段的值

// * URL对象的方法：openStream():其实就是 openConnection.getInputStream
class _6_URL {
    public static void main(String[] args) throws IOException {/* 记住异常名 */
        URL url = new URL("http://youtu.be/SD7gpoww6ag?name=haha&age=30#emp");
        System.out.println("protocol:" + url.getProtocol());
        System.out.println("host:" + url.getHost());
        System.out.println("path:" + url.getPath());// 没有指定端口时返回-1。而不用写prot时，是默认port都是80
        System.out.println("File:" + url.getFile());
        System.out.println("port:" + url.getPort());
        System.out.println("query:" + url.getQuery());// 用的频率非常高
        System.out.println("锚点：" + url.getRef());

        System.out.println();
        System.out.println("-------------------------");
        URL baidu = new URL("https://www.baidu.com/");
        URLConnection conn = baidu.openConnection();
        // *此处连接时也会把请求头封装发送，不用像socket那样自己发
        System.out.println(conn);
        byte[] buf = new byte[1024];
        int len = conn.getInputStream().read(buf);
        System.out.println(new String(buf, 0, len));

        // * 传输层协议（socket）会返回所有信息，而向上时会根据应用层协议对数据进行拆包（会去除消息头）
        // * 此处会返回网页数据，没有消息头
    }

}