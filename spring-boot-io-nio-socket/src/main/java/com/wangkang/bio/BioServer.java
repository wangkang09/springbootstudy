package com.wangkang.bio;

import com.wangkang.context.ProcessClientMsgBio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

import static com.wangkang.context.ThreadPoolUtil.myThreadPoolExecutor;

/**
 * Description:
 *
 * @author wangkang
 * @date: 2019-08-12
 */
public class BioServer {

    private CountDownLatch countDownLatch;
    private int port = 8181;
    public BioServer(int port, CountDownLatch countDownLatch) {
        this.port = port;
        this.countDownLatch = countDownLatch;
    }

    public void start() {
        try (ServerSocket server = new ServerSocket(port)) {// 服务端开启 port 端口，接收客户端连接
            System.out.println("Server is started in " + port + " port...");
            Socket socket;

            for(;;) {
                socket = server.accept();//在这里阻塞，直到接收到客户端连接
                System.out.println("接收到新的客户端连接...");
                myThreadPoolExecutor.submit(new ProcessClientMsgBio(socket));//异步处理客户端连接
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
            System.out.println("server closed ...");
        }
    }
}
