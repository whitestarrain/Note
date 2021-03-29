package fd;

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
public class MyFD {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = null;
        conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(MyFD.class);

        job.setJobName("friend");

        Path inputPath = new Path("/fd/input");
        FileInputFormat.addInputPath(job,inputPath);

        Path outpath = new Path("/fd/output");
        FileOutputFormat.setOutputPath(job,outpath);

        if(outpath.getFileSystem(conf).exists(outpath)){
            outpath.getFileSystem(conf).delete(outpath);
        }

        job.setMapperClass(FMapper.class);

        // 不指明的话会出类型不匹配异常
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setReducerClass(FReducer.class);

        job.waitForCompletion(true);
    }
}
