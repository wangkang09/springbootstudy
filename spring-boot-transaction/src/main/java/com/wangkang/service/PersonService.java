package com.wangkang.service;

import com.wangkang.entity.Person;
import com.wangkang.entity.PersonExample;
import org.springframework.cache.annotation.CachePut;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 14:01 2019/1/8
 * @Modified By:
 */
public interface PersonService {

    long countByExample(PersonExample example);

    int deleteByExample(PersonExample example);

    int insert(Person record);
    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    int insertSelective(Person record);

    List<Person> selectByExample(PersonExample example);

    int updateByExampleSelective(Person record, PersonExample example);

    int updateByExample(Person record, PersonExample example);

    //@Transactional(propagation = Propagation.REQUIRED)
    void insert0(Person person);

    //@Transactional(propagation = Propagation.REQUIRED)
    void insert1(Person person);

    List<Person> selectByName(String name);
}
