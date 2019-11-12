## time Schedule

### timeTask
- 创建一个MyTimeTask类继承TimeTask类，重写run方法，run方法中就是定时任务方法
- new 一个 Timer类，在此类方法中，传入上面创建的MyTimeTask类和定时参数，指定定时类型
- TimeTask定时执行的是run方法，Task类只创建一次，同理springTask
- timer.schedule(new FirstTimerTask(),0,1000*a);这里声明的很明确了
- JobDetailImpl中带的是Job.class，所以每次都是重新创建的


### springTask
* 首先创建springTaskConfig配置类
* 在这类上使用@EnableScheduling开启定时任务
* 可以在配置文件中定义TaskScheduler的一些属性
* 也可以直接在main类上开启注解，完全使用默认配置
* 定义一个Task类，将此类注入容器
* 在此类中，定义Job，并用@Scheduled注解声明定时类型
* 运行项目即可执行定时任务
- @Scheduled所支持的参数：

    1. cron：cron表达式，指定任务在特定时间执行；
    2. fixedDelay：表示上一次任务执行完成后多久再次执行，参数类型为long，单位ms；
    3. fixedDelayString：与fixedDelay含义一样，只是参数类型变为String；
    4. fixedRate：表示按一定的频率执行任务，参数类型为long，单位ms；
    5. fixedRateString: 与fixedRate的含义一样，只是将参数类型变为String；
    6. initialDelay：表示延迟多久再第一次执行任务，参数类型为long，单位ms；
    7. initialDelayString：与initialDelay的含义一样，只是将参数类型变为String；
    8. zone：时区，默认为当前时区，一般没有用到。


### Quartz
1 Quartz 简介 
Quartz 是基于Java 实现的一个开源的调度框架, 是开源组织OpenSymphony 的产品。该项目于 2009 年被 Terracotta 收购，目前是 Terracotta 旗下的一个项目。官方网站：www.quartz-scheduler.org/ 。

2 Quartz功能及特点
- Quartz 能通过JVM独立运行
- Quartz 能在一个应用服务器里被实例化(或servlet容器), 并且可以作为XA事务的一部分来运行
- Quartz 能被集群化部署运行， 也可以单机方式运行

3 Quartz的核心
- Quartz的核心是Scheduler、Job、Trigger
- Job负责定义需要执行的任务
- Trigger负责调度策略
- Scheduler负责将二者组合

4 Quartz核心API
- schedule：与调度程序交互的主要API。可以通过StdSchedulerFactory.getDefaultScheduler();获得
- Job：实现了Job接口的类。
- JobDetail：用于定义作业的实例。JobDetail是接口，可以通过JobBuilder来构建JobDetailImpl实例，传入自定义的Job对象（不是new出来的实例）
- Trigger：触发器，执行JobDetail。JobDetail和Trigger是1->n的关系。Trigger是接口，可以通过TriggerBuilder构建实例
- JobBuilder：用于定义/构建JobDetailImpl实例
- TriggerBuilder：用于定义/构建触发器实例

5 Job与JobDetail    
- 一个Job可以对应多个JobDetail
- 每个JobDetail都有属于自己的属性集和JobDataMap
- 所有的JobDetail实例都在schedule中
    
6 Trigger
- 当一个trigger被触发时，与之关联的JobDetail实例会被加载
- 然后通过JobDetail中的JobDataMap对Job实例注入属性
    
7 Job类上的注解
- @DisallowConcurrentExecution：表示，在注解类中对应的所有JobDetail实例不并发执行
- @PersistJobDataAfterExecution：表示，在执行execute方法后，更新对应的JobDetail中的JobDataMap数据
- 建议两个注解一起使用，不然当同一个Job的不同实例并发执行时，JobDataMap可以数据不一致（不懂，JobDataMap不是和JobDetail一一对应的吗）

8 SimpleTrigger
- SimpleTrigger的属性包括：开始时间、结束时间、重复次数以及重复的间隔

9 Job Store
- JobStore负责跟踪您提供给调度程序的所有“工作数据”：jobs，triggers，日历等
- 你必须告诉Quartz（通过配置）使用哪个JobStore
- RAMJobStore
    1. 它将其所有数据保存在RAM中,速度快
    2. 缺点是当您的应用程序结束（或崩溃）时，所有调度信息都将丢失
- JDBC JobStore
    1. 它通过JDBC将其所有数据保存在数据库中
    2. 使用JDBCJobStore，必须首先创建一组数据库表以供Quartz使用。您可以在Quartz发行版的“docs / dbTables”目录中找到表创建SQL脚本
    3. 确定应用程序需要哪种类型的事务：如果您不需要将调度命令（例如添加和删除triggers）绑定到其他事务，那么可以通过使用JobStoreTX作为JobStore 来管理事务（这是最常见的选择）
    4. 如果需要Quartz与其他事务（即J2EE应用程序服务器）一起工作，那么您应该使用JobStoreCMT，在这种情况下，Quartz将让应用程序服务器容器管理事务 
    5. 设置一个DataSource，设置为org.quartz.impl.jdbcjobstore.JobStoreTX或org.quartz.impl.jdbcjobstore.JobStoreCMT
- TerracottaJobStore
    1. 通过服务管理Job数据，比JDBC快，比RAM慢，可以集群
    2. 需要添加一个额外的行配置来指定Terracotta服务器的位置
    
10 SchedulerFactory
- StdSchedulerFactory
    1. StdSchedulerFactory是org.quartz.SchedulerFactory接口的一个实现
    2. 它使用一组属性（java.util.Properties）来创建和初始化Quartz Scheduler。属性通常存储在文件中并从文件中加载，但也可以由程序创建并直接传递到工厂
    3. 简单地调用工厂中的getScheduler（）将生成调度程序，并初始化它。StdSchedulerFactory.getDefaultScheduler()
- DirectSchedulerFactory
    1. DirectSchedulerFactory是另一个SchedulerFactory实现
    2. 更加自由的用户定制
    3. 要求用户更好地了解他们正在做什么
    4. 会硬 - 编辑所有调度程序的设置
    
11 Clustering
- 功能包括负载平衡和 job故障转移
- Clustering中的每个节点必须具有唯一的instanceId，通过将“AUTO”作为此属性的值
- 每次触发只能有一个节点有效,根据负载均衡策略选择节点

12 misfire
- 如果scheduler关闭了，或者Quartz线程池中没有可用的线程来执行job
- 默认60s内重新执行，可以有别的策略

13 other
- 默认的JobFactory只是在jobs类上调用newInstance（）。您可能需要创建自己的JobFactory实现，以完成诸如让应用程序的IoC或DI容器生成/初始化jobs实例之类的操作
- Quartz还提供了许多实用jobs，您可以在应用程序中用于执行诸如发送电子邮件和调用EJB等工作。这些开箱即用的jobs可以在org.quartz.jobs 包中找到

14 JobDataMap
- 保存Job实例的状态信息
- 将Job添加到调度程序时，JobDataMap实例将存储一次。 在每次执行使用@PersistJobDataAfterExecution注释的作业后，它们也会被重新保留
- JobDataMap实例也可以与Trigger一起存储。 如果您有一个存储在调度程序中的作业以供多个触发器定期/重复使用，但是每次独立触发，您希望为作业提供不同的数据输入，这可能很有用
- key：在执行时传递给Job的JobExecutionContext还包含一个方便的JobDataMap，它是在Job的JobDataMap（如果有）上合并触发器JobDataMap（如果有）的内容的结果

15 schedule方法
- [方法说明](Schedule方法说明.md)

16 JobDetail-Trigger，1对多怎么设置
- scheduler.scheduleJob(job,set<trigger>,false);
- scheduler.scheduleJob(trigger);--这个trigger已经绑定某个JobDetail

17 ThreadPool
- ThreadName可以配置？
- PROP_SCHED_THREAD_NAME = "org.quartz.scheduler.threadName";

#### 注意事项
- 在调用其execute(…)方法之前会创建该类的一个新的实例。执行完毕就无用了，会被垃圾回收
- JobDetail是使用反射通过无参构造器创建的Job实例，所以Job必须有个无参构造器。且必须是public修饰的
- Job类中不应该有有状态属性，因为没意义
- Trigger的定时触发和前一次有没有执行完没关系
- 如果用JDBCStore做测试，要注意因为Trigger和Job已经持久化了，所以每次重新测试前要把数据库表清楚，防止尝试已经绑定的错误


            1 scheduler.scheduleJob(job1,trigger1);
            2 scheduler.scheduleJob(job0,trigger0);
            3 scheduler.scheduleJob(job1,trigger0);
            在第3步的时候，因为trigger0.getkey!=job1.getkey而报错
            
            1 scheduler.scheduleJob(job1,trigger1);
            2 scheduler.scheduleJob(job1,trigger0);
            在第2步的时候，因为判断job1已经存在，且默认不覆盖而报错
            
            1 scheduler.scheduleJob(job1,trigger1);
            2 scheduler.scheduleJob(job0,trigger1);
            在第2步的时候，因为Trigger在第一步已经set了job1的name和group了
            
            关键：说明，同一个scheduler中，一个jobDetail绑定一个trigger，只是一对一的关系。可以通过其他避免
            
            
            scheduler.scheduleJob(job1,trigger1);
            Set set = new HashSet();
            set.add(trigger0);
            scheduler.scheduleJob(job1,set,true);//这里将job1的jobkey复制给trigger0了。发生了insertTrigger和updateJobDetail

            set = new HashSet();
            set.add(trigger1);

            scheduler.scheduleJob(job0,set,true);//这一步将job0的key复制给了tirgger0。发生了insertJobDetail和updateTrigger（关键：updateTrigger将qz_triggers更新了！）后记：因为trigger1之前已经有绑定啦，肯定要覆盖呀！
            set.clear();
            set.add(trigger1);
            scheduler.scheduleJob(job0,set,true);//这一步将job0的key复制给了tirgger1。发生了updateJobDetail和updateTrigger（关键：updateTrigger将qz_triggers更新了！）




https://www.w3cschool.cn/quartz_doc/quartz_doc-onm72d85.html

https://blog.csdn.net/u012907049/article/details/73801122

https://juejin.im/post/5bff8de4f265da61223a0f5e