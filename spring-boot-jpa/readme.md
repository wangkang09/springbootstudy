## jpa

### Spring Data JPA有什么
主要来看看Spring Data JPA提供的接口，也是Spring Data JPA的核心概念：
1：Repository：最顶层的接口，是一个空的接口，目的是为了统一所有Repository的类型，且能让组件扫描的时候自动识别。
2：CrudRepository ：是Repository的子接口，提供CRUD的功能
3：PagingAndSortingRepository：是CrudRepository的子接口，添加分页和排序的功能
4：JpaRepository：是PagingAndSortingRepository的子接口，增加了一些实用的功能，比如：批量操作等。
5：JpaSpecificationExecutor：用来做负责查询的接口
6：Specification：是Spring Data JPA提供的一个查询规范，要做复杂的查询，只需围绕这个规范来设置查询条件即可




- 如果实体的属性没有get/set，json里肯定不包含该属性的即使使用@JsonInclude也不行
- 建议实体的所有属性都要get/set，如果不想Json序列化，则可以使用@JsonIgnore指定


### 自动创建 table: hibernate_sequence问题
-  当使用@GeneratedValue生成自增主键时，可能默认生成方式是数据库里创建一个hibernate_sequence表来生成
- 如何避免：@GeneratedValue(strategy = GenerationType.IDENTITY) 用这个注解，表示自增主键交给数据库来，不需要额外生成表

### 问题
- 分页功能试了，但是没有数据，只有条数
- spring.datasource.url=jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8
- 上面配置解决时区问题