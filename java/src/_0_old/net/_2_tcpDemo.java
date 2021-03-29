package _0_old.net;
/**
 * *多客户并发登陆
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

class TcpClient {
    public static void main(String[] args) {
        Socket s = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        try {
            s = new Socket(InetAddress.getLocalHost(), 10011);
            br = new BufferedReader(new InputStreamReader(System.in));
            pw = new PrintWriter(s.getOutputStream(), true);
            BufferedReader brin = new BufferedReader(new InputStreamReader(s.getInputStream()));
            for (int i = 0; i < 3; i++) {
                System.out.println("请输入内容");
                String str = br.readLine();
                if (str == null) {
                    System.out.println("请务必输入内容");
                    continue;
                }
                pw.println(str);
                String readLine = brin.readLine();
                System.out.println(readLine);
                if (readLine.contains("欢迎")) {
                    break;
                }

            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (s != null)
                    s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class UserThread implements Runnable {
    private Socket s;
    private ArrayList<String> sarr = new ArrayList<String>();// 模拟数据库而已

    UserThread(Socket s) {
        this.s = s;
        sarr.add("111");
        sarr.add("222");
    }

    @Override
    public void run() {
        BufferedReader br = null;
        PrintWriter out = null;
        String str = null;
        try {
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream(), true);
            for (int i = 0; i < 3; i++) {
                str = br.readLine();
                if (str == null) {
                    break;
                }
                boolean flag = sarr.contains(str);
                if (flag) {
                    System.out.println(s.getInetAddress().getHostAddress() + "已经连接");
                    out.println("欢迎登录");
                    return;
                } else {
                    System.out.println(s.getInetAddress().getHostAddress() + "尝试连接");
                    out.println("该用户不存在");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("校验失败");
        }
    }
}

class TcpServer {
    public static void main(String[] args) {
        ServerSocket ss;
        try {
            ss = new ServerSocket(10011);
            while (true) {
                Socket s = ss.accept();
                new Thread(new UserThread(s)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}