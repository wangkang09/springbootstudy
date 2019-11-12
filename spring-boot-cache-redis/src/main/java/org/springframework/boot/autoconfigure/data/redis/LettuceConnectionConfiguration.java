//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.springframework.boot.autoconfigure.data.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;
import java.net.UnknownHostException;
import java.util.function.Consumer;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisConnectionConfiguration.ConnectionInfo;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Lettuce;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Pool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration.LettuceClientConfigurationBuilder;
import org.springframework.util.StringUtils;

@Configuration
@ConditionalOnClass({RedisClient.class})
class LettuceConnectionConfiguration extends RedisConnectionConfiguration {
    private final RedisProperties properties;
    private final ObjectProvider<LettuceClientConfigurationBuilderCustomizer> builderCustomizers;

    LettuceConnectionConfiguration(RedisProperties properties, ObjectProvider<RedisSentinelConfiguration> sentinelConfigurationProvider, ObjectProvider<RedisClusterConfiguration> clusterConfigurationProvider, ObjectProvider<LettuceClientConfigurationBuilderCustomizer> builderCustomizers) {
        super(properties, sentinelConfigurationProvider, clusterConfigurationProvider);
        this.properties = properties;
        this.builderCustomizers = builderCustomizers;
    }

    @Bean(
            destroyMethod = "shutdown"
    )
    @ConditionalOnMissingBean({ClientResources.class})
    public DefaultClientResources lettuceClientResources() {
        return DefaultClientResources.create();
    }

    @Bean(name = "lettuceConn")
    //@ConditionalOnMissingBean({RedisConnectionFactory.class})
    public LettuceConnectionFactory redisConnectionFactory(ClientResources clientResources) throws UnknownHostException {
        LettuceClientConfiguration clientConfig = this.getLettuceClientConfiguration(clientResources, this.properties.getLettuce().getPool());
        return this.createLettuceConnectionFactory(clientConfig);
    }

    private LettuceConnectionFactory createLettuceConnectionFactory(LettuceClientConfiguration clientConfiguration) {
        return this.getSentinelConfig() != null?new LettuceConnectionFactory(this.getSentinelConfig(), clientConfiguration):(this.getClusterConfiguration() != null?new LettuceConnectionFactory(this.getClusterConfiguration(), clientConfiguration):new LettuceConnectionFactory(this.getStandaloneConfig(), clientConfiguration));
    }

    private LettuceClientConfiguration getLettuceClientConfiguration(ClientResources clientResources, Pool pool) {
        LettuceClientConfigurationBuilder builder = this.createBuilder(pool);
        this.applyProperties(builder);
        if(StringUtils.hasText(this.properties.getUrl())) {
            this.customizeConfigurationFromUrl(builder);
        }

        builder.clientResources(clientResources);
        this.customize(builder);
        return builder.build();
    }

    private LettuceClientConfigurationBuilder createBuilder(Pool pool) {
        return pool == null?LettuceClientConfiguration.builder():(new LettuceConnectionConfiguration.PoolBuilderFactory()).createBuilder(pool);
    }

    private LettuceClientConfigurationBuilder applyProperties(LettuceClientConfigurationBuilder builder) {
        if(this.properties.isSsl()) {
            builder.useSsl();
        }

        if(this.properties.getTimeout() != null) {
            builder.commandTimeout(this.properties.getTimeout());
        }

        if(this.properties.getLettuce() != null) {
            Lettuce lettuce = this.properties.getLettuce();
            if(lettuce.getShutdownTimeout() != null && !lettuce.getShutdownTimeout().isZero()) {
                builder.shutdownTimeout(this.properties.getLettuce().getShutdownTimeout());
            }
        }

        return builder;
    }

    private void customizeConfigurationFromUrl(LettuceClientConfigurationBuilder builder) {
        ConnectionInfo connectionInfo = this.parseUrl(this.properties.getUrl());
        if(connectionInfo.isUseSsl()) {
            builder.useSsl();
        }

    }

    private void customize(LettuceClientConfigurationBuilder builder) {
        this.builderCustomizers.orderedStream().forEach((customizer) -> {
            customizer.customize(builder);
        });
    }

    private static class PoolBuilderFactory {
        private PoolBuilderFactory() {
        }

        public LettuceClientConfigurationBuilder createBuilder(Pool properties) {
            return LettucePoolingClientConfiguration.builder().poolConfig(this.getPoolConfig(properties));
        }

        private GenericObjectPoolConfig<?> getPoolConfig(Pool properties) {
            GenericObjectPoolConfig<?> config = new GenericObjectPoolConfig();
            config.setMaxTotal(properties.getMaxActive());
            config.setMaxIdle(properties.getMaxIdle());
            config.setMinIdle(properties.getMinIdle());
            if(properties.getMaxWait() != null) {
                config.setMaxWaitMillis(properties.getMaxWait().toMillis());
            }

            return config;
        }
    }
}
