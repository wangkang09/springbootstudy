package com.wangkang;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;
@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
/**
 *
 * @Description:
 * 1.如果为MOCk模式，放开port注释，会报错，因为根本没有port
 * 2.此例子启动了完整的Spring上下文，但没有启动Server
 * @auther: wangkang
 * @date: 9:49 2019/1/14
 * @param:
 * @return:
 *
 */
public class SpringBootMockApplicationTests {

//	@LocalServerPort
//	private int port;

	@Resource
	MockMvc mockMvc;

	@Test
	public void contextLoads() throws Exception {
//		System.out.println("port ：" + port);
		this.mockMvc.perform(get("/hello")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello World")));

	}

}

