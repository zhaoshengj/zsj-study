package study.SynchronizedLearn;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.atomic.AtomicInteger;

public class Study {

    public static void main(String[] args) {
        int i = 1;
        int a[] = {1,2};

        Demo demo = new Demo();
        System.out.println(Integer.toHexString(demo.hashCode()) );
        System.out.println(ClassLayout.parseInstance(demo).toPrintable());
    }
}
