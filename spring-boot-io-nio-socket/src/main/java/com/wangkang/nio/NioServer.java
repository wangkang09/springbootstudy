package com.wangkang.nio;

import com.wangkang.context.SelectorTask;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.CountDownLatch;

import static com.wangkang.context.ThreadPoolUtil.myThreadPoolExecutor;

/**
 * Description:
 *
 * @author wangkang
 * @date: 2019-08-12
 */
public class NioServer {
    private int port;
    private CountDownLatch countDownLatch;
    private Selector selector;
    private ServerSocketChannel serverChannel;

    public NioServer(int port, CountDownLatch countDownLatch) {
        this.port = port;
        this.countDownLatch = countDownLatch;
    }

    public void start() {
        try {
            selector = Selector.open();
            serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);
            serverChannel.socket().bind(new InetSocketAddress(port),1024);

            serverChannel.register(selector, SelectionKey.OP_ACCEPT);//将 server channel 注册进 selector 中，并监听 accept 事件
            System.out.println("NioServer is started in port：" + port);

            myThreadPoolExecutor.submit(new SelectorTask(selector));//开启 selector 线程轮询

        } catch (IOException e) {
            countDownLatch.countDown();
            e.printStackTrace();
        }

    }
}
