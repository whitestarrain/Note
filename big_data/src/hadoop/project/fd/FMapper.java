package fd;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;

import java.io.IOException;

/**
 * @author liyu
 */
public class FMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    Text tkey = new Text();
    IntWritable tval = new IntWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] words = StringUtils.split(value.toString(), ' ');
        // 第一个元素和后面所有元素进行匹配，为直接好友关系
        for (int i = 1; i < words.length; i++) {
            // 解决顺序不同非一组key的问题
            tkey.set(getKey(words[0], words[i]));

            // 直接好友，输出0
            tval.set(0);
            context.write(tkey, tval);

            // 后面所有的人暂且都以间接关系算
            for (int j = i + 1; j < words.length; j++) {
                tkey.set(getKey(words[i], words[j]));
                tval.set(1);
                context.write(tkey, tval);
            }
        }

    }

    private String getKey(String word, String word1) {
        return word.compareTo(word1) > 0 ? word : word1;
    }
}
