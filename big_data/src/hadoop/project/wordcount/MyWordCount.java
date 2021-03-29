package wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @author liyu
 */
public class MyWordCount {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance();

        job.setJarByClass(MyWordCount.class);

        job.setJobName("MyWordCount");

        // hdfs目录
        Path inPath = new Path("/user/root/test.txt");
        Path outPath = new Path("/output/wordcount");

        FileInputFormat.addInputPath(job,inPath);

        // 如果输出路径存在，就删除
        if(outPath.getFileSystem(conf).exists(outPath)){
            outPath.getFileSystem(conf).delete(outPath,true);
        }

        // 设置输出
        FileOutputFormat.setOutputPath(job,outPath);
        // 手动创建 MyMapper.class,MyReducer.class
        job.setMapperClass(MyMapper.class);
        // 告知reduce，map输出的数据类型，否则reduce无法反序列化，会报错
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setReducerClass(MyReducer.class);

        // Submit the job, then poll for progress until the job is complete
        job.waitForCompletion(true);
    }
}
