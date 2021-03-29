package tq;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author liyu
 * 2000-04-12 12:13:13 34c
 */
public class TMapper extends Mapper<LongWritable, Text, Tq, IntWritable> {
    Tq tkey = new Tq();
    IntWritable tvalue = new IntWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] words = StringUtils.split(value.toString(), '\t');
        // 切成：
        // 2000-04-12 12:13:13
        // 34c

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // 处理日期
            Date date = sdf.parse(words[0]);
            // date方法获取年月日等已经过时了，通
            // 过Calendar获取，可以获取不同时区，格林威治时间等
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            tkey.setYear(cal.get(Calendar.YEAR));
            tkey.setMonth(cal.get(Calendar.MONTH) + 1);
            tkey.setDay(cal.get(Calendar.DAY_OF_MONTH));

            // 设置温度
            int wd = Integer.parseInt(words[1].substring(0, words[1].lastIndexOf("c")));
            tkey.setWd(wd);

            tvalue.set(wd);

            context.write(tkey, tvalue);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
