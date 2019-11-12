package com.wangkang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.init.ScriptUtils;

@SpringBootApplication
public class SpringBootDatasourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDatasourceApplication.class, args);
	}

}

