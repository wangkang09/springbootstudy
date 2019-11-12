package com.wangkang.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangkang.entity.Person;
import com.wangkang.entity.PersonExample;
import com.wangkang.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    CacheManager cacheManager;

    @PostMapping("/insert")
    @CachePut(value = "person",key = "#person.id")
    public Person insertPerson(@RequestBody Person person) {
        System.out.println(cacheManager.getClass());
        //return personService.insertSelective(person);
        return person;
    }

    @Cacheable(value = "person",key = "#id")
    @GetMapping("person")
    @Transactional
    public Person getCache(Long id) {
        Person person1 = new Person();
        person1.setId(id);

        personService.method2(person1);

        personService.method1(person1);
        return new Person();
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
    @PostMapping("/insert00")
    @Transactional
    public Person insertPerson0(@RequestBody Person person) {

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

}
