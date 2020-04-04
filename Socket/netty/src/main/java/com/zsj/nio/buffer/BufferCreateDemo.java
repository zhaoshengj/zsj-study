package com.zsj.nio.buffer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.*;

public class BufferCreateDemo {

    public static void main(String[] args) {
        //方式1：allocate方式直接分配，内部将隐含的创建一个数组
        //ByteBuffer allocate = ByteBuffer.allocate(10);
        //方式2：通过wrap根据一个已有的数组创建
        //byte[] bytes=new byte[10];
        //ByteBuffer wrap = ByteBuffer.wrap(bytes);
        //方式3：通过wrap根据一个已有的数组指定区间创建
        //ByteBuffer wrapoffset = ByteBuffer.wrap(bytes,2,5);

        //打印出刚刚创建的缓冲区的相关信息
       // print(allocate,wrap,wrapoffset);

        ByteBuffer buffer = ByteBuffer.allocate(20);
        byte H=0x48;
        byte e=0x65;
        byte l=0x6C;
        byte o=0x6F;
        buffer.put(H).put(e).put(l).put(l).put(o);
        print(buffer);
        buffer.flip();
        print(buffer);
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        System.out.println(new String(bytes));
        print(buffer);
        buffer.clear();
        print(buffer);
    }

    private static void print(Buffer... buffers) {
        for (Buffer buffer : buffers) {
            System.out.println("capacity="+buffer.capacity()
                    +",limit="+buffer.limit()
                    +",position="+buffer.position()
                    +",hasRemaining:"+buffer.hasArray()
                    +",remaining="+buffer.remaining()
                    +",hasArray="+buffer.hasArray()
                    +",isReadOnly="+buffer.isReadOnly()
                    +",arrayOffset="+buffer.arrayOffset());
        }
    }



    public void channel() throws IOException {

        SocketChannel sc = SocketChannel.open();
        sc.connect (new InetSocketAddress("localhost", 10));
        sc.configureBlocking (false); // nonblocking
        if (!sc.isBlocking()) {
        }
        sc.finishConnect();

        ServerSocketChannel ssc = ServerSocketChannel.open( );
        ssc.socket().bind (new InetSocketAddress (21));
        ssc.register(sc,SelectionKey.OP_READ)
        SocketChannel accept = ssc.accept();
        accept.configureBlocking(false);
        accept.shutdownOutput();
        accept.close();

        DatagramChannel dc = DatagramChannel.open();
        dc.connect(new InetSocketAddress("localhost",10));

        RandomAccessFile raf = new RandomAccessFile("somefile", "r");
        FileChannel fc = raf.getChannel( );

    }
}
