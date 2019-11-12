package com.wangkang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.Ordered;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//设置事务优先级高
@SpringBootApplication
@EnableCaching(order = Ordered.LOWEST_PRECEDENCE - 100)
@EnableTransactionManagement(order = Ordered.LOWEST_PRECEDENCE - 200)
public class SpringBootCacheTransactionApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootCacheTransactionApplication.class, args);
	}

}

