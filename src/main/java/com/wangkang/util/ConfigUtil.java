package com.wangkang.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 9:52 2019/1/8
 * @Modified By:
 */
public class ConfigUtil {
    private static final Logger log = LoggerFactory.getLogger(ConfigUtil.class);

    /**
     *
     * @Description: 通过路径得到Properties，只能简单的按：和=来分割成key/value形式
     * ConfigUtil.getResource("classpath:mybatis.xml")
     *
     * @auther: wangkang
     * @date: 9:59 2019/1/8
     * @param: [path]
     * @return: java.util.Properties
     *
     */
    public static Properties getProperties(String path) {

        Properties properties = new Properties();

        InputStream in  =  ClassLoader.getSystemResourceAsStream(path);

        try {
            properties.load(in);
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }

        return properties;
    }

    /**
     *
     * @Description: 单纯的将path文件转为inputStream流的形式
     *
     * @auther: wangkang
     * @date: 10:24 2019/1/8
     * @param: [path]
     * @return: org.springframework.core.io.Resource
     *
     */
    public static Resource getResource(String path) {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource(path);
        return resource;
    }

    public static Resource[] getResources(String path) {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = null;
        try {
            resources = resolver.getResources(path);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return resources;
    }
}
