package study.SynchronizedLeaen;

import org.openjdk.jol.info.ClassLayout;

public class Study {

    public static void main(String[] args) {
        int i = 1;
        int a[] = {1,2};

        Demo demo = new Demo();
        System.out.println(Integer.toHexString(demo.hashCode()) );
        System.out.println(ClassLayout.parseInstance(demo).toPrintable());
    }
}
