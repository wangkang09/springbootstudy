package com.wangkang.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * Description:
 *
 * @author wangkang
 * @date: 2019-08-13
 */
public class AioClient implements CompletionHandler<Void,AioClient>, Runnable {

    AsynchronousSocketChannel channel;
    String host;
    int port;
    CountDownLatch latch;
    static CountDownLatch latch1;

    public AioClient(String host, int port) {
        this.host = host;
        this.port = port;

        try {
            channel = AsynchronousSocketChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new AioClient("127.0.0.1",8081));
        t.start();
        t.join();
    }

    @Override
    public void run() {
        latch = new CountDownLatch(1);
        channel.connect(new InetSocketAddress(host,port),this,this);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void completed(Void result, AioClient attachment) {
        byte[] req = "query time msg".getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(req.length);
        buffer.put(req);
        buffer.flip();

        channel.write(buffer,buffer,new WriteCompletionHandler());
    }

    @Override
    public void failed(Throwable exc, AioClient attachment) {
        System.out.println("AioClient failed e");
        try {
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }

    private class WriteCompletionHandler implements CompletionHandler<Integer,ByteBuffer> {
        @Override
        public void completed(Integer result, ByteBuffer buffer) {
            if (buffer.hasRemaining()) {
                channel.write(buffer,buffer,this);
            } else {
                ByteBuffer readBuf = ByteBuffer.allocate(1024);
                channel.read(readBuf,readBuf,new ReadCompletionHandler());
            }
        }

        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {
            System.out.println("WriteCompletionHandler failed e");
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }
    }

    private class ReadCompletionHandler implements CompletionHandler<Integer,ByteBuffer> {
        @Override
        public void completed(Integer result, ByteBuffer buffer) {
            buffer.flip();
            byte[] bytes = new byte[buffer.remaining()];

            buffer.get(bytes);
            try {
                String body = new String(bytes,"UTF-8");
                System.out.println("now is " + body);
                latch.countDown();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {
            System.out.println("ReadCompletionHandler failed e");
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }
    }
}
