package com.wangkang;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;

@SpringBootApplication
@Slf4j
@Order(3)
public class SpringBootCommandlinerunnerApplication implements CommandLineRunner{
	@Resource
	ApplicationContext context;
	public static void main(String[] args) {
		log.info("准备启动Spring");
		SpringApplication.run(SpringBootCommandlinerunnerApplication.class, args);
		log.info("启动完成");
	}

	@Override
	public void run(String... strings) throws Exception {
		int count = 10;
		int total = count;
		while (count>=0) {
			log.info("Schedule3第{}次执行,{}",total - --count,context.getBean("student").toString());
			Thread.sleep(1000);
		}
	}
}

