package com.wangkang;

import com.wangkang.controller.PersonController;
import com.wangkang.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
/**
 *
 * @Description:
 * 1.通过@WebMvcTest指定启动哪个controller
 * 2.通过@MockBean指定注入的bean
 * 3.有利于测试单个controller
 * @auther: wangkang
 * @date: 9:49 2019/1/14
 * @param:
 * @return:
 *
 */
public class SpringBootMockApplicationTests1 {
//	@MockBean
//	PersonService personService;
	@Resource
	MockMvc mockMvc;

	@Test
	public void contextLoads() throws Exception {
		this.mockMvc.perform(get("/hello")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello World")));

	}
	@Test
	public void testMock() throws Exception {
		this.mockMvc.perform(get("/mock")).andDo(print()).andExpect(status().isOk());
	}

}

