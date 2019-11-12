package com.wangkang.context;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Date;

/**
 * Description:
 *
 * @author wangkang
 * @date: 2019-08-12
 */
public class ProcessClientMsgNio implements Runnable {
    private SelectionKey key;

    public ProcessClientMsgNio(SelectionKey key) {
        this.key = key;
    }


    @Override
    public void run() {
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        try {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            int readBytes = socketChannel.read(readBuffer);
            if (readBytes > 0) {
                // ---------------- 解析 socket
                readBuffer.flip();
                byte[] bytes =new byte[readBuffer.remaining()];
                readBuffer.get(bytes);
                String body = new String(bytes,"UTF-8");
                System.out.println("server receive msg：" + body);

                // ---------------- 响应 socket
                String rep = new Date(System.currentTimeMillis()).toString();
                byte[] b = rep.getBytes();
                ByteBuffer writeBuffer = ByteBuffer.allocate(b.length);
                writeBuffer.put(b);
                writeBuffer.flip();
                socketChannel.write(writeBuffer);
                System.out.println("server send msg：" + rep);

            } else if (readBytes < 0) {

                key.cancel();
                key.channel().close();
                socketChannel.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
            key.cancel();
            try {
                key.channel().close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }
}
