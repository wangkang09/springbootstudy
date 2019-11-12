package com.wangkang;

import java.util.Iterator;
import java.util.ServiceLoader;

public class JavaSpiApplication {

	public static void main(String[] args) {
		ServiceLoader<Proxy> serviceLoader = ServiceLoader.load(Proxy.class);

		Iterator<Proxy> iter = serviceLoader.iterator();

		while (iter.hasNext()) {
			Proxy proxy = iter.next();
			String str = proxy.proxyType("动态代理");
			System.out.println(str);
		}
	}

}

