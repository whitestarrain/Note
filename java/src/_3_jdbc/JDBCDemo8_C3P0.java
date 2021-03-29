package _3_jdbc;

import java.sql.Connection;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

class JDBC_8_Demo1 {
    public static void main(String[] args) throws Exception {
        // 注册驱动，加载到内存自动进行，这里没有多写

        // 1 创建数据库连接池对象
        DataSource ds = new ComboPooledDataSource();
        //此处什么也不传，默认使用c3p0-config.xml配置文件中的default-config
        //也可以传入String类型参数来指定设置的名字，比如"accountc3p0""
        //但将来也基本只会用默认设置

        // 2 获取连接对象
        Connection conn = ds.getConnection();

        // 3 打印
        // System.out.println(conn);
        // 会打印日志信息

        //将10个连接逐个打印
        for (int i = 0; i < 10; i++) {
            System.out.println(i + ":" + conn);
            if(i==5){
                conn.close();//归还道数据连接池中
            }
            conn = ds.getConnection();
        }
    }
}