
### 注意事项
- 自定义配置文件Url,最好是JdbcUrl，不然有可能对应不上
- driverClass = com.mysql.cj.jdbc.Driver




### AutoWired Qualifer Resource primary注解
- Autowired：使用@Autowired注释进行byType注入，如果需要byName（byName就是通过id去标识）注入，增加@Qualifier注释
- Autowired：当不能确定 Spring 容器中一定拥有某个类的 Bean 时，可以在需要自动注入该类 Bean 的地方可以使用 @Autowired(required = false)，很少这样
- Qualifer：和Autowired配合使用，使之通过byName查询
- @Resource：作用相当于@Autowired，只不过@Autowired按byType自动注入，而@Resource默认按 byName自动注入罢了
- @Resource(name="hikerDatasource")：如果没有指定name属性，并且按照默认的名称仍然找不到依赖对象时， @Resource注解会回退到按类型装配。但一旦指定了name属性，就只能按名称装配了
- primary：当有多个相同bean时，注解在其中一个bean上，表示优先注入这个bean，多个bean时不会报错



https://github.com/brettwooldridge/HikariCP
https://github.com/alibaba/druid/wiki/DruidDataSource%E9%85%8D%E7%BD%AE
https://github.com/alibaba/druid