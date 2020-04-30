package com.jt;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.config.RedisConfig;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestObjectToJson {
	private ObjectMapper mapper=new ObjectMapper();
	@Test
	public void toJSon() {
		
		
	}
	@Autowired
	RedisConfig redcon;
	@Test
	public void ss() {
		
		System.out.println(redcon);
	}
}
