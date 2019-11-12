package com.wangkang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.wangkang.mapper")
public class SpringBootMybatisEhcacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMybatisEhcacheApplication.class, args);
	}

}

