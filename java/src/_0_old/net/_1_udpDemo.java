package _0_old.net;
/**
 * * udp练习
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

class SendDemo implements Runnable {
    private DatagramSocket ds;

    public SendDemo(DatagramSocket ds) {
        this.ds = ds;
    }

    @Override
    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        try {
            while ((line = br.readLine()) != null) {
                if (line.equals("over")) {
                    System.exit(0);
                }
                DatagramPacket datagramPacket = new DatagramPacket(line.getBytes(), line.getBytes().length,
                        InetAddress.getLocalHost(), 10007);
                ds.send(datagramPacket);

            }
        } catch (IOException e) {
            System.out.println("发送端异常");
            e.printStackTrace();
        }
    }
}

class ReceiveDemo implements Runnable {
    private DatagramSocket ds;

    public ReceiveDemo(DatagramSocket ds) {
        this.ds = ds;
    }

    @Override
    public void run() {
        while (true) {
            DatagramPacket dp = new DatagramPacket(new byte[1024], new byte[1024].length);
            try {
                ds.receive(dp);
            } catch (IOException e) {
                System.out.println("接收端异常");
                e.printStackTrace();
            }
            String ip = dp.getAddress().getHostAddress();
            String data = new String(dp.getData(), 0, dp.getLength());
            System.out.println(ip + "::" + data);

        }
    }
}

class ChatDemo {
    public static void main(String[] args) throws SocketException {
        DatagramSocket ds1 = new DatagramSocket();
        DatagramSocket ds2 = new DatagramSocket(10007);
        new Thread(new SendDemo(ds1)).start();
        new Thread(new ReceiveDemo(ds2)).start();
    }
}