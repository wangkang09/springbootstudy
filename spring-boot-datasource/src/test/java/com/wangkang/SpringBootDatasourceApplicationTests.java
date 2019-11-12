package com.wangkang;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDatasourceApplicationTests {

	@Resource(name = "c3p0Template")
	JdbcTemplate c3p0Template;

	@Resource(name = "hikarTemplate")
	JdbcTemplate hikarTemplate;


	@Resource(name = "dbcpTemplate")
	JdbcTemplate dbcpTemplate;

	@Resource(name = "druidTemplate")
	JdbcTemplate druidTemplate;

	@Resource(name = "druidDatasource")
	DataSource dataSource;
	@Test
	public void contextLoads() {
//		hikarTemplate.update("insert into a VALUES (2,'female')");
//		c3p0Template.update("insert into a VALUES (1,'male')");
		dbcpTemplate.update("delete from a");
		druidTemplate.update("insert into a VALUES (1,'male')");

	}

	@Test
	public void DruidCloseTest() throws InterruptedException {
		try {
			Connection connection1 = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		((DruidDataSource)dataSource).close();
		Thread.sleep(200);
		try {
			Connection connection = dataSource.getConnection();
			System.out.println(connection.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

