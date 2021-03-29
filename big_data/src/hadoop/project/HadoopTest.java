import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import sun.nio.ch.IOUtil;

import java.io.IOException;

/**
 * @author liyu
 */
public class HadoopTest {
    public static void main(String[] args) throws IOException {
        Configuration conf = null;
        conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
    }
}
