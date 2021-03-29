/* 警示：一个包下不能定义同类名，刚刚因为两个文件下都有Demo1导致还以为代码出错了差点崩溃 */
package _3_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

//Statement练习

class JDBC_2_Demo1 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stsm = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "root");
            stsm = conn.createStatement();
            // 1. studnet表，添加一条记录
            String sql = "INSERT INTO student(id,NAME,age,score) VALUES(7,'七',18,100.0)";//语句中不用加;
            int r1 = stsm.executeUpdate(sql);
            System.out.println(r1);
            if (r1 > 0) {
                System.out.println("执行成功");
            } else {
                System.out.println("执行失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //按照开启顺序相反得来关闭
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
        //其他只需要将sql修改一下即可

        // 2. student表，修改一条记录
        //String sql="update student set socre=101 where id=6"

        // 3. student表，删除一条记录
        //String sql="delete from student where id=7"

        //4. DDL语句执行
        //String sql="create table if not exists db3(id int)"
    }

}