package com.jt.conf;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jt.quartz.OrderQuartz;

@Configuration
public class OrderQuartzConfig {
	
	//定义任务详情
	@Bean
	public JobDetail orderjobDetail() {
		//指定job的名称和持久化保存任务
		return JobBuilder
				.newJob(OrderQuartz.class)//创建一个job任务 指定任务的处理类OrderQuartz
				.withIdentity("orderQuartz")//给任务起名为orderQuartz
				.storeDurably()//表示持久化
				.build();//表示建造者JobBuilder的运行，则建造了一个JobDetail返回
	}
	//定义触发器
	@Bean
	public Trigger orderTrigger() {
		/*SimpleScheduleBuilder builder = SimpleScheduleBuilder.simpleSchedule()
				.withIntervalInMinutes(1)	//定义时间周期
				.repeatForever();*/
		CronScheduleBuilder scheduleBuilder 
			= CronScheduleBuilder.cronSchedule("0 0/1 * * * ?");
		return TriggerBuilder
				.newTrigger()
				.forJob(orderjobDetail())//绑定任务
				.withIdentity("orderQuartz")//给触发器起名
				.withSchedule(scheduleBuilder).build();
	}
}
