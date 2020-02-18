package com.zsj.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimeServer {

    private static ExecutorService executor;
    static {
        executor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1000));
    }

    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(8080));
        ssc.configureBlocking(false);
        Selector selector = Selector.open();
        ssc.register(selector, ssc.validOps());

        while (true) {
            int readyCount = selector.select(1000);
            if(readyCount==0){
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while(keyIterator.hasNext()){
                SelectionKey selectionKey = keyIterator.next();
                if(selectionKey.isValid()){
                    //表示ServerSocketChannel
                    if(selectionKey.isAcceptable()){
                        ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel socketChannel = server.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector,SelectionKey.OP_READ|SelectionKey.OP_WRITE);
                    }

                    //表示SocketChannel
                    if(selectionKey.isReadable()){
                        executor.submit(new TimeServerTask(selectionKey));
                    }
                    keyIterator.remove();
                }
            }
        }
    }


    static class TimeServerTask implements Runnable{

        private SelectionKey selectionKey;

        public TimeServerTask(SelectionKey selectionKey) {
            this.selectionKey = selectionKey;
        }

        @Override
        public void run() {
            SocketChannel channel = (SocketChannel) selectionKey.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
            try {
                int count =0;
                while ((count = channel.read(byteBuffer)) > 0) {
                    byteBuffer.flip();
                    byte[] request=new byte[byteBuffer.remaining()];
                    byteBuffer.get(request);
                    String requestStr=new String(request);
                    byteBuffer.clear();
                    if (!"GET CURRENT TIME".equals(requestStr)) {
                        channel.write(byteBuffer.put("BAD_REQUEST".getBytes()));
                    } else {
                        byteBuffer.put(Calendar.getInstance().getTime().toLocaleString().getBytes());
                        byteBuffer.flip();
                        channel.write(byteBuffer);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
                selectionKey.cancel();
            }
        }
    }

}

