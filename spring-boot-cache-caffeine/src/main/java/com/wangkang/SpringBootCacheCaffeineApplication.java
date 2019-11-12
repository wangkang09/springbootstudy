package com.wangkang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootCacheCaffeineApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCacheCaffeineApplication.class, args);
	}

}

