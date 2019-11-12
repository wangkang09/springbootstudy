package com.wangkang.importConfig;

import com.wangkang.ImportConfigBy;
import com.wangkang.ImportNormal;
import com.wangkang.ImportRegistrarMy;
import com.wangkang.ImportSeletorMy;
import com.wangkang.Xentity.ImportConfigEntity;
import com.wangkang.doubleDefinClassAndMethod.DefinClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * Description: 测试 import 注解
 *
 * @author wangkang
 * @date: 2019-07-24
 */
@Configuration
@Import(value = {DefinClass.class,ImportConfigBy.class, ImportNormal.class, ImportRegistrarMy.class, ImportSeletorMy.class})
public class ImportConfig {

    @Bean
    public ImportConfigEntity importConfigEntity() {
        return new ImportConfigEntity("wkka",65);
    }
    @PostConstruct
    public void init() {
        System.out.println("初始化 ImportConfig ....");
    }
}
