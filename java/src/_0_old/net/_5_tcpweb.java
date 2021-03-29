package _0_old.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * * 模仿浏览器向服务器发送请求消息头
 */

class myIe {

    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 8080);
        /**
         * ! 别忘了加true自动刷新啊啊啊啊啊啊，好几次了。这次费了一个钟头
         */
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        out.println("GET /snake/index.html HTTP/1.1");
        out.println("Accept: */*");
        out.println("Accept-Language: zh-cn");
        out.println("Host: localhost:8080");
        out.println("Connection :Keep-Alive");
        out.println();
        out.println();
        BufferedReader bufr = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String line = null;
        while ((line = bufr.readLine()) != null) {
            System.out.println(line);
        }
        s.close();//不关闭连接的话标签页上面会一直有个加载的圈圈在转
    }
}
/**
 * * http应答消息头。 * 浏览器访问服务器，服务器返回应答消息头以及数据
 * 看看格式吧
 * 
HTTP/1.1 200
Accept-Ranges: bytes
ETag: W/"466-1582106101553"
Last-Modified: Wed, 19 Feb 2020 09:55:01 GMT
Content-Type: text/html
Content-Length: 466
Date: Mon, 02 Mar 2020 14:21:37 GMT

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Document</title>
        <link rel="stylesheet" href="css/style.css" />
    </head>
    <body>
        <div id="container"></div>
        <!-- 要按顺序引入 -->
        <script src="./js/base.js"></script>
        <script src="./js/main.js"></script>

    </body>
</html>
 */