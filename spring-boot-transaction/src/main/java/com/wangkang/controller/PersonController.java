package com.wangkang.controller;


import com.sun.org.apache.bcel.internal.generic.NEW;
import com.wangkang.entity.Person;
import com.wangkang.entity.PersonExample;
import com.wangkang.entity.Profiling;
import com.wangkang.mapper.PersonMapper;
import com.wangkang.mapper.ProfilingMapper;
import com.wangkang.service.PersonService;
import org.springframework.transaction.annotation.Propagation;
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

    @Resource
    PersonMapper personMapper;

    @Resource
    ProfilingMapper profilingMapper;

    @ResponseBody
    @GetMapping("/person")
    public List<Person> getPersonByName(String name) {
        PersonExample personExample = new PersonExample();
        PersonExample.Criteria criteria = personExample.createCriteria();
        criteria.andNameEqualTo("admin");
        return personService.selectByExample(personExample);
    }



    @PostMapping("/insert0")
    /**
     *
     * @Description: 有自调用问题，外层事务回滚，内层事务也回滚了
     *
     * @auther: wangkang
     * @date: 14:05 2019/1/14
     * @param: [person]
     * @return: void
     *
     */
    public void insert0(@RequestBody Person person) {
        personService.insert0(person);
    }

    @PostMapping("/insert1")
    /**
     *
     * @Description: 没有自调用问题，外层事务回滚，内层事务不回滚
     *
     * @auther: wangkang
     * @date: 14:05 2019/1/14
     * @param: [person]
     * @return: void
     *
     */
    public void insert1(@RequestBody Person person) {
        personService.insert1(person);
    }

    /**
     *
     * @Description: 因为selectByName是NEVER类型，所以报错，全部回滚
     *
     * @auther: wangkang
     * @date: 16:08 2019/1/14
     * @param: [name]
     * @return: void
     *
     */
    @GetMapping("/never")
    @Transactional
    public void testNever(String name) {
        Person person = new Person();
        person.setAge(111);
        person.setName("never");
        person.setAddress("nan");
        int coutn = profilingMapper.selectOtherDatabase();
        personMapper.insertSelective(person);
        Profiling p = profilingMapper.showProfile();
        personService.selectByName(name);
        Profiling p0 = profilingMapper.showProfile();

    }

    /**
     *
     * @Description: 测试notSupport，当insertSelective为此属性时，因为以非事务运行，可以插入！
     * 
     * @auther: wangkang
     * @date: 16:15 2019/1/14
     * @param: [person]
     * @return: void
     *
     */
    @GetMapping("/notSupport")
    @Transactional
    public void testNOT_Supported(@RequestBody Person person) {
        personMapper.insertSelective(person);//回退了

        Person person1 = new Person();
        person1.setAge(111);
        person1.setName("notSupport1");
        person1.setAddress("nan");
        personService.insertSelective(person1);//非事务插入了
    }

    /**
     *
     * @Description: 测试MANDATORY，不存在事务时，抛出异常。此方法抛出异常
     *
     * @auther: wangkang
     * @date: 16:19 2019/1/14
     * @param: [person]
     * @return: void
     *
     */
    @PostMapping("/MANDATORY")
    @Transactional(propagation = Propagation.MANDATORY)
    public void testMANDATORY(@RequestBody Person person) {
        System.out.println(1);
    }

    /**
     *
     * @Description: 测试SUPPORTS，不存在事务时，以非事务运行
     * 此方法，抛出异常，但插入数据成功
     *
     * @auther: wangkang
     * @date: 16:21 2019/1/14
     * @param: []
     * @return: void
     *
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @PostMapping("/support")
    public void testSupport(@RequestBody Person person) {
        personMapper.insertSelective(person);//没有回退

        throw new RuntimeException("测试SUPPORTS，抛出异常，但不会回退！");

    }

    /**
     *
     * @Description: 测试notRollbackfor
     * 测例，虽然报错了，但没回退
     *
     * @auther: wangkang
     * @date: 16:25 2019/1/14
     * @param: [person]
     * @return: void
     *
     */
    @Transactional(noRollbackFor = RuntimeException.class)
    @PostMapping("/notRollback")
    public void testNOrollbackFor(@RequestBody Person person) {
        personMapper.insertSelective(person);//没有回退
        throw new RuntimeException("测试notRollback，抛出异常，但不会回退！");

    }

}
