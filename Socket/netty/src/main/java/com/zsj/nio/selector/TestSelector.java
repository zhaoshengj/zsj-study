package com.zsj.nio.selector;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;

public class TestSelector {

    @Test
    public void test() throws IOException {

        //方式一：
        //Selector selector = Selector.open( );

        // 方式二：
        //SelectorProvider provider = SelectorProvider.provider();
        //Selector abstractSelector = provider.openSelector();

        ServerSocketChannel ssc=ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress("localhost",8080));
        ssc.configureBlocking(false);
        Selector selector = Selector.open();
        SelectionKey sscSelectionKey = ssc.register(selector, SelectionKey.OP_ACCEPT);//注册ServerSocketChannel
        while(true){
            SocketChannel sc = ssc.accept();
            if(sc==null){
                continue;
            }
            sc.configureBlocking(false);
            //注册SocketChannel
            SelectionKey scselectionKey = sc.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            //...其他操作

            scselectionKey.readyOps();

            scselectionKey.interestOps();
        }
    }
}
