package fd;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author liyu
 */
public class FReducer extends Reducer<Text, IntWritable,Text,IntWritable> {
    IntWritable tval = new IntWritable();
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException,
            InterruptedException {
        // tom hello 1
        // tom hello 1
        // tom hello 0
        // tom hello 1
        int sum = 0;
        for (IntWritable value : values) {
            if(value.get()==0){
                return;
            }
            sum++;
        }
        context.write(key, tval);
    }
}
