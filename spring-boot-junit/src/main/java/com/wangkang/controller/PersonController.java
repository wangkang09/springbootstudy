package com.wangkang.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangkang.entity.Person;
import com.wangkang.entity.PersonExample;
import com.wangkang.service.PersonService;
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

}
