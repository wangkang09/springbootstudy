package com.wangkang.controller;

import com.wangkang.javaX.customCheckRules.NotEmpty;
import com.wangkang.javaX.group.Social;
import com.wangkang.vo.Student;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import static org.springframework.util.StringUtils.isEmpty;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 21:20 2018/12/18
 * @Modified By:
 */
@RestController
@Slf4j
@Validated //对类进行这个注解时，类中就可用相关注解了
public class SpringframeworkController {

    //这个初始化方法是覆盖config中的配置
//    @InitBinder
//    public void initBander(DataBinder binder) {
//        binder.setValidator(new StudentValidator());
//    }


    @PostMapping("/validator2")
    public String validator2(@RequestBody Student student, Errors errors) {

        if (isEmpty(student.getName())) {
            errors.rejectValue("name","01","name is empty");
        }

        if (isEmpty(student.getAge())) {
            errors.rejectValue("age","02","age is empty");
        } else {
            if (student.getAge()>30) {
                errors.rejectValue("age","03","age is too large");
            }
        }
        log.info(errors.toString());
        return errors.toString();
    }

    @PostMapping("/springValidator")
    public String springValidator(@RequestBody @Validated({Social.class})  Student student, BindingResult result) {
        if (result.hasErrors()) {
            log.info(result.getAllErrors().toString());
            return result.getAllErrors().toString();       }
        return "success";
    }

    /**如果只有少数对象，直接把参数写到Controller层，然后在Controller层进行验证就可以了。*/
    //BindResult不能用在@requestParam后，只能通过全局异常来处理了
    @RequestMapping(value = "/demo3", method = RequestMethod.GET)
    public @NotEmpty String demo3(@Range(min = 1, max = 9, message = "{min},年级只能从1-9")
                      @RequestParam(name = "grade", required = true)
                              int grade,
                 @Min(value = 1, message = "班级最小只能1")
                      @Max(value = 99, message = "班级最大只能99")
                      @RequestParam(name = "classroom", required = true)
                              int classroom) {

        System.out.println(grade + "," + classroom);
        return "";
    }
}
