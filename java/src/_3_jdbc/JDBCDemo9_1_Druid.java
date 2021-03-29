package _3_jdbc;

import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

class JDBC_9_Demo1 {
    public static void main(String[] args) throws Exception {
        // 注册驱动，加载到内存

        // 导入jar包
        // 定义配置文件

        // 加载配置文件
        Properties pro = new Properties();
        pro.load(JDBC_9_Demo1.class.getClassLoader().getResourceAsStream("druid.properties"));

        // 通过数据工厂类来获得数据连接池对象,传入Properties对象
        DataSource ds = DruidDataSourceFactory.createDataSource(pro);

        System.out.println(ds.getConnection());
    }
}