package com.wangkang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SpringBootIoOperationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootIoOperationApplication.class, args);

		FileLogManage.fileLog().write(null);
	}

}
