package com.wangkang.controller;

import com.wangkang.entity.Person;
import com.wangkang.entity.PersonExample;
import com.wangkang.mapper.PersonMapper;
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
    PersonMapper personMapper;

    @ResponseBody
    @GetMapping("/person")
    public List<Person> getPersonByName(String name) {
        PersonExample personExample = new PersonExample();
        PersonExample.Criteria criteria = personExample.createCriteria();
        criteria.andNameEqualTo("admin");
        return personMapper.selectByExample(personExample);
    }

    @PostMapping("/insert")
    public int insertPerson(@RequestBody Person person) {
        return personMapper.insertSelective(person);
    }
}
