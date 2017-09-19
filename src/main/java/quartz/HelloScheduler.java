package quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/*
* Created by  WangDi  on 2017/9/19 0019
*/
public class HelloScheduler {

    /*每两秒钟向控制台打印一次HelloWorld*/
    public static void main(String[] args) {

        //创建jobDetail并且绑定HelloJob.class
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).
                withIdentity("myJob","group1").
                usingJobData("message","JobDetail").
                usingJobData("floatValue",3.21F).
                build();

        //创建每两秒钟执行一次的trigger实例
        Trigger trigger = TriggerBuilder.newTrigger().
                withIdentity("myTrigger","group1").
                usingJobData("message","Trigger").
                usingJobData("booleanValue",true).
                startNow().withSchedule(
                    SimpleScheduleBuilder.simpleSchedule().
                    withIntervalInSeconds(2).repeatForever()
                ).build();

        //创建调度实例
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = null;

        try {
            scheduler = schedulerFactory.getScheduler();
            scheduler.start();
            scheduler.scheduleJob(jobDetail,trigger);

        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }
}
