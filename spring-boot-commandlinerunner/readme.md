## 简单定时任务

- 定义一个类，实现 commmandlinerunner或ApplicationRunner，并注入到容器中，这样容器启动后，就会执行对应的run方法
- 通过在相关类上使用@Order注解，来指定多个run方法执行顺序
- 多个run方法时串行执行的，可以在其中调用异步来变成相对异步执行
- 必须将command类注入容器，才能开启任务