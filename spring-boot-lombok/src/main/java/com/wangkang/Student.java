package com.wangkang;

import lombok.*;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 17:26 2018/12/17
 * @Modified By:
 */
@Setter
@Getter
@Builder //创建一个构建类
@Data  // 注解在类上，将类提供的所有属性都添加get、set方法，并添加、equals、canEquals、hashCode、toString方法
@AllArgsConstructor //全参构造器
@NoArgsConstructor  //无参构造器
public class Student {


    @NonNull //如果是null抛出空指针
    private String name;
    private Integer age;
    private String sex;

}
