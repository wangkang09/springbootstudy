package com.wangkang;

import com.wangkang.aio.AioServer;
import com.wangkang.bio.BioServer;
import com.wangkang.netty.NettyServer;
import com.wangkang.nio.NioServer;

import java.util.concurrent.CountDownLatch;

/**
 * @author wangkang
 */
public class SpringBootIoNioSocketApplication {

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch c = new CountDownLatch(1);
		System.out.println(1);
		//new BioServer(8081,c).start();
		//new NioServer(8081,c).start();
		//new Thread(new AioServer(8081)).start();
		new NettyServer().bind(8081);
		c.await();
	}

}
