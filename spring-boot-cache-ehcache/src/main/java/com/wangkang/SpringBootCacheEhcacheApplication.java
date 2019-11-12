package com.wangkang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@EnableCaching
@Transactional
public class SpringBootCacheEhcacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCacheEhcacheApplication.class, args);
	}



}

