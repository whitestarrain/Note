/* 代码重复度很多（连接，关闭资源等） */
/* 此处抽取一个jdbc工具类 */
package _3_jdbc;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCDemo5_Utils {
    private static String url = null;
    private static String user = null;
    private static String password = null;
    private static String driver = null;
    /* static */ {
        try {
            Properties pro = new Properties();
            // 读取配置文件


            /*
            // 获取  **src**  路径下文件的方式：ClassLoder
            ClassLoader cl = JDBCDemo5_Utils.class.getClassLoader();
            URL res=cl.getResource("JDBC.properties");//统一资源标识符，包含绝对路径
            String path=res.getPath();
            //URL ClassLoder等在javaweb中会仔细讲
            pro.load(new FileReader(path));
            */
            //因为绝对路径中有空格，导致上面的运行失败

            pro.load(new FileReader("src/JDBC.properties"));


            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");

            // 加载驱动
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection gConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void close(Statement stsm, Connection conn) {
        if (stsm != null) {
            try {
                stsm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

class tools_test {
    public static void main(String[] args) throws Exception {
        Connection conn = JDBCDemo5_Utils.gConnection();
        Statement stsm = conn.createStatement();
        String sql = "select * from student";
        ResultSet rs = stsm.executeQuery(sql);
        rs.next();
        System.out.println(rs.getInt("id"));
        JDBCDemo5_Utils.close(stsm, conn);
    }
}