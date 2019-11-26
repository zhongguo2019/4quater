package com.boot.configurations;
import static org.quartz.CalendarIntervalScheduleBuilder.calendarIntervalSchedule;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.quartz.DateBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TimeOfDay;
import org.quartz.Trigger;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.boot.util.qq.weixin.mp.aes.InforQuaterz;

@Configuration
public class QuatzConfig {
/*
    public void CalendarIntervalTriggerTest() throws SchedulerException{
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        JobDetail job = newJob(InforQuaterz.class).withIdentity("job", "group1").build();
        //每隔5个月，在指定日期和时间激活任务
        Trigger trigger = newTrigger().startAt(DateBuilder.dateOf(10, 10, 10, 13, 3, 2016)).withSchedule(calendarIntervalSchedule().withInterval(5, IntervalUnit.MONTH)).build();
        Date ft = sched.scheduleJob(job, trigger);
        sched.start();
    }
   
    
    
    public void DailyTimeIntervalTriggerTest() throws SchedulerException{
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        JobDetail job = newJob(InforQuaterz.class).withIdentity("job2", "group1").build();
        Set<Integer> daysOfWeek = new HashSet<Integer>();
        daysOfWeek.add(Calendar.SATURDAY);
        daysOfWeek.add(Calendar.SUNDAY);
        //每个周末，20点-20点30分，每隔1分钟激活1次
        Trigger trigger = newTrigger().startNow().withSchedule(dailyTimeIntervalSchedule().onDaysOfTheWeek(daysOfWeek).withInterval(1, IntervalUnit.MINUTE).withRepeatCount(5).startingDailyAt(new TimeOfDay(20,00)).endingDailyAt(new TimeOfDay(20,30)))
                .build();
        Date ft2 = sched.scheduleJob(job, trigger);
        sched.start();
    }
    
    public void SimpleTriggerTest() throws Exception {
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        JobDetail job = newJob(InforQuaterz.class).withIdentity("job1", "group1")
                .build();
        //在指定时间，按照指定周期和次数重复激活
        Trigger trigger = newTrigger()//TriggerBuilder.newTrigger()
                .startAt(DateBuilder.dateOf(10, 10, 10, 13, 3, 2016))
                .withSchedule(
                        simpleSchedule().repeatSecondlyForTotalCount(5, 2))//<SBT extends T> TriggerBuilder<SBT> withSchedule(ScheduleBuilder<SBT> schedBuilder)
                .build();
        Date ft = sched.scheduleJob(job, trigger);
        sched.start();
    }*/

	@Bean
    public void CronTriggerTest() throws SchedulerException{
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        JobDetail job = newJob(InforQuaterz.class).withIdentity("job2", "group1").build();
        //每个周二到周六，早上8点整点激活任务，从明天早上9点开始
        Trigger trigger = newTrigger()
        		.startAt(DateBuilder.tomorrowAt(6, 0, 0))
        		.withIdentity("trigger2", "group1")
        		.withSchedule(cronSchedule("0 0 8 ? * TUE-SAT"))
                .build();
        Date ft = sched.scheduleJob(job, trigger);
        sched.start();
    }
    
    public static void main(String[] args) throws Exception {
    	QuatzConfig example = new QuatzConfig();
        //example.SimpleTriggerTest();
        //example.DailyTimeIntervalTriggerTest();
        //example.CalendarIntervalTriggerTest();
        example.CronTriggerTest();
    }
    
    
}