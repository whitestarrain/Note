package tq;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 数据：
 * 1949-10-01 38
 * 1949-10-01 34
 * 1949-10-05 32
 * 1949-10-06 34
 * 1949-10-01 35
 *
 * @author liyu
 */
public class TReducer extends Reducer<Tq, IntWritable, Text, IntWritable> {
    Text tkey = new Text();
    IntWritable tval = new IntWritable();

    @Override
    protected void reduce(Tq key, Iterable<IntWritable> values, Context context) throws IOException,
            InterruptedException {
        // 数据都是排好序的
        // 只输出月最高气温的两天,所以设置flag和day
        int flag = 0;
        int day = 0;
        for (IntWritable value : values) {
            if (flag == 0) {
                tkey.set(key.toString());
                tval.set(value.get());
                context.write(tkey, tval);
                flag++;
                day = key.getDay();
            }
            if (flag != 0 && day != key.getDay()) {
                tkey.set(key.toString());
                tval.set(value.get());
                context.write(tkey, tval);
                // 输出两次了，所以退出
                return;
            }
        }

    }
}
