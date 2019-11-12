package com.wangkang.service.impl;

import com.wangkang.entity.Person;
import com.wangkang.entity.PersonExample;
import com.wangkang.mapper.PersonMapper;
import com.wangkang.service.PersonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    PersonService personService;

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
    //重新开启一个事务
    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public int insertSelective(Person record) {
         int count = personMapper.insertSelective(record);

         if (true) {
             throw new RuntimeException("测试NOT_SUPPORTED，抛出异常！");
         }
         //如果为NOT_SUPPORTED则插入成功
        return count;
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

    @Override
    //这里有自调用问题，insertSelective()方法是原生方法，而不是代理方法
    @Transactional(propagation = Propagation.REQUIRED)
    public void insert0(Person person) {
        insertSelective(person);

        Person person1 = new Person();
        person1.setName("wkinsert0");
        person1.setAddress("nanjin");
        person1.setAge(20);

        personMapper.insertSelective(person1);

        if (true) {
            throw new RuntimeException("insert0 出错了！");
        }
    }

    @Override
    //注入了代理类
    @Transactional(propagation = Propagation.REQUIRED)
    public void insert1(Person person) {

        personService.insertSelective(person);
        Person person1 = new Person();
        person1.setName("wkinsert1");
        person1.setAddress("nanjin");
        person1.setAge(20);

        personMapper.insertSelective(person1);

        if (true) {
            throw new RuntimeException("insert1 出错了！");
        }
    }

    @Override
    //@Transactional(propagation = Propagation.NEVER)
    public List<Person> selectByName(String name) {
        return personMapper.selectByName(name);
    }
}
