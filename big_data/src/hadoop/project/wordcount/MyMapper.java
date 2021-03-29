package wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * @author liyu
 * Mapper泛型  keyIn valueIN keyOut valueOut，输入输出都是k-v类型
 * 默认keyIn:一行首字符的下标索引 valueIn:一行的内容
 * 注意：不支持基本类型，String类型用Text代替，int用IntWritable代替
 */
public class MyMapper extends Mapper<Object, Text, Text, IntWritable> {
    private final static IntWritable one = new IntWritable(1);
    // 每次计数为1
    private Text word = new Text();

    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        // 每行执行一次map方法
        StringTokenizer itr = new StringTokenizer(value.toString());
        // 将字符串放到迭代器中
        // 通过迭代器对字符串进行切割
        while (itr.hasMoreTokens()) {
            // itr.nextToken() 返回String，此处将String封装到Text中
            word.set(itr.nextToken());
            context.write(word, one);
            // 将单词装到context中，每次计数为1
            // word 对应keyOut,one对应 valueOut
            // 最后输出形式：
            // hello 1
            // hadoop 1
            // hello 1
            // hello 1
            // ...
        }
    }
}
