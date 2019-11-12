package com.wangkang.config;

import java.io.IOException;
import java.util.Properties;

import com.wangkang.util.ConfigUtil;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.github.pagehelper.PageInterceptor;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 18:25 2019/1/7
 * @Modified By:
 */
public class config {

//    @Bean
//    @ConditionalOnMissingBean // 当容器里没有指定的Bean的情况下创建该对象
//    public SqlSessionFactoryBean sqlSessionFactory(HikariDataSource dataSource) throws IOException {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        // 设置数据源
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        // 设置mybatis的主配置文件
//        sqlSessionFactoryBean.setConfigLocation(ConfigUtil.getResource("classpath:mybatis.xml"));
//        // 设置mapper映射文件，失败！
//        //sqlSessionFactoryBean.setMapperLocations(ConfigUtil.getResources("classpath:com/wangkang/mapper/*.xml"));
//
//        //分页插件
//        PageInterceptor pageInterceptor = new PageInterceptor();
//        Properties properties = new Properties();
//        //properties.setProperty("helperDialect", "mysql");
//        properties.setProperty("reasonable", "true");
//        properties.setProperty("params", "pageNum=pageNum;pageSize=pageSize");
//        pageInterceptor.setProperties(properties);
//
//
//        //添加插件
//        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageInterceptor});
//
//        return sqlSessionFactoryBean;
//    }
}
