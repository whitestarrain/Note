package _3_jdbc;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * JDBCDemo10_JdbcTemplate
 */
class JDBC_10_Demo1 {
    public static void main(String[] args) {
        // 1.导入jar包
        // 2.创建JdbcTemplate对象
        DataSource ds = JDBCDemo9_2_Utils.getDataSource();
        JdbcTemplate template = new JdbcTemplate(ds);

        // 3.调用方法
        String sql = "update student set score=121 where id =?";
        int count = template.update(sql, 1);// 最后几个参数和?一一对应,返回影响行数
        // 申请连接和释放资源都在框架内部自己做的
        // 前两行是日志

        System.out.println(count);
    }

}