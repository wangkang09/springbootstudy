package com.wangkang;

import com.wangkang.entity.Person;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootJunitApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() {

    }

    @Test
    /*
     * @Description:
     * 1. getForEntity只能接收List，处理比较麻烦
     */
    public void getPersonByName() throws Exception {
        ResponseEntity<List> result = restTemplate.getForEntity("/person",List.class);
        Assert.assertThat((((LinkedHashMap<String, String>) result.getBody().get(0))).get("name"), Is.is("admin"));
    }

    @Test
    /*
     * @Description:
     * 1. 使用 exchange 函数代替getForEntity
     * 2. Jackson 反序列化需要无参构造函数
     */
    public void getPersonByName1() throws Exception {
        ParameterizedTypeReference<List<Person>> type = new ParameterizedTypeReference<List<Person>>() {};

        ResponseEntity<List<Person>> result = restTemplate.exchange("/person",HttpMethod.GET,null,type);

        Assert.assertThat(result.getBody().get(0).getAddress(),Is.is("nanjin2"));
    }
}
