package com.wangkang.controller;

import com.wangkang.entity.Person;
import com.wangkang.entity.PersonExample;
import com.wangkang.service.PersonService;
import com.wangkang.util.CacheUtil;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    CacheUtil cacheUtil;



    //---------------以下将使用redis代替hashMap作为cache，这样可以模拟redis出错的情况

    /**
     *
     * @Description:
     * // 数据库正常，缓存异常（将redis关闭了）
     * 1. 事务优先级大
     *  1.1 tx -> insert -> cacheRedis -> tx commit
     *  1.2 若cacheRedis失败
     *      1. 事务等待cache的完成，最后都失败，回滚
     *
     * 2. cache优先级大
     *  2.1 tx -> insert -> tx commit -> cacheRedis
     *  2.2 若cacheRedis失败
     *      1. 事务已经先提交了，但是缓存了没有，且客户端失败
     *
     * // 数据库异常，缓存正常（调试情况下，执行sql后，将数据库关闭）
     * 3. 事务优先级大
     *  3.1 tx -> insert -> cacheRedis -> tx commit
     *  3.2 若tx commit失败
     *      1. cacheRedis完成，数据库没插入
     *
     * 4. cache优先级大
     *  4.1 tx -> insert -> tx commit -> cacheRedis
     *  4.2 Could not commit JDBC transaction;
     *      1. cacheRedis未完成
     *
     *
     * 5. 综上：
     *  3.1 当数据库正常，cache失败时
     *      1. 若事务优先级高，则事务要等待cache，最后数据一致
     *      2. 若缓存优先级高，则事务先提交了，最后数据不一致
     *
     *
     *
     * @auther: wangkang
     * @date: 14:37 2019/1/15
     * @param: [person]
     * @return: com.wangkang.entity.Person
     *
     */
    @PostMapping("/insertRedis")
    @Transactional
    @CachePut(value = "person",key = "#person.id")
    public Person insertRedisPerson(@RequestBody Person person) {

        personService.insertSelective(person);

        //personService.insertSelective(person);
        //cacheUtil.cacheDetails();//进入这里后的缓存信息

        return person;
    }



    /**
     *
     * @Description: 如果数据库没查到时，缓存中有id，value是[]
     * //缓存中不是list，如果直接取缓存的话，会包entity转list出错！
     * @auther: wangkang
     * @date: 17:32 2019/1/14
     * @param: [id]
     * @return: java.util.List<com.wangkang.entity.Person>
     *
     */
    @ResponseBody
    @GetMapping("/person")
    @Cacheable(value = "person",key = "#id")
    public List<Person> getPersonByName(Long id, Integer selectCount) {
        cacheUtil.cacheDetails();//进入这里后的缓存信息

        PersonExample personExample = new PersonExample();
        PersonExample.Criteria criteria = personExample.createCriteria();
        criteria.andIdEqualTo(id);
        List<Person> personList = personService.selectByExample(personExample);

        if (selectCount == 0) {
            //do nothing
        }else {
            throw new RuntimeException("测试cacheable,当异常时的情况");
        }

        return personList;
    }

    /**
     *
     * @Description: 测试cachePut和事务的顺序不同造成的影响，等方法执行完后，才放入缓存
     * 1. putCount==0
     *  1.1 controller的时候没有缓存
     *  1.2 filter的时候有一个了
     * 2. putCount!=0
     *  2.1 没有插入数据库
     *  2.2 没有插入缓存
     *
     * @auther: wangkang
     * @date: 17:07 2019/1/14
     * @param: [person, putCount]
     * @return: com.wangkang.entity.Person
     *
     */
    @PostMapping("/insert")
    @Transactional
    @CachePut(value = "person",key = "#person.id")
    @ResponseBody
    public Person insertPerson(@RequestBody Person person,@RequestParam("putCount") Integer putCount) {
        personService.insertSelective(person);

        cacheUtil.cacheDetails();//进入这里后的缓存信息
        if(putCount == 0) {
            //do nothing
        } else {
            throw new RuntimeException("报错，看是否将数据加入缓存");
        }
        return person;
    }


    /**
     *
     * @Description: 测试CacheEvict和事务的顺序不同造成的影响，等方法执行完后，才删除缓存
     * 1. deleteCount!=0时，数据库删除失败，也没删除缓存
     * 2. 当beforeInvocation == true时，到方法内时，缓存已经清空了，最后抛出异常，数据库没清空，这里出现不一致情况，但是没关系
     * @auther: wangkang
     * @date: 17:07 2019/1/14
     * @param: [person, deleteCount]
     * @return: void
     *
     */
    @Transactional
    @PostMapping("/delete")
    @CacheEvict(value = "person",key = "#person.id",beforeInvocation = true)
    public void deletePerson(@RequestBody Person person,@RequestParam("deleteCount") Integer deleteCount) {
        PersonExample personExample = new PersonExample();
        PersonExample.Criteria criteria = personExample.createCriteria();
        criteria.andIdEqualTo(person.getId());
        personService.deleteByExample(personExample);

        cacheUtil.cacheDetails();//进入这里后的缓存信息

        if(deleteCount == 0) {
            //do nothing
        } else {
            throw new RuntimeException("报错，看是否将数据加入缓存");
        }
    }


    //--------以上测试的是方法内异常情况，不管优先级如何，因为cache和事务都在方法返回后再操作，由于方法根本没返回，所以都回退了，所以效果一样
    //--------以下测试的是方法返回正常，但是JDBC报错的情况，经测试，与顺序无关！



    /**
     *
     * @Description:
     * 1. cache优先级高：出现主键冲突的时候，数据库没插入，但是缓存插入了
     * 2. tx优先级高：缓存还是插入了
     * 3. 原因：因为catch已经捕获了，最后事务是提交的，哈哈，，
     * @auther: wangkang
     * @date: 10:46 2019/1/15
     * @param: [person]
     * @return: com.wangkang.entity.Person
     *
     */
    @Transactional
    @CachePut(value = "person",key = "#person.id")
    @PostMapping("/insertPerson")
    public Person insertPerson(@RequestBody Person person) {

        try {
            personService.insertSelective(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //personService.insertSelective(person);
        cacheUtil.cacheDetails();//进入这里后的缓存信息

        return person;
    }


    //-----------以下是嵌套调用的情况，嵌套调用，顺序无关！

    /**
     *
     * @Description:
     * //无异常情况
     * 1. 事务优先级大
     *  1.1 tx -> cache -> insert -> tx commit
     * 2. 缓存优先级大
     *  2.1 和1 一样
     *
     * //数据库异常情况
     * 1. 缓存优先级大，数据库插入失败，缓存成功
     * 2. 事务优先级大，一样。。。。。。。。。。。。。。
     *
     * ------------------------以上分析完全不成立，不管哪个优先级大，都是先开启事务呀！
     *
     * // spring原生Cache测试
     * 1. 数据库异常（断开连接）后，数据已经进入缓存，数据库没有，造成数据不一致
     *
     * // redisCache测试
     * 1. 数据库异常（断开连接）后，数据已经进入缓存，数据库没有，造成数据不一致
     *
     * 综上：cache没有加入事务
     * @auther: wangkang
     * @date: 13:33 2019/1/15
     * @param: [person]
     * @return: com.wangkang.entity.Person
     *
     */
    @PostMapping("/insertPerson2")
    @Transactional
    public Person insertPerson2(@RequestBody Person person) {

        //cacheUtil.cacheDetails();//进入这里后的缓存信息

        personService.cachePerson(person);

        //cacheUtil.cacheDetails();//进入这里后的缓存信息

        personService.insertSelective(person);

        if (true) {
            throw new RuntimeException("报错，测试数据一致性");
        }

        //cacheUtil.cacheDetails();//进入这里后的缓存信息

        return person;
    }


    @ResponseBody
    @GetMapping("/selectTransaction")
    @Transactional
    @Cacheable(value = "person",key = "#person.id")
    public Person selectTransaction(@RequestBody Person person) {
        System.out.println("进入方法内了");
        person.setAge(100);
        return person;
    }


}
