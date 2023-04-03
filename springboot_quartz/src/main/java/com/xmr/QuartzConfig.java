package com.xmr;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xmr on 2020/3/19.
 */
@Configuration
public class QuartzConfig {

    // 使用jobDetail包装job
    @Bean
    public JobDetail simpleJobDetail() {
        return JobBuilder.newJob(DateTimeJob.class)
                .withIdentity("simpleJob")
                .usingJobData("msg", "Hello world")
                .storeDurably()
                .build();
    }

    /**
     * 把jobDetail注册到trigger上去
     * @return
     */
    @Bean
    public Trigger simpleJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(15).repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(simpleJobDetail())
                .withIdentity("simpleJobTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }

//    @Bean
    public JobDetail cronJobDetail(){
        return JobBuilder.newJob(DateTimeJob.class)
                //可以给该JobDetail起一个id
                .withIdentity("cronJob")
                //每个JobDetail内都有一个Map，包含了关联到这个Job的数据，在Job类中可以通过context获取
                //关联键值对
                .usingJobData("msg", "Hello Quartz")
                //即使没有Trigger关联时，也不需要删除该JobDetail
                .storeDurably()
                .build();
    }
//    @Bean
    public Trigger printTimeJobTrigger() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/1 * * * * ?");
        return TriggerBuilder.newTrigger()
                //关联上述的JobDetail
                .forJob(cronJobDetail())
                //给Trigger起个名字
                .withIdentity("quartzTaskService")
                .withSchedule(cronScheduleBuilder)
                .build();
    }
}