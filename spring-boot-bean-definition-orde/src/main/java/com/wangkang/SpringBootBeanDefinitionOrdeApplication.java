package com.wangkang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

/**
 * Description:
 * @author wangkang
 */
@SpringBootApplication()
@ComponentScan(basePackages = {"com.wangkang.controller","com.wangkang.importConfig","com.wangkang.condition","com.wangkang.doubleDefin"})
public class SpringBootBeanDefinitionOrdeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBeanDefinitionOrdeApplication.class, args);
	}

	@PostConstruct
	public void init() {
		System.out.println("初始化 SpringBootBeanDefinitionOrdeApplication ....");
	}
}
