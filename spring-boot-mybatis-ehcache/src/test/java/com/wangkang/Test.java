package com.wangkang;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.DefaultConfigurationBuilder;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.mybatis.spring.SqlSessionFactoryBean;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 10:45 2019/1/8
 * @Modified By:
 */
public class Test {

    @org.junit.Test
    public void CommonConfig() {
        String path = "application.properties";

        try {
            PropertiesConfiguration p = new PropertiesConfiguration(path);
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            org.apache.ibatis.session.Configuration  c = new org.apache.ibatis.session.Configuration();
            c.addLoadedResource("");
            System.out.println(1);
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

    }
}
