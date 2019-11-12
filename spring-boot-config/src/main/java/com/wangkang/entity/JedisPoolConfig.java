package com.wangkang.entity;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 15:52 2018/12/25
 * @Modified By:
 */
@Data
@Validated
public class JedisPoolConfig {
    @Min(1)
    private Integer maxIdle;
    private Integer minIdle;
    private Integer maxActive;
    private Integer maxWait;

}
