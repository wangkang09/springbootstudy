package com.wangkang.context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * Description:
 *
 * @author wangkang
 * @date: 2019-08-12
 */
public class ProcessClientMsgBio implements Runnable {

    private Socket socket;

    public ProcessClientMsgBio(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true)
        ) {
            String time, body;
            body = in.readLine();//阻塞直到有数据过来


            System.out.println("receive msg : " + body);
            time = new Date(System.currentTimeMillis()).toString();

            System.out.println("发送时间给客户端...");
            out.println(time);//发送给客户端
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
