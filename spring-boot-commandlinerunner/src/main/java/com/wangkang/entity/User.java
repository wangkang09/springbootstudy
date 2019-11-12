package com.wangkang.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 9:22 2018/12/25
 * @Modified By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private String name;
    private String sex;
    private Integer age;
}
