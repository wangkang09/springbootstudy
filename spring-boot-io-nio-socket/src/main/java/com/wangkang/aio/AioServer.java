package com.wangkang.aio;

import com.wangkang.nio.NioServer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Description:
 *
 * @author wangkang
 * @date: 2019-08-13
 */
public class AioServer implements Runnable {
    int port = 9091;
    CountDownLatch countDownLatch;
    AsynchronousServerSocketChannel asynchronousServerSocketChannel;

    public AioServer(int port) {
        this.port = port;
        try {
            asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
            asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
            System.out.println("nioserver is started in port : " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        countDownLatch = new CountDownLatch(1);

        asynchronousServerSocketChannel.accept(this,new AcceptCompletionHandler());// 异步 server 接收到请求后，回调 completed 方法

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel,AioServer> {


        @Override
        public void completed(AsynchronousSocketChannel result, AioServer attachment) {
            attachment.asynchronousServerSocketChannel.accept(attachment,this);// 相当于 while(){accept}

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            result.read(byteBuffer,byteBuffer,new ReadCompletionHandler(result));
        }

        @Override
        public void failed(Throwable exc, AioServer attachment) {
            System.out.println("AcceptCompletionHandler failed e");
            countDownLatch.countDown();
        }
    }

    private class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {
        AsynchronousSocketChannel channel;
        public ReadCompletionHandler(AsynchronousSocketChannel channel) {
            this.channel = channel;
        }

        @Override
        public void completed(Integer result, ByteBuffer buffer) {
            buffer.flip();
            byte[] body = new byte[buffer.remaining()];

            buffer.get(body);
            try {
                String req = new String(body,"UTF-8");
                System.out.println("server receive msg : " + req);

                String time = new Date(System.currentTimeMillis()).toString();
                
                doWrite(time);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        private void doWrite(String time) {
            byte[] bytes = time.getBytes();
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
            buffer.put(bytes);
            buffer.flip();

            channel.write(buffer,buffer,new WriteCompletionHandler(channel));
        }

        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {
            try {
                channel.close();
            } catch (IOException e) {
                System.out.println("ReadCompletionHandler failed e");
            }
        }
    }

    private class WriteCompletionHandler implements CompletionHandler<Integer,ByteBuffer>{
        AsynchronousSocketChannel channel;

        public WriteCompletionHandler(AsynchronousSocketChannel channel) {
            this.channel = channel;
        }

        @Override
        public void completed(Integer result, ByteBuffer buffer) {
            if (buffer.hasRemaining()) {
                channel.write(buffer,buffer,this);
            }
        }

        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {
            try {
                channel.close();
            } catch (IOException e) {
                System.out.println("WriteCompletionHandler failed e");
            }
        }
    }
}
