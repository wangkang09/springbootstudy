package com.wangkang.service;

import com.alibaba.fastjson.JSONObject;
import com.wangkang.SpringBootCacheApplication;
import com.wangkang.entity.Person;
import com.wangkang.filter.MyCacheFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 16:40 2019/1/9
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableCaching
//@AutoConfigureMockMvc
public class PersonServiceTest {

    //@Resource
    private MockMvc mockMvc;

    @Resource
    MyCacheFilter myCacheFilter;

    @Autowired
    private WebApplicationContext wac;

    //    @Autowired
//    private MockHttpSession session;// 注入模拟的http session
//
//    @Autowired
//    private MockHttpServletRequest request;// 注入模拟的http request\

    @Before // 在测试开始前初始化工作
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac)
                .addFilters(myCacheFilter).build();
    }


    @Test
    public void countByExample() throws Exception {
    }

    @Test
    public void deleteByExample() throws Exception {
    }

    @Test
    public void insert() throws Exception {
        int count = 10;
        Person p = null;
        for (int i = 0; i < count; i++) {
            p = new Person();
            p.setName("wang1"+i);
            p.setAddress("nanjin1"+i);
            p.setAge(20+i);
            String json = JSONObject.toJSONString(p);
            mockMvc.perform(post("/insert").contentType(MediaType.APPLICATION_JSON_UTF8).content(json).servletPath("/insert"))
                    .andExpect(status().isOk())// 模拟向testRest发送get请求
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                    .andReturn();// 返回执行请求的结果
        }
    }

    @Test
    public void deleteByName() throws Exception {
    }

    @Test
    public void insertSelective() throws Exception {
    }

    @Test
    public void selectByName() throws Exception {
    }

    @Test
    public void selectByExample() throws Exception {
    }

    @Test
    public void updateByExampleSelective() throws Exception {
    }

    @Test
    public void updateByExample() throws Exception {
    }

    @Test
    public void updateByName() throws Exception {
    }

    @Test
    public void selectByName0() throws Exception {
    }

}