package com.wangkang.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * Description: nio 客户端如何写，，还是用  bioClient 吧
 *
 * @author wangkang
 * @date: 2019-08-12
 */
@Deprecated
public class NioClient implements Runnable{

    int port;
    SocketChannel socketChannel;
    Selector selector;
    static CountDownLatch countDownLatch;
    static volatile boolean stop = true;

    public NioClient(int port) throws IOException {
        socketChannel = SocketChannel.open();
        selector = Selector.open();
        socketChannel.configureBlocking(false);
        this.port = port;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        countDownLatch = new CountDownLatch(1);
        int port = 8081;
        new Thread(new NioClient(port)).start();
        countDownLatch.await();
    }

    private static void readServer(SocketChannel socketChannel) throws IOException {
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);

        int readBytes = socketChannel.read(readBuffer);
        if (readBytes > 0) {
            readBuffer.flip();
            byte[] bytes = new byte[readBuffer.remaining()];
            readBuffer.get(bytes);
            String body = new String(bytes,"UTF-8");
            System.out.println("now is : " + body);
            socketChannel.close();
            stop = false;
            countDownLatch.countDown();
        } else if (readBytes < 0) {
            socketChannel.close();
        }

    }

    private static void askServer(SocketChannel socketChannel) throws IOException {
        byte[] req = "query time order".getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);

        writeBuffer.put(req);
        writeBuffer.flip();
        socketChannel.write(writeBuffer);
    }


    @Override
    public void run() {
        try {
            if (socketChannel.connect(new InetSocketAddress("127.0.0.1",port))) {
                socketChannel.register(selector, SelectionKey.OP_READ);
                askServer(socketChannel);
            } else {
                socketChannel.register(selector,SelectionKey.OP_CONNECT);
            }

            while(stop) {
                try {
                    selector.select(2000);
                    Set<SelectionKey> keys = selector.selectedKeys();//轮询 key
                    Iterator<SelectionKey> it = keys.iterator();
                    SelectionKey key;
                    while (it.hasNext()) {
                        key = it.next();
                        it.remove();
                        handlerKeyType(key);

                        //readServer((SocketChannel) key.channel());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handlerKeyType(SelectionKey key) throws IOException {
        if (key.isValid()) {
            SocketChannel sc = (SocketChannel) key.channel();
            if (key.isConnectable()) {
                if (sc.finishConnect()) {
                    sc.register(selector,SelectionKey.OP_READ);
                    askServer(sc);
                } else {
                    System.exit(1);
                }
            }
            if (key.isReadable()) {
                readServer((SocketChannel) key.channel());
            }
        }
    }
}
