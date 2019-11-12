package com.wangkang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@EnableTransactionManagement(mode = AdviceMode.ASPECTJ,proxyTargetClass = true)
@EnableTransactionManagement(proxyTargetClass = true)
public class SpringBootTransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTransactionApplication.class, args);
	}

}

