package com.wangkang.context;

import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import static com.wangkang.context.ThreadPoolUtil.myThreadPoolExecutor;

/**
 * Description:
 *
 * @author wangkang
 * @date: 2019-08-12
 */
public class SelectorTask implements Runnable {
    private Selector selector;

    public SelectorTask(Selector selector) {
        this.selector = selector;
    }


    @Override
    public void run() {
        while (true) {

            try {
                selector.select(2000);
                Set<SelectionKey> keys = selector.selectedKeys();//轮询 key
                Iterator<SelectionKey> it = keys.iterator();
                SelectionKey key;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();
                    handlerKeyType(key);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //这里的 slector 只负责监听 read accept 事件，具体的业务会调用其他线程做处理
    private void handlerKeyType(SelectionKey key) throws IOException {
        if (key.isValid()) {
             if (key.isAcceptable()) {
                 ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                 SocketChannel sc = ssc.accept();
                 sc.configureBlocking(false);
                 sc.register(selector,SelectionKey.OP_READ);//将 客户端端 channel 注册进 selector 中，并监听 read 事件
             }

             if (key.isReadable()) {
                 myThreadPoolExecutor.submit(new ProcessClientMsgNio(key));//异步处理客户度 channel，做业务处理
             }
        }
    }
}
