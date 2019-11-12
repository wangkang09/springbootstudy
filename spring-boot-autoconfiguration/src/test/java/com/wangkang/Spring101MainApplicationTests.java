package com.wangkang;

import com.wangkang.zhuye.AbstractMyService;
import com.wangkang.zhuye.MyAutoConfiguration;
import com.wangkang.zhuye.MyService;
import org.junit.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 17:14 2019/2/15
 * @Modified By:
 */
public class Spring101MainApplicationTests {

    private ApplicationContextRunner applicationContextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(MyAutoConfiguration.class));

    @Test
    //V1 Hello >> zhuye:35 !!
    //匹配到了v1，且没有用application.properties中的值，如果把.withPropertyValues去掉，报错!
    public void testService() {
        applicationContextRunner
                .withPropertyValues("spring101.age=35")
                .withPropertyValues("spring101.name=zhuye")
                .run(context -> {
                    assertThat(context).hasSingleBean(AbstractMyService.class);
                    assertThat(context.getBean(AbstractMyService.class).hello()).containsSequence("zhuye:35");
                    System.out.println(context.getBean(MyService.class).hello());
                });

    }


    @Test
    public void testConditionalOnProperty() {
        applicationContextRunner
                .run(context -> {
                    assertThat(context).hasSingleBean(AbstractMyService.class);
                    assertThat(context.getBean(AbstractMyService.class).hello()).containsSequence("V1 Hello");
                    System.out.println(context.getBean(AbstractMyService.class).hello());
                });

        applicationContextRunner
                .withPropertyValues("spring101.version=v1")
                .run(context -> {
                    assertThat(context).hasSingleBean(AbstractMyService.class);
                    assertThat(context.getBean(AbstractMyService.class).hello()).containsSequence("V1 Hello");
                    System.out.println(context.getBean(AbstractMyService.class).hello());

                });

        applicationContextRunner
                .withPropertyValues("spring101.version=v2")
                .run(context -> {
                    assertThat(context).hasSingleBean(AbstractMyService.class);
                    assertThat(context.getBean(AbstractMyService.class).hello()).containsSequence("V2 Hello");
                    System.out.println(context.getBean(AbstractMyService.class).hello());
                });
    }



    @Test

    public void testConditionalOnMissingBean() {

        applicationContextRunner

                .withUserConfiguration(MyServiceConfig.class)

                .run(context -> {

                    assertThat(context).hasSingleBean(MyService.class);//因为autoconfigure中注解了OnMissingBean

                    assertThat(context.getBean(MyService.class).hello()).containsSequence("V1 Hi");

                    System.out.println(context.getBean(MyService.class).hello());

                });
    }

}

@Configuration
class MyServiceConfig {
    @Bean
    MyService getService() {

        return new MyService("Hi");
    }
}
