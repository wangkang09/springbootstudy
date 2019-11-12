### XML配置文件导入
ImportResouce有两种常用的引入方式：classpath和file，具体查看如下的例子：
classpath路径：locations={"classpath:application-bean1.xml","classpath:application-bean2.xml"}
file路径：locations= {"file:d:/test/application-bean1.xml"};

### 外部配置文件使用
- 可以使用 command-line 来指定外部配置文件
- 或者再bootstrap.properties中指定，不能在application.properties中指定，因为指定配置文件要做这之前

### @EnableConfigurationProperties 与 @ConfigurationProperties
- enable类中注入ConfigurationProperties注解类，这两个配合使用
- 这个完全可以在ConfigurationProperties时使用@Configuration注解，直接注入容器，再在其他配置类中注入
- 关键是：ConfigurationProperties只是把属性注入了类，但是没哟把类注入容器
- 如果不使用EnableConfigurationProperties，则不能得到该实例

### random使用
- 只调用一次，都次调用得到的值一样
my.secret=${random.value}
my.number=${random.int}
my.bignumber=${random.long}
my.uuid=${random.uuid}
my.number.less.than.ten=${random.int(10)}
my.number.in.range=${random.int[1024,65536]}


### command line 配置
- SpringApplication.setAddCommandLineProperties(false) ： 禁用 command line 配置
- java -jar D:/name.jar --server.port = 9090

### spring 默认加载 application.properties/.yml 配置文件的路径
- 当前路径：file:./
- 当前路径下 /config文件：file:./config/
- classpath 路径：classpath:/
- classpath 路径下的 /config文件：classpath:/config/

### spring.config.name spring.config.location
- 用来指定系统配置文件，用来替代默认的 application.properties/.yml
- spring.config.additional-location ：首先在此路径下查找
- 以上指定名字就是替换默认的application.properties，而指定路径是在该路径下查找application.properties
- 如果：spring.config.additional-location=classpath:/custom-config/,file:./custom-config/
    - 则查找顺序是以下
    - file:./custom-config/
    - classpath:custom-config/
    - file:./config/
    - file:./
    - classpath:/config/
    - classpath:/
- 如果：spring.config.location=classpath:/custom-config/,file:./custom-config/
    - 则查找顺序是以下
    - file:./custom-config/
    - classpath:custom-config/


### 使用mvn install打jar包


### 初始化文件选择
- 当development配置被激活时，server.address=0.1
- 当production或eu-central激活时，server.address=120
- 否则，server.address=100
server:
	address: 192.168.1.100
---
spring:
	profiles: development
server:
	address: 127.0.0.1
---
spring:
	profiles: production & eu-central
server:
	address: 192.168.1.120
	
	
### @Value @PropertySource
- 使用@PropertySource指定配置文件
- 使用@Value指定属性
- 注意：如果application.properties（application.yml无效）中有对应的属性，那么会将@PropertySource指定的值覆盖
- @PropertySource不可以指定yml,YAML files cannot be loaded by using the @PropertySource annotation. So, in the case that you need to load values that way, you need to use a properties file.
- 上面不能指定yml，可能是不能使用 key:value这种形式，如果在yml中用properties的格式也许可以

### @Bean与@ConfigurationProperties 一起使用

### @ConfigurationProperties中的prefix属性，如果是prefix一个单词为驼峰形式，要用-分解
- spring.redis.poolConfig -> spring.redis.pool-config
- The prefix value for the annotation must be in kebab case
- 否则报一下错误
    - Configuration property name 'spring.redis.poolConfig' is not valid:
    - Invalid characters: 'C'
    - Bean: getRedisConfig
    - Reason: Canonical names should be kebab-case ('-' separated), lowercase alpha-numeric characters and must start with a letter
- We recommend that, when possible, properties are stored in lower-case kebab format, such as my.property-name=acme
    
### 单独用@ConfigurationProperties不指定@PropertySource，可以匹配.yml

### 使用@Validated 进行校验

### profiles
- 在@Component(及其子注解)和@Configuration情况下，可以使用@Profiles注解加载指定profiles的配置文件
- 上面的情况，只有active指定的profiles时，才会加载该配置类，否则不进行加载，容器中自然没这个bean
- 指定profiles时，默认的application.properties/.yml中的属性也被加载了

