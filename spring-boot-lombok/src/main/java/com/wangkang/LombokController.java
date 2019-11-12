package com.wangkang;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 17:26 2018/12/17
 * @Modified By:
 */
@RestController
@Slf4j
public class LombokController {


    @GetMapping("/lombok")
    public String test() {

        Student student = new Student("wk",12,"man");

        log.info("姓名：{}，年龄：{}，性别：{}",student.getName(),student.getAge(),student.getSex());

        student = Student.builder().age(11).name("yq").sex("woman").build();

        log.info("姓名：{}，年龄：{}，性别：{}",student.getName(),student.getAge(),student.getSex());

        // student = Student.builder().age(11).sex("woman").build(); 报空指针

        return "lombok";
    }
}
