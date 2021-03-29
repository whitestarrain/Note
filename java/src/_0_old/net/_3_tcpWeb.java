package _0_old.net;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * _3_tcpWeb
 */
class _3_tcpWeb {
    public static void main(String[] args) throws Exception {
        while (true) {

            ServerSocket ss = new ServerSocket(10010);
            System.out.println("1");
            Socket s = ss.accept();
            System.out.println(s.getInetAddress().getHostAddress());
            PrintWriter printWriter = new PrintWriter(s.getOutputStream(), true);
            printWriter.println("Http:/1.1 200 \\t\\n <div style=\"color:red;height:100px;width:100px;\">你好</div>");
            printWriter.flush();
            s.close();
            ss.close();
        }
    }

}