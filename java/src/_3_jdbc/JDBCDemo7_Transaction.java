package _3_jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * JDBCDemo7_Transaction
 */
class JDBC_7_Demo1 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstsm = null, pstsm2 = null;
        try {
            // 连接数据库
            conn = JDBCDemo5_Utils.gConnection();
            // sql语句定义
            String sql1 = "update student set score=1 where id =1";
            String sql2 = "update student set score=2 where id =2";
            // 开启事件
            conn.setAutoCommit(false);
            // 获得执行SQL对象
            pstsm = conn.prepareStatement(sql1);
            pstsm2 = conn.prepareStatement(sql2);

            // 执行第一条
            pstsm.executeUpdate();

            // 此处为错误中断
            int x = 1 / 0;

            // 执行第二条
            pstsm2.executeUpdate();

            // 提交
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // 回滚
            try {
                if (conn != null)//避免为null
                    conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            JDBCDemo5_Utils.close(pstsm, conn);
        }
    }
}