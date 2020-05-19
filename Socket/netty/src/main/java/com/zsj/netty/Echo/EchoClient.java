package com.zsj.netty.Echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

import javax.net.ssl.SSLException;

public class EchoClient {

    static final boolean SSL = System.getProperty("ssl") != null;
    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));

    public static void main(String[] args) throws Exception {
        // Configure SSL.git
        // 配置 SSL
        final SslContext sslCtx;
        if (SSL) {
            sslCtx = SslContextBuilder.forClient()
                    .trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        } else {
            sslCtx = null;
        }
        // Configure the client.
        // 创建一个 EventLoopGroup 对象
        EventLoopGroup group = new NioEventLoopGroup();

        try{
            // 创建 Bootstrap 对象
            Bootstrap b = new Bootstrap();
            b.group(group) // 设置使用的 EventLoopGroup
                    .channel(NioSocketChannel.class) // 设置要被实例化的为 NioSocketChannel 类
                    .option(ChannelOption.TCP_NODELAY, true) // 设置 NioSocketChannel 的可选项
                    .handler(new ChannelInitializer<SocketChannel>() { // 设置 NioSocketChannel 的处理器
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            if (sslCtx != null) {
                                p.addLast(sslCtx.newHandler(ch.alloc(), HOST, PORT));
                            }
                            //p.addLast(new LoggingHandler(LogLevel.INFO));
                            p.addLast(new EchoClientHandler());
                        }
                    });
            // 连接服务器，并同步等待成功，即启动客户端
            ChannelFuture sync = b.connect(HOST, PORT).sync();
        }catch (Exception e){

        }finally {

            group.shutdownGracefully();
        }
    }

}
