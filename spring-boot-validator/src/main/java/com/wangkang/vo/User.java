package com.wangkang.vo;

import com.wangkang.javaX.customCheckRules.CheckPassword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 17:34 2018/12/18
 * @Modified By:
 */
@Data
@Builder
@CheckPassword //类级别校验，里面设置会覆盖默认校验
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @NotNull

    private String name;
    private String sex;
    @Max(50)
    @NotNull
    private Integer age;
}
