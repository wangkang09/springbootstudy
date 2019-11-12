package com.wangkang.bio;

import org.springframework.core.annotation.Order;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Description:
 *
 * @author wangkang
 * @date: 2019-08-12
 */
public class BioClient {
    public static void main(String[] args) {
        int port = 8081;
        try (
             Socket socket = new Socket("127.0.0.1", port);
             InputStream in = socket.getInputStream();
             PrintWriter out = new PrintWriter(socket.getOutputStream(),true)
        ) {
            out.println("query time order");
            System.out.println("send order to server succeed...");

            byte[] bs = new byte[1024];
            while (in.read(bs) != -1) {
                System.out.println("Now is " + bs.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
