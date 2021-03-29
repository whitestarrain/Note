import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * test
 */
public class test {

    public static void main(String[] args) throws UnknownHostException {
        InetAddress inet = InetAddress.getByName("www.baidu.com");
        System.out.println(inet.getHostAddress());
        System.out.println(inet.getHostName());
    }
}