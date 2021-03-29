package tq;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * 实现天气年月正序
 *
 * @author liyu
 */
public class TSortComparator extends WritableComparator {
    Tq t1 = null;
    Tq t2 = null;
    // 这两个写到compare()中也行，这里实在仿照WritableComparator 中key1，key2的写法

    public TSortComparator(){
        // 调用父类构造器
        super(Tq.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        // T1 实现了WritableComparable 接口
        t1 = (Tq) a;
        t2 = (Tq) b;

        //  源码中默认：   return t1.compareTo(t2);
        // 但此处要涉及温度，所以要重写


        int c1 = Integer.compare(t1.getYear(), t2.getYear());
        if (c1 == 0) {
            int c2 = Integer.compare(t1.getMonth(), t2.getMonth());
            if (c2 == 0) {
                // 当年月相同时，按照温度排序，而不日期
                return Integer.compare(t1.getWd(), t2.getWd());
            }
            return c2;
        }
        return c1;
    }


}
