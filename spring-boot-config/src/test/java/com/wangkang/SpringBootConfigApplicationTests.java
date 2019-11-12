package com.wangkang;

import com.wangkang.config.ConfigurationPropertiesConfig;
import com.wangkang.config.EnableConfig;
import com.wangkang.config.ProfilesConfig;
import com.wangkang.config.ValueConfig;
import com.wangkang.entity.JedisConnectionFactory;
import com.wangkang.entity.JedisPoolConfig;
import com.wangkang.entity.Student;
import com.wangkang.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringBootConfigApplicationTests {

	@Resource
	ApplicationContext context;

	@Resource
	Student student;

	@Resource
	ValueConfig config;

	@Test
	public void contextLoads() {
		User user = (User)context.getBean("user");
		log.info(user.toString());

		log.info(student.toString());
	}

	@Test
	public void testValueConfig() {
		log.info("{}",config.getName());
		log.info("{}",config.getAge());
		log.info("{}",config.getAge());
		log.info("{}",config.getAge());

	}

	@Test
	public void tt() {
		student.setName(null);
		log.error(student.getName());
		Map map = new HashMap();
		log.error("{}",map.get(null));
	}

	@Resource
	ConfigurationPropertiesConfig configurationPropertiesConfig;
	@Test
	public void configuration() {
		log.info(configurationPropertiesConfig.toString());
	}


	@Resource
	JedisPoolConfig jedisPoolConfig;
	@Resource
	JedisConnectionFactory jedisConnectionFactory;
	@Test
	public void enableConfiguration() {
		log.info(jedisConnectionFactory.toString());
		log.info(jedisPoolConfig.toString());
	}

	@Resource
	ProfilesConfig profilesConfig;
	@Test
	public void profilesTest() {
		log.info(profilesConfig.getProfiles());
	}

	@Resource
	EnableConfig enableConfig;
	@Test
	public void enableConfigTest() {
		enableConfig.en();
	}
}

