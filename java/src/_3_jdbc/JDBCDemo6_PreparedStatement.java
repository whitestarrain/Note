package _3_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class JDBC_6_Demo1 {
    public static boolean ishasAccount(String username, String password) {
        Connection conn = null;
        Statement stsm = null;
        ResultSet rs = null;
        String sql = "select * from account where username='" + username + "' and password='" + password + "'";
        // 此处加不加单引号都可以正确执行
        System.out.println(sql);
        try {
            conn = JDBCDemo5_Utils.gConnection();
            stsm = conn.createStatement();
            rs = stsm.executeQuery(sql);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            JDBCDemo5_Utils.close(stsm, conn);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = scan.nextLine();
        System.out.println("请输入密码");
        String password = scan.nextLine();
        if (ishasAccount(username, password)) {
            System.out.println("登陆成功");
        } else {
            System.out.println("登录失败");
        }
    }
}


/**
 * 动态/预编译sql
 */

class JDBC_6_Demo2 {
    public static boolean ishasAccount(String username, String password) {
        Connection conn = null;
        PreparedStatement pstsm = null;
        ResultSet rs = null;
        String sql = "select * from account where username=? and password=?";
        //通过？作为通配符来解决JDBC注入问题
        try {
            conn = JDBCDemo5_Utils.gConnection();
            pstsm = conn.prepareStatement(sql);
            pstsm.setString(1, username);
            pstsm.setString(2, password);
            rs = pstsm.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            JDBCDemo5_Utils.close(pstsm, conn);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = scan.nextLine();
        System.out.println("请输入密码");
        String password = scan.nextLine();
        if (ishasAccount(username, password)) {
            System.out.println("登陆成功");
        } else {
            System.out.println("登录失败");
        }
    }
}