package quartz;

import org.quartz.*;

/*
* Created by  WangDi  on 2017/9/19 0019
*/
public class HelloJob implements Job{

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

//        System.out.println("HelloWorld!");

        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
        System.out.println(jobKey.getName());

        JobDataMap dataMap1 = jobExecutionContext.getJobDetail().getJobDataMap();
        JobDataMap dataMap2 = jobExecutionContext.getTrigger().getJobDataMap();
        System.out.println(dataMap1.get("message"));
        System.out.println(dataMap2.get("message"));
    }
}
