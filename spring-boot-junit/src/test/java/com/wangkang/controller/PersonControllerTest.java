package com.wangkang.controller;

import com.wangkang.entity.Person;
import com.wangkang.service.PersonService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Arrays;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Description: Controller层单元测试
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PersonService personService;


    @Before
    public void setUp() throws Exception {
        //数据打桩，设置该方法返回的的值
        Mockito.when(personService.selectByExample(null)).thenReturn(Arrays.asList(new Person("ww",123),new Person("w2w",122)));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getPersonByName() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/person"))
                .andExpect(status().isOk()) // 期待返回状态吗码200
                // JsonPath expression  https://github.com/jayway/JsonPath
                .andExpect(jsonPath("$[1].name").value("ww")) // 这里是期待返回值是数组，并且第二个值的 name 存在，所以这里测试是失败的
                .andDo(print()); // 打印返回的 http response 信息
    }
}