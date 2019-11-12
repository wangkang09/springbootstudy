package com.wangkang;

import com.wangkang.config.UserServiceAutoConfiguration;
import com.wangkang.service.UserService;
import org.junit.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 15:24 2019/2/15
 * @Modified By:
 */
public class ApplicationContextRunnerTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(UserServiceAutoConfiguration.class));

    @Test
    public void defaultServiceBacksOff() {
        this.contextRunner.withUserConfiguration(UserConfiguration.class)
                .run((context) -> {
                    assertThat(context).hasSingleBean(UserService.class);
                    assertThat(context.getBean(UserService.class)).isSameAs(
                            context.getBean(UserConfiguration.class).myUserService());
                });
    }

    @Configuration
    static class UserConfiguration {

        @Bean
        public UserService myUserService() {
            return new UserService("mine");
        }
    }

    @Test
    public void serviceNameCanBeConfigured() {
        //将 user.name = test123,加入属性文件
        this.contextRunner.withPropertyValues("SerName:test123").run((context) -> {
            assertThat(context).hasSingleBean(UserService.class);
            assertThat(context.getBean(UserService.class).getSerName()).isEqualTo("test123");
        });
    }
}
