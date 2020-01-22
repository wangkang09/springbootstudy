package com.wangkang.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wangkang.entity.Person;
import com.wangkang.entity.PersonExample;
import com.wangkang.service.PersonService;
import org.springframework.cache.annotation.CachePut;
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

    /**
     *
     * @Description: 按name查询数据库，并添加缓存
     *
     * @auther: wangkang
     * @date: 15:13 2019/1/9
     * @param: [name]
     * @return: java.util.List<com.wangkang.entity.Person>
     *
     */
    @ResponseBody
    @GetMapping("/person")
    public List<Person> getPersonByName(String name) {

        return personService.selectByName(name);
    }

    /**
     *
     * @Description: 按name删除数据库，并删除缓存
     *
     * @auther: wangkang
     * @date: 15:13 2019/1/9
     * @param: [name]
     * @return: int
     *
     */
    @GetMapping("/delete")
    public int deletePersonByName(String name) {
        return personService.deleteByName(name);
    }


    /**
     *
     * @Description: 更新数据库，更新缓存
     *
     * @auther: wangkang
     * @date: 15:21 2019/1/9
     * @param: [newName, oldName]
     * @return: int
     *
     */
    @GetMapping("/update")
    public int updateByName(String newName, String oldName) {
        return personService.updateByName(newName,oldName);
    }



    @ResponseBody
    @GetMapping("/person0")
    public List<Person> getPersonByName0(String name) {
        try {
            return personService.selectByName0(name);
        } catch (Exception e) {
            System.out.println("--------------------------->e.getMessage");
            System.out.println(e.getMessage());
            System.out.println("--------------------------->e.getLocalizedMessage");

            System.out.println(e.getLocalizedMessage());
            System.out.println("--------------------------->e.getCause");

            System.out.println(e.getCause());
            throw  e;
        }
    }

    @PostMapping("/insert")
    @CachePut("person")
    public Person insertPerson(@RequestBody Person person) {
        personService.insertSelective(person);
        return person;
    }


    @ResponseBody
    @GetMapping("/pageinfo")
    public Page<Person> pageInfo(int pageSize, int pageNUm) {
        //分页查询 ，紧跟在 PageHelper.startPage 方法后的第一个Mybatis查询方法会被进行分页
        Page<Person> page = PageHelper.startPage(1,10);
        page.count(true);

        personService.selectByName(null);
        System.out.println(page.getTotal());
        List<Person> ps = page.getResult();
        return page;
    }
}
