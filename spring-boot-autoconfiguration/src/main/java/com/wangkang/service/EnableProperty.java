package com.wangkang.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 17:26 2019/2/15
 * @Modified By:
 */
@ConfigurationProperties
@Data
public class EnableProperty {
    private String SerName;
}
