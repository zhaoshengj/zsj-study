package com.zsj.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Calendar;
import java.util.concurrent.*;

public class TimeServer {
    private  BlockingQueue<SocketChannel> idleQueue =new LinkedBlockingQueue<SocketChannel>();

    private BlockingQueue<Future<SocketChannel>> workingQueue=new LinkedBlockingQueue<Future<SocketChannel>>();

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    {
        new Thread(){
            @Override
            public void run() {
                try {
                    while (true) {
                        //task1：迭代当前idleQueue中的SocketChannel，提交到线程池中执行任务，并将其移到workingQueue中
                        for (int i = 0; i < idleQueue.size(); i++) {
                            SocketChannel socketChannel = idleQueue.poll();
                            if (socketChannel != null) {
                                Future<SocketChannel> result = executor.submit(new TimeServerHandleTask(socketChannel,executor), socketChannel);
                                workingQueue.put(result);
                            }
                        }
                        //task2：迭代当前workingQueue中的SocketChannel，如果任务执行完成，将其移到idleQueue中
                        for (int i = 0; i < workingQueue.size(); i++) {
                            Future<SocketChannel> future = workingQueue.poll();
                            if (!future.isDone()){
                                workingQueue.put(future);
                                continue;
                            }
                            SocketChannel channel  = null;
                            try {
                                channel = future.get();
                                idleQueue.put(channel);
                            } catch (ExecutionException e) {
                                //如果future.get()抛出异常，关闭SocketChannel，不再放回idleQueue
                                channel.close();
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        TimeServer timeServer = new TimeServer();
        ServerSocketChannel ssc=ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.socket().bind(new InetSocketAddress(8080));
        while (true){
            SocketChannel socketChannel = ssc.accept();
            if(socketChannel==null){
                continue;
            }else{
                socketChannel.configureBlocking(false);
                timeServer.idleQueue.add(socketChannel);
            }
        }
    }
}

class TimeServerHandleTask implements Runnable {

    SocketChannel socketChannel;
    ExecutorService executorService;
    ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);

    public TimeServerHandleTask(SocketChannel socketChannel, ExecutorService executorService) {
        this.socketChannel = socketChannel;
        this.executorService = executorService;
    }

    @Override
    public void run() {
        try {
            if(socketChannel.read(byteBuffer)>0){
                while (true){
                    byteBuffer.flip();
                    if(byteBuffer.remaining()<"GET CURRENT TIME".length()){
                        byteBuffer.compact();
                        socketChannel.read(byteBuffer);
                        continue;
                    }
                    byte[] request=new byte[byteBuffer.remaining()];
                    byteBuffer.get(request);
                    String requestStr=new String(request);
                    byteBuffer.clear();
                    if (!"GET CURRENT TIME".equals(requestStr)) {
                        socketChannel.write(byteBuffer.put("BAD_REQUEST".getBytes()));
                    } else {
                        ByteBuffer byteBuffer = this.byteBuffer.put(Calendar.getInstance()
                                .getTime().toLocaleString().getBytes());
                        byteBuffer.flip();
                        socketChannel.write(byteBuffer);
                    }

                }
            }
            TimeServerHandleTask currentTask = new TimeServerHandleTask(socketChannel,
                    executorService);
            executorService.submit(currentTask);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}