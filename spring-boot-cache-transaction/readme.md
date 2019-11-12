### 注意
1. 如果 cacheUtil.cacheDetails(); 方法保存，注释掉即可，无影响

### 集成步骤
1. 在入口(main)类上加@EnableTransactionManagement注解，开启事务
2. 在入口类上加@EnableCaching主键，开启缓存
3. 添加start依赖
4. 如果添加了redis依赖（不需要配置文件说明），就会自动集成redis，而不是用默认的 hashMap形式


### 测试总结（两个注解放在一个方法上的测试）
1. 如果方法内抛出异常，则缓存和事务都不成功（这时顺序没有意义）
2. 如果cacheable命中缓存，即使方法上加了事务也不会执行
3. order顺序有意义的情况是，方法返回后，某个(cache或transaction)发生异常的情况

### 测试总结第3条 理论 说明
- 哪个优先级高，哪个后提交

#### cache优先级大，cache失败
- 因为cache没加入事务，所以事务能成功，缓存失败

#### cache优先级大，transaction失败
- 因为transaction失败，报异常，所以执行异常逻辑，cache也会失败

#### transaction优先级大，cache失败
- 因为cache失败，抛出异常，走异常逻辑，transaction也回退了

#### transaction优先级大，transaction失败（关键）
- 如果cache是事务型cache（即cache加入了事务），则transaction失败，cache也失败
- 如果cache是非事务型（即cache不加入事务），则cache缓存成功


### 以下是混乱笔记

### 测试cache和transaction同时注解时存在的问题
- cacheAble和cachePut当方法中发生异常时，对缓存都没操作，当然，对缓存的操作是在返回值后！
- cacheEvict如果beforeInvocation == true，则先删除缓存，所以方法中执行异常和删除缓存没关系

### 问题
- 即使cache的优先级大于transaction，也一样，直到执行完方法才操作缓存
- 就是说，操作缓存出问题了，肯定抛异常，则数据库肯定也回滚
- 如果说，缓存没问题，数据库出问题了，缓存还是有值的（新：缓存还是没有值的）
- 若，先数据库，再缓存
    - 数据库出问题，缓存肯定不走了，数据是一致的
    - 数据库没出问题，缓存出问题了，数据还是一致的
- 所以，要先事务，后缓存
- 新：以上内容都是错的
    - 在方法中抛出异常时，不管是数据库或是缓存都是没有操作的。这两个操作（cache的put和evict(before==false)）都是在返回值之后才操作的
    
### 真正有问题的情况
- 简单的两个注解配置在一个方法上，order顺序是无所谓的！



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