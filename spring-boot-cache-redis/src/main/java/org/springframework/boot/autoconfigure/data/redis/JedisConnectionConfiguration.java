//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springframework.boot.autoconfigure.data.redis;

import java.net.UnknownHostException;
import java.time.Duration;
import java.util.function.Consumer;

import com.wangkang.config.TestConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisConnectionConfiguration.ConnectionInfo;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Pool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisClientConfigurationBuilder;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@ConditionalOnClass({GenericObjectPool.class, JedisConnection.class, Jedis.class})
class JedisConnectionConfiguration extends RedisConnectionConfiguration {


//    @Autowired
//    JedisConfg jedisConfg;

    @Autowired
    TestConfig testConfig;

    private final RedisProperties properties;
    private final ObjectProvider<JedisClientConfigurationBuilderCustomizer> builderCustomizers;

    JedisConnectionConfiguration(RedisProperties properties, ObjectProvider<RedisSentinelConfiguration> sentinelConfiguration, ObjectProvider<RedisClusterConfiguration> clusterConfiguration, ObjectProvider<JedisClientConfigurationBuilderCustomizer> builderCustomizers) {
        super(properties, sentinelConfiguration, clusterConfiguration);
        this.properties = properties;
        this.builderCustomizers = builderCustomizers;
    }

    @Bean(name = "myJedisConnectFactory")
    @Primary
    @ConditionalOnMissingBean(RedisConnectionFactory.class)
    public JedisConnectionFactory redisConnectionFactory() throws UnknownHostException {
        return this.createJedisConnectionFactory();
    }

    private JedisConnectionFactory createJedisConnectionFactory() {
        JedisClientConfiguration clientConfiguration = this.getJedisClientConfiguration();
        JedisConnectionFactory jedisConnectionFactory = this.getSentinelConfig() != null?new JedisConnectionFactory(this.getSentinelConfig(), clientConfiguration):(this.getClusterConfiguration() != null?new JedisConnectionFactory(this.getClusterConfiguration(), clientConfiguration):new JedisConnectionFactory(this.getStandaloneConfig(), clientConfiguration));

        jedisConnectionFactory.getPoolConfig().setMaxIdle(1111);
        return jedisConnectionFactory;
    }

    private JedisClientConfiguration getJedisClientConfiguration() {
        JedisClientConfigurationBuilder builder = this.applyProperties(JedisClientConfiguration.builder());
        Pool pool = this.properties.getJedis().getPool();
        if(pool != null) {
            this.applyPooling(pool, builder);
        }

        if(StringUtils.hasText(this.properties.getUrl())) {
            this.customizeConfigurationFromUrl(builder);
        }

        this.customize(builder);
        return builder.build();
    }

    private JedisClientConfigurationBuilder applyProperties(JedisClientConfigurationBuilder builder) {
        if(this.properties.isSsl()) {
            builder.useSsl();
        }

        if(this.properties.getTimeout() != null) {
            Duration timeout = this.properties.getTimeout();
            builder.readTimeout(timeout).connectTimeout(timeout);
        }

        return builder;
    }

    private void applyPooling(Pool pool, JedisClientConfigurationBuilder builder) {
        builder.usePooling().poolConfig(this.jedisPoolConfig(pool));
    }

    private JedisPoolConfig jedisPoolConfig(Pool pool) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(pool.getMaxActive());
        config.setMaxIdle(pool.getMaxIdle());
        config.setMinIdle(pool.getMinIdle());
        if(pool.getMaxWait() != null) {
            config.setMaxWaitMillis(pool.getMaxWait().toMillis());
        }

        return config;
    }

    private void customizeConfigurationFromUrl(JedisClientConfigurationBuilder builder) {
        ConnectionInfo connectionInfo = this.parseUrl(this.properties.getUrl());
        if(connectionInfo.isUseSsl()) {
            builder.useSsl();
        }

    }

    private void customize(JedisClientConfigurationBuilder builder) {
        this.builderCustomizers.orderedStream().forEach((customizer) -> {
            customizer.customize(builder);
        });
    }
}
