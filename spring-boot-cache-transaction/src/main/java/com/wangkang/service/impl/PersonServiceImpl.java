package com.wangkang.service.impl;

import com.wangkang.entity.Person;
import com.wangkang.entity.PersonExample;
import com.wangkang.mapper.PersonMapper;
import com.wangkang.service.PersonService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 14:02 2019/1/8
 * @Modified By:
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Resource
    PersonMapper personMapper;

    @Override
    public long countByExample(PersonExample example) {
        return personMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(PersonExample example) {
        return personMapper.deleteByExample(example);
    }

    @Override
    public int insert(Person record) {
        return personMapper.insert(record);
    }

    @Override
    public int insertSelective(Person record) {
        return personMapper.insertSelective(record);
    }

    @Override
    public List<Person> selectByExample(PersonExample example) {
        return personMapper.selectByExample(example);
    }

    @Override
    public int updateByExampleSelective(Person record, PersonExample example) {
        return personMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(Person record, PersonExample example) {
        return personMapper.updateByExample(record,example);
    }

    /**
     *
     * @Description: 缓存person
     *
     * @auther: wangkang
     * @date: 13:17 2019/1/15
     * @param: [person]
     * @return: com.wangkang.entity.Person
     *
     */
    @Override
    @CachePut(value = "person",key = "#person.id")
    public Person cachePerson(Person person) {
        System.out.println("准备缓存person");
        return person;
    }

}
