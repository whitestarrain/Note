package _3_jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * JDBCDemo9_2_Utils
 */
public class JDBCDemo9_2_Utils {
    /**
     * 加载配置文件
     */
    private static Properties pro = null;
    private static DataSource ds = null;
    /* static */ /*  *以后做笔记不开mysql */{
        pro = new Properties();
        try {
            pro.load(JDBC_9_Demo1.class.getClassLoader().getResourceAsStream("druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    /**
     * 获取连接
     * 
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        return ds.getConnection();
    }

    /**
     * 关闭资源
     */
    public static void closeSource(ResultSet rs, Statement stsm, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stsm != null) {
            try {
                stsm.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();//归还连接
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public static void closeSource(Statement stsm, Connection conn) {
        closeSource(null, stsm, conn);
    }

    /**
     * 获取连接池
     */

    public static DataSource getDataSource() {
        return ds;
    }
}

class JDBC_9_2_Test {
    public static void main(String[] args) {

    }
}