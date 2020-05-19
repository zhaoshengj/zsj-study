package com.zsj.netty.Echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

public class EchoServer {

    static final boolean SSL = System.getProperty("ssl") != null;
    static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));

    public static void main(String[] args) throws Exception {
        //配置  SSL
        final SslContext sslCtx;
        if(SSL){
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
        }else {
            sslCtx = null;
        }

        // 创建两个 EventLoopGroup 对象
        //创建 boss 线程组 用于服务端接受客户端的连接
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // 创建 worker 线程组 用于进行 SocketChannel 的数据读写
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        // 创建 EchoServerHandler 对象
        final EchoServerHandler serverHandler = new EchoServerHandler();

        try {
            // 创建 ServerBootstrap 对象
            ServerBootstrap server = new ServerBootstrap();
            // 设置使用的 EventLoopGroup
            server.group(bossGroup,workerGroup)
                    // 设置要被实例化的为 NioServerSocketChannel 类
                    .channel(NioServerSocketChannel.class)
                    // 设置 NioServerSocketChannel 的可选项
                    .option(ChannelOption.SO_BACKLOG,100)
                    // 设置 NioServerSocketChannel 的处理器
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        // 设置连入服务端的 Client 的 SocketChannel 的处理器
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            ChannelPipeline pipeline = sc.pipeline();
                            if(sslCtx != null){
                                pipeline.addLast(sslCtx.newHandler(sc.alloc()));
                            }
                            pipeline.addLast(serverHandler);
                        }
                    });


            // Start the server.
            // 绑定端口，并同步等待成功，即启动服务端
            ChannelFuture f = server.bind(PORT).sync();

            // Wait until the server socket is closed.
            // 监听服务端关闭，并阻塞等待
            f.channel().closeFuture().sync();

        }catch (Exception e){
            System.out.println(e);
        }finally {
            // Shut down all event loops to terminate all threads.
            // 优雅关闭两个 EventLoopGroup 对象
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
