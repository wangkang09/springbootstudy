package com.wangkang.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 21:05 2018/12/25
 * @Modified By:
 */
@ConfigurationProperties
@Data
public class EnableConfig0 {
    private String enableName;
    private String enableSex;
}
