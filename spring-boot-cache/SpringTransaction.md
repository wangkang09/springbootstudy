### Spring Transaction

------

  Spring的事务机制是用统一的机制来处理不同数据访问计数的事务处理.它提供了一个**PlatformTransactionManager**接口,不同的数据访问技术使用不同的接口实现.

| 数据访问技术 | 接口实现                     |
| ------------ | ---------------------------- |
| JDBC         | DataSourceTransactionManager |
| JPA          | JpaTransactionManager        |
| Hibernate    | HibernateTransactionManager  |
| JDO          | JdoTransactionManager        |
| 分布式事务   | JtaTransactionManager        |

#### 声明式事务

------

  Spring使用`@Transactional`注解在方法上表明事务支持.被注解的方法在被调用时,会开启一个新的事务,当方法无异常完成提交后.Spring会提交事务.

  `@Transactional`注解也可以用在类上,表明这个类下的所有方法都有事务支持.如果类和方法都使用了`@Transactional`,则使用方法上的注解覆盖类级别注解.

  `@Transactional`注解是基于AOP的实现操作.

  注意:`@Transactional`注解是由Spring提供的,而不是来自javax.transaction.

  Spring提供`@EnableTransactionManagerment`注解在配置类上开启声明式事务,Spring容器会自动扫描带有注解`@Transactional`的类和方法.



#### Spring Data JPA支持

------

  Spring Data JPA对所有方法默认开启了事务支持,查询类事务默认启用readOnly-true属性.

#### Spring Boot支持

------

  Spring Boot会自动配置事务管理器.

- 当使用JDBC时,Spring Boot会自动配置DataSourceTransactionManager.
- 当使用JPA时,Spring Boot会自动配置JpaTransactionManager.

  在Spring Boot中,不需要显式开启`@EnableTransactionManagement`注解.





https://sylvanassun.github.io/2016/08/01/2016-08-1-Spring-data-transaction/