package com.wangkang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 11:23 2019/1/7
 * @Modified By:
 */
@Configuration
public class JDBCTemplateConfig {
    //加了这个就会产生循环依赖
//    @Resource(name = "primaryDatasource")
//    DataSource primaryDatasource;

    @Resource(name = "c3p0Datasource")
    DataSource c3p0Datasource;

    @Resource(name = "hikarDatasource")
    DataSource hikarDatasource;

    @Resource(name = "dbcpDatasource")
    DataSource dbcpDatasource;

    @Resource(name = "druidDatasource")
    DataSource druidDatasource;

    @Bean(name = "c3p0Template")
    public JdbcTemplate c3p0JdbcTemplate() {
        return new JdbcTemplate(c3p0Datasource);
    }

    @Bean(name = "hikarTemplate")
    public JdbcTemplate hikarJdbcTemplate() {
        return new JdbcTemplate(hikarDatasource);
    }

    @Bean(name = "dbcpTemplate")
    public JdbcTemplate dbcpJdbcTemplate() {
        return new JdbcTemplate(dbcpDatasource);
    }

    @Bean(name = "druidTemplate")
    public JdbcTemplate druidJdbcTemplate() {
        return new JdbcTemplate(druidDatasource);
    }
}
