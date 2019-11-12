package com.wangkang.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.wangkang.DataSourceNames;
import com.wangkang.DynamicDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 10:07 2019/1/7
 * @Modified By:
 */
@Configuration
public class DatasourceConfig {

    @Bean(name = "c3p0Datasource")
    @ConfigurationProperties("wang-kang.c3p0.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(ComboPooledDataSource.class).build();
    }

    @Bean(name = "hikarDatasource")
    @ConfigurationProperties("wang-kang.hikar.datasource")
    public HikariDataSource hikariDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "dbcpDatasource")

    @ConfigurationProperties("wang-kang.dbcp.datasource")
    public BasicDataSource basicDataSource() {
        return DataSourceBuilder.create().type(BasicDataSource.class).build();
    }

    @Bean(name = "druidDatasource")
    @ConfigurationProperties("wang-kang.druid.datasource")
    public DataSource druidDatasource() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Bean
    @Primary
    public DataSource primaryDatasource(DataSource c3p0Datasource,DataSource dbcpDatasource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceNames.FIRST, c3p0Datasource);
        targetDataSources.put(DataSourceNames.SECOND, dbcpDatasource);
        return new DynamicDataSource(c3p0Datasource, targetDataSources);
    }

}
