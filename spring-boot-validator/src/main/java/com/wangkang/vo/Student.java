package com.wangkang.vo;

import com.wangkang.javaX.customCheckRules.NotEmpty;
import com.wangkang.javaX.group.student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 21:21 2018/12/18
 * @Modified By:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {


    //测试分组
    @Min(value = 10,message = "年龄最小10岁",groups = {student.class})
    private Integer age;

    //@Size (min=3, max=20, message="用户名长度只能在3-20之间")
    @NotEmpty
    //@javax.validation.constraints.NotEmpty
    private String name;

    //@Size(min=6, max=20, message="wk ${user.password.null} ${min==7?'kk':'ll'} ${max==2?'true':'false'}")
    @NotEmpty
    private String password;

    //@Pattern(regexp="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message="邮件格式错误")
    private String email;

    //@NotNull
    //@Valid
    //@ConvertGroup(from=student.class, to=Social.class) //当Student的分组校验是student时，user为Social
    //private User user;
}
