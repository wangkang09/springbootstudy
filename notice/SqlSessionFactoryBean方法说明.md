| method                        | summary                 | detail                                                       |
| ----------------------------- | ----------------------- | ------------------------------------------------------------ |
| setConfiguration()            | 设置自定义mybatis配置   | Configuration：里面有一系列的值，如cacheEnabled，lazyLoadingEnabled |
| setConfigLocation()           | 设置mybatis.xml配置文件 | Resource                                                     |
| setMapperLocations()          | 设置mapper.xml配置      | Resource                                                     |
| setConfigurationProperties()  | 额外的配置              | Properties：可以用于其他配置文件中的占位符                   |
| setDataSource()               |                         |                                                              |
| setSqlSessionFactoryBuilder() |                         | This is mainly meant for testing so that mock SqlSessionFactory classes can be injected. By default, `SqlSessionFactoryBuilder` creates `DefaultSqlSessionFactory` instances. |
| setTransactionFactory()       |                         | It is strongly recommended to use the default TransactionFactory |
| buildSqlSessionFactory()      |                         | Since 1.3.0, it can be specified a `Configuration` instance directly.The default implementation uses the standard MyBatis `XMLConfigBuilder` API to build a `SqlSessionFactory` instance based on an Reader. |





```java
//设置mybatis.xml
Resource mybatisConfigXml = new PathMatchingResourcePatternResolver().getResource("classpath:mybatis.xml");
sqlSessionFactoryBean.setConfigLocation(mybatisConfigXml);

//设置mapper.xml
PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
 sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:/mapper/*Mapper.xml"));
```