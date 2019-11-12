package com.wangkang.util;


import org.junit.Test;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 10:00 2019/1/8
 * @Modified By:
 */
public class ConfigUtilTest {

    @Test
    public void testProperties() {

        String path = "application.properties";
        String pathYml = "application.yml";
        Properties p = new Properties();
        String rPath = "mybatis.xml";
        String ePath = "ehcache.xml";
        String pPath = "PersonMapper.xml";

        p = ConfigUtil.getProperties(rPath);

        p = ConfigUtil.getProperties(pathYml);

        p = ConfigUtil.getProperties(path);
        p = ConfigUtil.getProperties(ePath);
        p = ConfigUtil.getProperties(pPath);

        System.out.println(1);
    }

    @Test
    public void testResource() {

        String path = "application.properties";
        String pathYml = "application.yml";
        Properties p = new Properties();
        String rPath = "mybatis.xml";
        //p = ConfigUtil.getProperties(path);

        Resource r = ConfigUtil.getResource(pathYml);

        try {
            p.load(r.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(1);
    }

    @Test
    public void CommonConfig() {
        String path = "application.properties";

//        try {
//            PropertiesConfiguration p = new PropertiesConfiguration(path);
//            System.out.println(1);
//        } catch (ConfigurationException e) {
//            e.printStackTrace();
//        }

    }

}