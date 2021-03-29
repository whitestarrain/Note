package tq;

import org.apache.hadoop.io.RawComparator;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * @author liyu
 */
public class TGroupingComparator extends WritableComparator {

    Tq t1 = null;
    Tq t2 = null;
    // 这两个写到compare()中也行，这里实在仿照WritableComparator 中key1，key2的写法

    public TGroupingComparator() {
        // 调用父类构造器
        super(Tq.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {

        t1 = (Tq) a;
        t2 = (Tq) b;

        int c1 = Integer.compare(t1.getYear(), t2.getYear());
        if (c1 == 0) {
            return Integer.compare(t1.getWd(), t2.getWd());
        }
        return c1;
    }


}
