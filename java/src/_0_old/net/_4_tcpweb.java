package _0_old.net;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class ServerDemo {

    public static void main(String[] args) throws Exception {

        while (true) {

            ServerSocket ss = new ServerSocket(11000);
            Socket s = ss.accept();
            System.out.println(s.getInetAddress().getHostAddress());
            InputStream in = s.getInputStream();
            byte[] buf = new byte[1024];
            int len = in.read(buf);
            System.out.println(new String(buf, 0, len));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            StringBuffer result = new StringBuffer();
            result.append("HTTP/1.1 200 OK\n");
            result.append("Content-Type: text/html; charset=UTF-8\n\n");
            result.append(
                    "<html><head><title>test</title></head><body><font color='red'size='7'>客户端你好</font></body></html>");
            out.println(result.toString());
            s.close();
            ss.close();
        }
    }
}

/**
 * * http请求消息头。
 * * 当浏览器访问服务端时发送的东西
 * 以后有表单时还会有请求数据体
 * 两者之间要用空行隔开
 * ! 就算下面没有请求数据体也要加空格
 * 
 * 下面中文写的都是笔记
 */
// GET /（此处为资源文件路径，根据浏览器网址栏上的资源路径返回，此处没写就是/） HTTP/1.1
// Host: 192.168.20.1:11000（指定主机。一个服务器主机不一定就一个）
// Connection: keep-alive
// Upgrade-Insecure-Requests: 1
// User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36
// (KHTML, like Gecko) Chrome/80.0.3987.122 Safari/537.36
// Accept:
// text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*（代表支持所有资源文件，前面的只是写出来看的）;q=0.8,application/signed-exchange;v=b3;q=0.9
// Accept-Encoding: gzip（支持压缩数据方式）, deflate
// Accept-Language: zh-CN,zh;q=0.9,ja;q=0.8
