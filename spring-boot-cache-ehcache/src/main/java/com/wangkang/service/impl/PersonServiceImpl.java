package com.wangkang.service.impl;

import com.wangkang.entity.Person;
import com.wangkang.entity.PersonExample;
import com.wangkang.mapper.PersonMapper;
import com.wangkang.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
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

    @Autowired
    CacheManager cacheManager;

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
     * @Description: 第0个方法成功，如果第1个报错，查看数据情况
     *
     * @auther: wangkang
     * @date: 17:05 2019/1/15
     * @param: [person]
     * @return: com.wangkang.entity.Person
     *
     */
    @Override
    @CachePut(value = "person",key = "#person1.id")
    public Person method0(Person person1) {
        personMapper.insertSelective(person1);
        return person1;
    }

    @Override
    public void method1(Person person1) {
        if (true) {
            throw new RuntimeException("method1抛异常，测试数据一致性");
        }
    }

    @Override
    public Person method2(Person person2) {

        Cache cache = cacheManager.getCache("person");
        cache.put("wang","kang");
        return person2;
    }
}
