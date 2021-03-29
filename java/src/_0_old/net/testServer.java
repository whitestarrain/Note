package _0_old.net;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * _0_testServer
 */
class UserThead implements Runnable {
    private Socket s;
    String buf = null;

    UserThead(Socket s, String buf) {
        this.s = s;
        this.buf = buf;
    }

    @Override
    public void run() {
        try {
            System.out.println("接入：");
            System.out.println(s.getInetAddress().getHostAddress());
            System.out.println(s.getInetAddress().getHostName()+"\n");
            PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()), true);
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/html; charset=UTF-8");
            out.println("Connection :Keep-Alive");
            out.println();
            out.println(buf);
            out.flush();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)//保留到运行阶段
@interface filePathAnno {
    String value() default "";
}


@filePathAnno("src/_0_old/net/test.html")
class testServer {

    public static void main(String[] args) throws IOException {
        filePathAnno filepath=testServer.class.getAnnotation(filePathAnno.class);
        String file = filepath.value();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));//以UTF-8读入
        StringBuffer sb = new StringBuffer();
        String temp = null;
        ServerSocket ss = new ServerSocket(10020);
        while ((temp = br.readLine()) != null) {
            sb.append(temp);
        }
		
        String buf = new String(sb.toString().getBytes("UTF-8"),"UTF-8");//java中字符串无编码形式，对应byte有编码形式
        while (true) {
            new Thread(new UserThead(ss.accept(), buf)).run();
        }
    }
}