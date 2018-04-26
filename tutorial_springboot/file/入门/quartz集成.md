### quartz 集成
1. 引入依赖
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-quartz</artifactId>
</dependency>
```

#### 入门
1. 配置Job
```
public class CustomJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
        System.out.println("hello,job");
    }
}
```

2. 配置quartz触发器
```
@Configuration
public class CustomJobConfigurer {
    @Bean
    public JobDetail customJobDetail() {
        return JobBuilder.newJob(CustomJob.class).withIdentity("HelloJob")
                .storeDurably().build();
    }

    @Bean
    public Trigger customJobTrigger() {
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever();
        return TriggerBuilder.newTrigger().forJob(customJobDetail()).withIdentity("HelloTrigger").withSchedule(simpleScheduleBuilder).build();
    }
}
```
说明:  
JobBuilder         创建Job    
TriggerBuilder     创建触发器  
Scheduler          定时任务调度器,管理定时任务
问题:定时任务调度器谁管理?  
由于使用了Spring集成了Quartz,所以这里的Scheduler是由SchedulerFactoryBean进行管理.

#### 设置Job Data
1. 使用usingJobData(key,value)设置
```
@Bean
public JobDetail customJobDetail() {
    return JobBuilder.newJob(CustomJob.class).withIdentity("HelloJob")
            .usingJobData("name","Jimmy")
            .storeDurably().build();
}
```

2. 接收data
```
@Data
public class CustomJob extends QuartzJobBean {

    private String name;

    @Override
    protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
        System.out.println("hello,"+name);
    }
}
```

#### Job 注入Service
```
@Data
public class CustomJob extends QuartzJobBean {
    @Autowired
    private UserService userService;
} 
```

#### Job 持久化
1. 路径\org\quartz\impl\jdbcjobstore下的tables_mysql.sql执行
2. application.xml配置
```
spring.quartz.job-store-type=jdbc
spring.quartz.jdbc.initialize-schema=embedded
```
3. 运行,在表qrtz_job_details中有数据生成


#### 任务动态管理
1. 动态添加



注:多次添加在参数一样的情况下,后一次添加的无法正常加入且会抛出一个异常,但此异常不会影响到已经在运行的定时任务.

2. 动态删除

3. 动态修改

4. 动态关闭所有

5. 动态启动所有


