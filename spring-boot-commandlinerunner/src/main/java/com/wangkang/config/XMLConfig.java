package com.wangkang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 17:27 2018/12/24
 * @Modified By:
 */
@Configuration
//也可以用绝对路径 locations= {"file:d:/test/application-bean1.xml"
@ImportResource(locations = {"classpath:config/first.xml","classpath:config/second.xml"})
public class XMLConfig {
}
