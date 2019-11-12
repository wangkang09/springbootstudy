package com.wangkang;

import com.wangkang.Xentity.ImportUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Description: 通过 import 导入，这里 @configuration 要不要无所谓，效果一样
 *
 * @author wangkang
 * @date: 2019-07-24
 */
@Configuration
public class ImportConfigBy {

    @PostConstruct
    public void init() {
        System.out.println("初始化 ImportConfigBy ....");
    }

    @Bean
    public ImportUser importUser() {
        return new ImportUser("wk",22);
    }

}
