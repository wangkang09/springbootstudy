package com.wangkang.service;

import com.wangkang.entity.Person;
import com.wangkang.entity.PersonExample;

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

    int insertSelective(Person record);

    List<Person> selectByExample(PersonExample example);

    int updateByExampleSelective(Person record, PersonExample example);

    int updateByExample(Person record, PersonExample example);

    void mock();
}
