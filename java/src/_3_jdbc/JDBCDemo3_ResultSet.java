package _3_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class JDBC_3_Demo1 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stsm = null;
        ResultSet res = null;//结果集也要关闭！！
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "root");
            stsm = conn.createStatement();
            String sql = "select * from student";
            res = stsm.executeQuery(sql);
            res.next();

            //仅仅实验，并非正确使用
            System.out.println("id:--"+res.getInt(1));
            System.out.println("name:--"+res.getString("name"));
        
        
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (res != null) {
                try {
                    res.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (stsm != null) {
                try {
                    stsm.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class JDBC_3_Demo2{
    public static void main(String[] args) {
        Connection conn = null;
        Statement stsm = null;
        ResultSet res = null;//结果集也要关闭！！
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "root");
            stsm = conn.createStatement();
            String sql = "select * from student";
            res = stsm.executeQuery(sql);

            //多一个判断语句
            while(res.next()){
                System.out.println("id:"+res.getInt(1));
                System.out.println("name:"+res.getString("name"));
            }
        
        
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (res != null) {
                try {
                    res.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (stsm != null) {
                try {
                    stsm.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}