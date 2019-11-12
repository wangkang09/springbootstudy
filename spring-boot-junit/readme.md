[TOC]

## 创建测试类的方法

1. 进入需要测试的类
2. 选中类名
3. 使用快捷键：`alt+enter`，选择 `create test`,回车
4. 选择要测试的方法和`setUp`等特定方法

##  JUnit 注解说明
- @BeforeClass：针对所有测试，只执行一次，且必须为static void
- @Before：初始化方法，执行当前测试类的每个测试方法前执行。
- @Test：测试方法，在这里可以测试期望异常和超时时间
- @After：释放资源，执行当前测试类的每个测试方法后执行
- @AfterClass：针对所有测试，只执行一次，且必须为static void
- @Ignore：忽略的测试方法（只在测试类的时候生效，单独执行该测试方法无效）
- @RunWith:可以更改测试运行器 ，缺省值 org.junit.runner.Runner

### 一个单元测试类的执行顺序为

`@BeforeClass` –> `@Before` –> `@Test` –> `@After` –> `@AfterClass` 

### 每一个测试方法的调用顺序为 

`@Before` –> `@Test` –> `@After` 



## 超时测试

如果一个测试用例比起指定的毫秒数花费了更多的时间，那么 Junit 将自动将它标记为失败 

```java
@Test(timeout = 1000)
public void testTimeout() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("Complete");
}
```

## 异常测试

你可以测试代码是否它抛出了想要得到的异常 

```java
@Test(expected = NullPointerException.class) 
public void testNullException() { 
    throw new NullPointerException();
} 
```



## 参数化测试

Junit 4 引入了一个新的功能**参数化测试**。参数化测试允许开发人员使用不同的值反复运行同一个测试。你将遵循 5 个步骤来创建**参数化测试** 

1. 用 `@RunWith(Parameterized.class)`来注释 test 类 
2. 创建一个由 `@Parameters` 注释的**公共的静态方法**，它返回**一个对象的集合(数组)**来作为测试数据集合 
3. 创建一个公共的构造函数，它接受和一行测试数据相等同的东西 
4. 为每一列测试数据创建一个实例变量 
5. 用实例变量作为测试数据的来源来创建你的测试用例 



## Spring MVC 测试

当你想对 `Spring MVC` 控制器编写单元测试代码时，可以使用`@WebMvcTest`注解。它提供了自配置的 `MockMvc`，可以不需要完整启动 `HTTP` [服务器](https://www.baidu.com/s?wd=%E6%9C%8D%E5%8A%A1%E5%99%A8&tn=24004469_oem_dg&rsv_dl=gh_pl_sl_csd)就可以快速测试 `MVC 控制器` 

### 测试Controller

使用`@WebMvcTest`注解时，只有一部分的 Bean 能够被扫描得到，它们分别是 

1. @Controller
2. @ControllerAdvice
3. @JsonComponent
4. Filter
5. WebMvcConfigurer
6. HandlerMethodArgumentResolver 

其他常规的@Component（包括@Service、@Repository等）Bean 则不会被加载到 Spring 测试环境上下文中。所以我在上面使用了数据打桩。

### Spring Boot Web 测试

当你想启动一个完整的 HTTP 服务器对 Spring Boot 的 Web 应用编写测试代码时，可以使用@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)注解开启一个随机的可用端口。Spring Boot 针对 REST 调用的测试提供了一个 TestRestTemplate 模板，它可以解析链接服务器的相对地址。



### Spring Data JPA 测试

我们可以使用 @DataJpaTest注解表示只对 JPA 测试；@DataJpaTest注解它**只扫描@EntityBean 和装配 Spring Data JPA 存储库**，其他常规的@Component（包括@Service、@Repository等）Bean 则不会被加载到 Spring 测试环境上下文。

`@DataJpaTest` 还提供两种测试方式：

1. 使用内存数据库 `h2database`，Spring Data Jpa 测试默认采取的是这种方式；
2. 使用真实环境的数据库

#### 使用内存数据库测试

默认情况下，`@DataJpaTest`使用的是内存数据库进行测试，你无需配置和启用真实的数据库。只需要在 `pom.xml` 配置文件中声明如下依赖即可 ,test-starter里已经包含了。

```java
<dependency>
   <groupId>com.h2database</groupId>
   <artifactId>h2</artifactId>
</dependency>

testCompile('com.h2database:h2')
```

配置H2的配置文件

```java
spring:
  profiles: test
  datasource:
    url: jdbc:h2:mem:testdb;MODE=MYSQL;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false
    driver-class-name: org.h2.Driver
    username: root
    password: root
    schema-username: root
    schema-password: root
    data-username: root
    data-password: root
    schema: classpath:sql/schema.sql   //关键
    #data: classpath:sql/data.sql
    initialization-mode: always
    platform: h2
```

配置测试类

```java
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test") //指定profiles
public class PersonMapperTest {

    @Autowired
    private PersonMapper personMapper;
}
```

#### 使用真实数据库测试

```java
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test") //指定profiles
@Transactional
@Rollback(value = false)  //默认为true，防止测试污染数据库
public class PersonMapperTest {

    @Autowired
    private PersonMapper personMapper;
}
```
## 注意事项

- Jackson 反序列化需要无参构造函数





https://blog.csdn.net/qq_35915384/article/details/80227297

https://www.cnblogs.com/lori/archive/2018/09/21/9684946.html