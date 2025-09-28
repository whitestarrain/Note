package _3_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.Consumer;
import java.util.stream.Stream;

/* 
查询Student表数据，将其封装为对象，并打印
 */
class Demo3_4_Student {
    private int id;
    private String name;
    private int age;
    private double score;
    private Date birthday;
    private Date joindate;

    Demo3_4_Student(int id, String name, int age, double score, Date birthday, Date joindate) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.birthday = birthday;
        this.joindate = joindate;
    }

    public int gid() {
        return id;
    }

    public String gString() {
        return name;
    }

    public int gage() {
        return age;
    }

    public double gscore() {
        return score;
    }

    public Date gbirthdDate() {
        return birthday;
    }

    public Date gjoindate() {
        return joindate;
    }

    public void setid(int id) {
        this.id = id;
    }

    public void setname(String name) {
        this.name = name;
    }

    public void setage(int age) {
        this.age = age;
    }

    public void setscore(double score) {
        this.score = score;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setjoindate(Date joindate) {
        this.joindate = joindate;
    }

    @Override
    public String toString() {
        return id + "-" + name + "-" + age + "-" + score + joindate.toString();
    }
}

class JDBC_4_Demo1 {
    public static ArrayList<Demo3_4_Student> getall() {
        ArrayList<Demo3_4_Student> arrstu = new ArrayList<Demo3_4_Student>();
        Connection conn = null;
        Statement stsm = null;
        ResultSet rs = null;
        try {
            // 1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2.获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "root");
            // 3. 获得执行对象
            stsm = conn.createStatement();
            // 4. 定义sql
            String sql = "select * from student";
            // 5. 获得结果集
            rs = stsm.executeQuery(sql);
            // 6. 遍历结果集
            while (rs.next()) {
                // 获取数据
                arrstu.add(new Demo3_4_Student(rs.getInt("id"), rs.getString("name"), rs.getInt("age"),
                        rs.getDouble("score"), rs.getDate("birthday"), rs.getDate("inserttime")));
                // java.util.Date是java.sql.Date的父类，所以这里可以直接接收
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
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
        return arrstu;
    }

    public static void main(String[] args) {
        ArrayList<Demo3_4_Student> arrstu = getall();
        // 稍微复习下函数式编程
        Consumer<Demo3_4_Student> consumer = stu -> System.out.println(stu);
        for (Demo3_4_Student temp : arrstu) {
            consumer.accept(temp);
        }

        //稍微复习下Stream流
        Stream<Demo3_4_Student> stream=arrstu.stream();
        stream.forEach(stu->System.out.println(stu));
    }
}