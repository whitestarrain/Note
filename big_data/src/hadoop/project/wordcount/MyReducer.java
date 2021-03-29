package wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.util.bloom.Key;

import java.io.IOException;
import java.util.Iterator;

/**
 * @author liyu
 * keyIn:从map端来，为Text
 * ValueIn:word计数，为IntWritable
 * keyOut:输出到文件的类型，单词本身，Text
 * valueOut:输出到文件的类型，单词的出现次数，IntWritable
 */
public class MyReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    private IntWritable result = new IntWritable();

    // 这里是 key 和 values ，也就是一个key下多个value。和reduce同一个key为一组，计算一次相符合。
    // 每个key执行一遍该方法，进行一次迭代
    @Override
    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable val : values) {
            // 这里的val都为1，进行累加运算
            sum += val.get();
        }
        result.set(sum);
        context.write(key, result);

        // 另一种写法，具体查看源码
//        int sum = 0;
//        Iterator<IntWritable> iterator = values.iterator();
//        while (iterator.hasNext()) {
//            IntWritable next = iterator.next();
//            sum += next.get();
//            result.set(sum);
//            context.write(key, result);
//        ｝
    }
}
