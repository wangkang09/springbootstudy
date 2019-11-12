package com.wangkang.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.concurrent.CountDownLatch;

/**
 * Description:
 *
 * @author wangkang
 * @date: 2019-08-13
 */
public class NettyClient {
    static CountDownLatch latch;
    public static void main(String[] args) throws InterruptedException {
        //latch = new CountDownLatch(1);
        new NettyClient().connect(8081,"127.0.0.1");
        //latch.await();
    }

    public void connect(int port, String host) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap client = new Bootstrap();
            client.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new TimeClientHandler());
                        }
                    });

            ChannelFuture f = client.connect(host,port).sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }

    }

    private class TimeClientHandler extends ChannelHandlerAdapter {

        private final ByteBuf buf;
        public TimeClientHandler() {
            byte[] req = "query time msg".getBytes();
            buf = Unpooled.buffer(req.length);
            buf.writeBytes(req);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            ctx.writeAndFlush(buf);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf buf = (ByteBuf) msg;
            byte[] req = new byte[buf.readableBytes()];
            buf.readBytes(req);
            String body = new String(req,"UTF-8");
            System.out.println("now is " + body);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            latch.countDown();
        }
    }
}
