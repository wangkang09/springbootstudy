package com.wangkang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@SpringBootApplication(scanBasePackages = {"com.wangkang","org.springframework.boot.autoconfigure.data.redis"})
@EnableCaching
//@SpringBootApplication
//@Import(org.springframework.boot.autoconfigure.data.redis.JedisConfg.class)
@Transactional
public class SpringBootCacheRedisApplication implements CommandLineRunner{

	public static RedisConnectionFactory redis0;

	public static RedisConnectionFactory redis1;

	@Autowired
	@Qualifier("myJedisConnectFactory") // 默认容器中的name为：redisConnectionFactory
	RedisConnectionFactory redisConnectionFactory0;

	@Autowired
	@Qualifier("myJedisConnectFactory") // 默认容器中的name为：redisConnectionFactory
	RedisConnectionFactory redisConnectionFactory1;

	//测试子类父类上下文关系用到
//	@Autowired
//	@Qualifier("myJedisConnectFactory")
//	RedisConnectionFactory my;


	//@Autowired
	//JedisConnectionFactory jedisConnectionFactory; //默认容器中没有jedisConnectionFactory，只有LettuceConnectionFactory！

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCacheRedisApplication.class, args);
		
		redisConnectionFactoryDetail();
	}

	private static void redisConnectionFactoryDetail() {
		RedisConnection connection = redis0.getConnection();
	}

	@PostConstruct
	public void init() {
		redis0 = redisConnectionFactory0;
		redis1 = redisConnectionFactory1;
	}


	@Override
	public void run(String... strings) throws Exception {
		//RedisConnection connection = jedisConnectionFactory.getConnection();
	}
}

