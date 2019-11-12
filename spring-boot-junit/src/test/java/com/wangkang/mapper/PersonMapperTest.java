package com.wangkang.mapper;

import com.wangkang.entity.Person;
import com.wangkang.entity.PersonExample;
import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 13:39 2019/3/13
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test")
@Transactional
@Rollback(value = false)
public class PersonMapperTest {

    @Autowired
    private PersonMapper personMapper;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void selectByExample() {

    }

    @Test
    public void SavePerson() {
        Person p = new Person("wk",99);

        personMapper.insertSelective(p);

        PersonExample personExample = new PersonExample();

        List<Person> list = personMapper.selectByExample(personExample);

        //assertThat(list.size(), Is.is(1));

        //assertThat(list.get(0).getAge(),Is.is(99));

    }

}