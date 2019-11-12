package com.wangkang.javaX;

import com.wangkang.vo.Student;
import com.wangkang.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 14:41 2018/12/19
 * @Modified By:
 */
@Slf4j
public class ValidatorUtilTest {

    static Logger logger = LoggerFactory.getLogger(ValidatorUtilTest.class);
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    //级联验证,validator中设置了failFast
    public void validateEntity() throws Exception {

        //Student student = Student.builder().age(8).name("").build();
        //Student student = Student.builder().age(8).name("wk").user(User.builder().sex("1").age(8).build()).build();
        Student student = Student.builder().age(8).name("").build();
        //在ValidateEntity中设置分组信息
        ValidatorUtil.ValidationResult result = ValidatorUtil.ValidateEntity(student);

        if(result.isHasErrors()) {
            for (Map.Entry<String, String> entry : result.getErrorMsgs().entrySet()) {
                log.error(entry.getKey()+ "：" + entry.getValue());
                logger.error(entry.getKey()+ "：" + entry.getValue());
            }
        }
    }

    @Test
    public void validateProperty() throws Exception {
        //只输出了name
        Student student = Student.builder().password("12").build();

        ValidatorUtil.ValidationResult result = ValidatorUtil.ValidateProperty(student,"password");

        if(result.isHasErrors()) {
            for (Map.Entry<String, String> entry : result.getErrorMsgs().entrySet()) {
                log.error(entry.getKey()+ "：" + entry.getValue());
                logger.error(entry.getKey()+ "：" + entry.getValue());
            }
        }
    }

    @Test
    public void UserClass() throws Exception {
        //只输出了name
        //类级别的校验使默认校验失效了
        User user = User.builder().sex("man").build();
        //User user = new User();
       user.setSex("man");

        ValidatorUtil.ValidationResult result = ValidatorUtil.ValidateEntity(user);

        if(result.isHasErrors()) {
            for (Map.Entry<String, String> entry : result.getErrorMsgs().entrySet()) {
                log.error(entry.getKey()+ "：" + entry.getValue());
                logger.error(entry.getKey()+ "：" + entry.getValue());
            }
        }
    }


}