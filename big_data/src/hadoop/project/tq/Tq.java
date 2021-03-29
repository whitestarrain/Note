package tq;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author liyu
 */
public class Tq implements WritableComparable<Tq> {

    private int year;
    private int month;
    private int day;
    private int wd;

    @Override
    public String toString() {
        return year+"-"+month+"-"+day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getWd() {
        return wd;
    }

    public void setWd(int wd) {
        this.wd = wd;
    }

    /**
     * 默认排序
     * @param tq
     * @return
     */
    @Override
    public int compareTo(Tq tq) {
        int cy = Integer.compare(this.getYear(), tq.getYear());
        int cm = 0;
        if (cy == 0) {
            cm = Integer.compare(this.getMonth(), tq.getMonth());
        } else {
            return cy;
        }
        int cd = 0;
        if (cm == 0) {
            cd = Integer.compare(this.getDay(), tq.getDay());
        } else {
            return cm;
        }
        return cd;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        // 序列化
        // 写和读顺序要相同
        // 否则会有 end of file 异常
        out.writeInt(this.getYear());
        out.writeInt(this.getMonth());
        out.writeInt(this.getDay());
        out.writeInt(this.getWd());
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        // 反序列化
        // 写和读顺序要相同
        // 否则会有 end of file 异常
        this.setYear(in.readInt());
        this.setMonth(in.readInt());
        this.setDay(in.readInt());
        this.setWd(in.readInt());
    }
}
