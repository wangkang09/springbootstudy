package com.wangkang.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpServerChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;

import java.util.Date;


/**
 * Description:
 *
 * @author wangkang
 * @date: 2019-08-13
 */
public class NettyServer {

    public void bind(int port) throws InterruptedException {
        EventLoopGroup bossG = new NioEventLoopGroup(1);
        EventLoopGroup workG = new NioEventLoopGroup();
        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(bossG,workG)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .handler(new LoggingHandler())
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
            ch.pipeline().addLast(new TimeServerHandler());
        }

    }

    private class TimeServerHandler extends ChannelHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf buf = (ByteBuf) msg;
            byte[] req = new byte[buf.readableBytes()];
            buf.readBytes(req);

            String body = new String(req,"UTF-8");
            System.out.println("netty server receive msg : " + body);

            String time = new Date(System.currentTimeMillis()).toString();

            ByteBuf resp = Unpooled.copiedBuffer(time.getBytes());
            ctx.write(resp);
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
