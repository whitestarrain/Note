package tq;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @author liyu
 */
public class TPartitioner extends Partitioner<Tq, IntWritable> {
    /**
     * 根据 key和value获取分区id
     * @param tq
     * @param intWritable
     * @param numPartitions
     * @return
     */
    @Override
    public int getPartition(Tq tq, IntWritable intWritable, int numPartitions) {
        // numPartitions 默认为1，job中可以设置
        // 此处只是演示，可以写得复杂些，但要保证逻辑正确
        return tq.getYear()%numPartitions;
    }
}
