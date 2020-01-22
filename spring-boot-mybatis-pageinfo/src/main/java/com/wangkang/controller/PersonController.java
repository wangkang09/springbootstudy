package com.wangkang.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangkang.entity.Person;
import com.wangkang.entity.PersonExample;
import com.wangkang.service.PersonService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
@EnableAutoConfiguration

public class PersonController {
    @Resource
    PersonService personService;

    @ResponseBody
    @GetMapping("/person")
    public List<Person> getPersonByName(String name) {
        PersonExample personExample = new PersonExample();
        PersonExample.Criteria criteria = personExample.createCriteria();
        criteria.andNameEqualTo("admin");
        return personService.selectByExample(personExample);
    }

    @PostMapping("/insert")
    public int insertPerson(@RequestBody Person person) {
        return personService.insertSelective(person);
    }

    @ResponseBody
    @GetMapping("/pageinfo")
    public Page<Person> pageInfo(int pageSize, int pageNUm) {
        //分页查询 ，紧跟在 PageHelper.startPage 方法后的第一个Mybatis查询方法会被进行分页
        Page<Person> page = PageHelper.startPage(1,10);
        page.count(true);
        PersonExample personExample = new PersonExample();
        PersonExample.Criteria criteria = personExample.createCriteria();
        criteria.andNameEqualTo("admin");
        personService.selectByExample(personExample);
        System.out.println(page.getTotal());
        List<Person> ps = page.getResult();
        return page;
    }

    @ResponseBody
    @GetMapping("/person")
    public List<Person> getPersonByName0(@RequestParam("name") String name) {
        PersonExample personExample = new PersonExample();
        PersonExample.Criteria criteria = personExample.createCriteria();
        criteria.andNameEqualTo("admin");
        return personService.selectByExample(personExample);
    }
}
