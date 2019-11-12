package com.wangkang.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangkang.config.AnotherRedisConnectionFactory;
import com.wangkang.config.TestConfig;
import com.wangkang.entity.Person;
import com.wangkang.entity.PersonExample;
import com.wangkang.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.data.redis.JedisConfg;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 13:39 2019/1/2
 * @Modified By:
 */
@RestController
public class PersonController {
    @Resource
    PersonService personService;

    @Autowired
    AnotherRedisConnectionFactory j;

    @Autowired
    JedisConfg jedisConfg; //not found

    @Autowired
    @Qualifier("myJedisConnectFactory")
    RedisConnectionFactory redisConnectionFactory;

    @Autowired
    TestConfig testConfig;

    @Autowired
    CacheManager cacheManager;

    @ResponseBody
    @GetMapping("/person")
    @Cacheable(value = "person",key = "#id")
    public Person getPersonByName(Long id) {
        PersonExample personExample = new PersonExample();
        PersonExample.Criteria criteria = personExample.createCriteria();
        criteria.andIdEqualTo(id);
        return personService.selectByExample(personExample).get(0);
    }

    /**
     *
     * @Description: 测试cache是否是事务型（可以查看spring-boot-cache-transaction模块中的readme.md里的说明）cache
     *  结论：不是事务型，cache成功，外部事务失败，cache不回退
     * @auther: wangkang
     * @date: 15:34 2019/1/17
     * @param: [person]
     * @return: com.wangkang.entity.Person
     *
     */
    @PostMapping("/insert")
    @Transactional
    public Person insertPerson(@RequestBody Person person) {

        personService.insertSelective(person);

        Person person1 = new Person();
        person1.setId(person.getId()+1);
        person1.setAddress(person.getAddress());
        person1.setName(person.getName());
        person1.setAge(person.getAge());

        personService.method0(person1);

        personService.method1(person1);

        return person;
    }



    @PostMapping("/insert0")
    @CachePut(value = "person",key = "#person.id")
    public Person insertPerson0(@RequestBody Person person) {
        System.out.println(cacheManager.getClass());
        //return personService.insertSelective(person);
        return person;
    }

    @Cacheable(value = "person",key = "#id")
    @GetMapping("person0")
    public Person getCache(Long id) {
        Cache cache = cacheManager.getCache("my");
        cache.put("wang","kang");
        System.out.println(cache.get("wang"));
        return new Person();
    }

}
