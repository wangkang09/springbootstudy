package com.wangkang.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangkang.entity.Person;
import com.wangkang.entity.PersonExample;
import com.wangkang.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;
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

    @ResponseBody
    @GetMapping("/person")
    @CachePut(value = "person",key = "#person.id")
    public Person getPersonByName(@RequestBody Person person) {


        System.out.println(cacheManager.getClass().toString());

        Cache cache1 =  cacheManager.getCache("person");
        cache1.put("k","w");
        String w = (String) cache1.get("k").get();

        Cache cache =  cacheManager.getCache("wang");
       cache.put("w","k");

        return person;
    }

    @PostMapping("/insert")
    @Cacheable(value = "person",key = "#person.id")
    public Person insertPerson(@RequestBody Person person) {
        personService.insertSelective(person);

        if (cacheManager instanceof AbstractTransactionSupportingCacheManager) {
            System.out.println("可以事务");
        }
        return person;
    }


}
