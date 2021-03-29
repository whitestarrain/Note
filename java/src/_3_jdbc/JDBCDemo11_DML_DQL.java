package _3_jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * 学生类
 */
class JDBC_11_Student {
    // 最好不要使用基本数据类型，无法接收null
    private Integer id;
    private String name;
    private Integer age;
    private Double score;
    private Date birthday;
    private Date inserttime;

    public JDBC_11_Student() {

    }

    public JDBC_11_Student(int id, String name, int age, double score, Date birthday, Date inserttime) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.birthday = birthday;
        this.inserttime = inserttime;
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

    public Date ginserttime() {
        return inserttime;
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

    public void setjoindate(Date inserttime) {
        this.inserttime = inserttime;
    }

    @Override
    public String toString() {
        return id.toString() + "  " + name + "   " + score.toString() + "   ";
    }
}

/**
 * 测试类
 */

public class JDBCDemo11_DML_DQL {
    private JdbcTemplate template = new JdbcTemplate(JDBCDemo9_2_Utils.getDataSource());

    /**
     * 修改 id 为 1 的 student 分数为 111
     */
    @Test
    public void test1() {
        // 1.获取JdbcTemplate对象
        // 2. 定义sql
        String sql = "update student set score=111 where id=1";
        // 3.执行sql
        int count = template.update(sql);
        System.out.println(count);
    }

    /**
     * 添加一条记录
     */
    @Test
    public void test2() {
        String sql = "insert into student values(null,'八',19,818,null,null)";
        System.out.println(template.update(sql));
    }

    /**
     * 删除新的那条记录
     */
    @Test
    public void test3() {
        String sql = "delete from student where id =8";
        int count = template.update(sql);
        System.out.println(count);
    }

    /**
     * 查询 id 为 1 的account中的记录封装为 map 集合
     */
    @Test
    public void test4() {
        String sql = "select * from account where id=?";
        Map<String, Object> map = template.queryForMap(sql, 1);
        System.out.println(map);
        // 结果：{id=1, username=111, password=111}
        // 字段名当作key，字段值当作value，封装Map。
    }

    /**
     * 查询所有记录封装为 list 集合
     */
    @Test
    public void test5() {
        String sql = "select * from account";
        List<Map<String, Object>> list = template.queryForList(sql);
        System.out.println(list);
        // 结果：[{id=1, username=111, password=111}, {id=2, username=222, password=222}]
        // 将每一条记录封装为map集合，再将map集合封装到list中
    }

    /**
     * 查询所有记录，将其封装为 JDBC_11_Student 对象的 List 集合（较常见）
     */
    @Test
    public void test6_1() {
        String sql = "select * from student";
        List<JDBC_11_Student> list = template.query(sql, new RowMapper<JDBC_11_Student>() {
            @Override
            public JDBC_11_Student mapRow(ResultSet arg0, int arg1) throws SQLException {

                // RowMapper接口每调用一次返回一个JDBC_11_Student对象
                JDBC_11_Student temp = new JDBC_11_Student(arg0.getInt("id"), arg0.getString("name"),
                        arg0.getInt("age"), arg0.getDouble("score"), arg0.getDate("birthday"),
                        arg0.getDate("inserttime"));
                return temp;
            }
        });

        System.out.println(list);
    }

    /**
     * 查询所有记录，将其封装为 JDBC_11_Student 对象的 List 集合（较常见）
     * 通过Spring中的BeanPropertyRowMapper，而不自己去重写mapRow方法。
     */
    @Test
    public void test6_2() {
        String sql = "select * from student";
        List<JDBC_11_Student> list = template.query(sql,
                new BeanPropertyRowMapper<JDBC_11_Student>(JDBC_11_Student.class));
        list.stream().forEach(temp -> System.out.println(temp));
        // 疑问：inserttime为何为null
    }

    /**
     *  查询总记录数
     */
    @Test
    public void test7() {
        String sql="select count(id) from student";
        Long total=template.queryForObject(sql,long.class);
        System.out.println(total);
    }
}