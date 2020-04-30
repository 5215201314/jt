package com.jt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//exclude=DataSourceAutoConfiguration.class使得程序启动时将数据源排除在外
@SpringBootApplication(exclude=DataSourceAutoConfiguration.class)  
public class SpringBootRun {
	
	public static void main(String[] args) {
		
		SpringApplication.run(SpringBootRun.class,args);
	}
}
