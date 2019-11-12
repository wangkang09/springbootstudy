package com.wangkang.entity;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 15:54 2018/12/25
 * @Modified By:
 */
@Data
@Validated
public class JedisConnectionFactory {
    @Min(-11)
    private Integer dbIndex;
    private String hostName;
    private String password;
    private Integer port;
    private Integer timeout;
    @Valid //不使用也可以，最好使用
    private JedisPoolConfig poolConfig;
}
