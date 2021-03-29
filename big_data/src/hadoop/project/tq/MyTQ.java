package tq;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author liyu
 */
public class MyTQ {

    public static void main(String[] args) throws IOException {
        // 1,配置
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(MyTQ.class);

        // 2.设置输入输出路径
        Path inPath = new Path("/tq/input");
        FileInputFormat.addInputPath(job, inPath);
        Path outPath = new Path("/tq/output");
        if (outPath.getFileSystem(conf).exists(outPath)) {
            outPath.getFileSystem(conf).delete(outPath, true);
        }
        FileOutputFormat.setOutputPath(job, outPath);

        // 3.设置mapper
        // 3.1 实现天气类
        // 3.2 实现TMapper
        // 3.3配置
        job.setMapperClass(TMapper.class);
        job.setMapOutputKeyClass(Tq.class);
        job.setMapOutputValueClass(IntWritable.class);

        // 4. 自定义比较器，在map端进行排序
        // 4.1实现TSortComparator
        // 4.2配置
        job.setSortComparatorClass(TSortComparator.class);

        // 设置reduce数量
        job.setNumReduceTasks(2);

        // 5 设置分区器,定义数据分发策略，默认hash取模（看源码）
        // 单分区的话不需要设置
        // 5.1 自定义分区器
        // 5.2 配置
        job.setPartitionerClass(TPartitioner.class);

        // 6 设置组比较器（reduce端那个）
        // 6.1 自定义组比较器
        // 6.2 配置
        job.setGroupingComparatorClass(TGroupingComparator.class);

        // 7 设置reducer
        job.setReducerClass(TReducer.class);

    }
}
