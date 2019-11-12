package com.wangkang.netty.nettyV1;

/**
 * Description:
 *
 * @author wangkang
 * @date: 2019-08-13
 */

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.Date;
import java.util.concurrent.CountDownLatch;


/**
 * Description:
 *
 * @author wangkang
 * @date: 2019-08-13
 */
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch =new CountDownLatch(1);
        new NettyServer().bind(8081);
        latch.await();
    }
    public void bind(int port) throws InterruptedException {
        EventLoopGroup bossG = new NioEventLoopGroup(1);
        EventLoopGroup workG = new NioEventLoopGroup();
        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(bossG,workG)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new myChildHandler());

            ChannelFuture f = server.bind(port).sync();
            f.channel().closeFuture().sync();
        } finally {
            bossG.shutdownGracefully();
            workG.shutdownGracefully();
        }

    }

    private class myChildHandler extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast(new FixedLengthFrameDecoder(10));
            ch.pipeline().addLast(new StringDecoder());
            ch.pipeline().addLast(new TimeServerHandler());
        }

    }

    private class TimeServerHandler extends ChannelHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

            System.out.println("netty server receive msg : " + msg);

//            String time = new Date(System.currentTimeMillis()).toString();
//
//            ByteBuf resp = Unpooled.copiedBuffer(time.getBytes());
//            ctx.write(resp);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.flush();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }
    }
}
