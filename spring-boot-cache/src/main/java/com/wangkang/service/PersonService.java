package com.wangkang.service;

import com.wangkang.entity.Person;
import com.wangkang.entity.PersonExample;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

    @CacheEvict("person") //默认先删除数据库，再使缓存失效
    int deleteByName(String name);

    //@CachePut("person") //如果注解放在这，缓存的value是int型
    int insertSelective(Person record);

    @Cacheable("person")
    List<Person> selectByName(String name);

    List<Person> selectByExample(PersonExample example);

    int updateByExampleSelective(Person record, PersonExample example);

    int updateByExample(Person record, PersonExample example);

    @CachePut(value = "person",key = "#newName") //表示不查询缓存，更新数据库，之后再更新缓存
    int updateByName(String newName, String oldName);

    @Cacheable(value = "person",key = "#name")
    List<Person> selectByName0(String name);
}
