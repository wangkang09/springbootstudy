package com.wangkang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath:dependsOn.xml"})
public class SpringBootDependsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDependsApplication.class, args);
    }

}
