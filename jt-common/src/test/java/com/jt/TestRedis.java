package com.jt;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestRedis {
	/*
	 * 1.spring 整合redis入门案例
	 */
	@Test
	public void testRedis() {
		String host="192.168.186.128";
		int port=6379;
		Jedis jedis=new Jedis(host, port);
		jedis.set("1903", "hello!月");
		String re = jedis.get("1903");
		System.out.println(re);
		System.out.println(jedis.strlen("1903"));
		jedis.append("1903", "value");
		System.out.println(jedis.get("1903"));
		System.out.println(jedis.exists("1903")+" "+jedis.exists("1902"));
		System.out.println(jedis.type("1903"));
		
		System.out.println(jedis.get("1903"));
		jedis.expire("1903", 20);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jedis.ttl("1903"));	
	}
	
	@Test
	public void testRedis2() {
		String host="192.168.194.128";
		int port=6379;
		Jedis jedis=new Jedis(host, port);

		jedis.setex("a",523,"18");
		jedis.incrBy("a", 10);
		System.out.println(jedis.get("a"));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jedis.ttl("a"));
	}
	
	@Test
	public void testRedis3() {
		String host="192.168.194.128";
		int port=6379;
		Jedis jedis=new Jedis(host, port);
		//jedis.set("yue", "8点xxx地点");
		//jedis.set("yue", "5点xxx地点");     //set方法会覆盖掉前面的值
		Long f1 = jedis.setnx("yue", "8点xxx地点");  //setnx方法如果之前键值yue已存在，则不会覆盖前面的值
		Long f2 = jedis.setnx("yue", "5点xxx地点");
		System.out.println(f1+"::::"+f2);
		
		System.out.println(jedis.get("yue"));
		System.out.println(jedis.keys("*"));
	}
	/*
	 * 死锁？？？？
	 *  1.setnx
	 *  set("yue", "今晚八点不夜之城见", "NX", "EX", 10) 以此方法在创建字符串时同时制定生存时间
	 *  			NX:setnx方式     EX:expire    10:生存时间为10秒
	 */
	@Test
	public void testRedis4() {
		String host="192.168.194.128";
		int port=6379;
		Jedis jedis=new Jedis(host, port);
		System.out.println(jedis.set("yue", "今晚八点不夜之城见", "NX", "EX", 10));
		// int a=1/0;
		jedis.del("yue");
		System.out.println(jedis.get("yue"));
	}
	
	@Test
	public void testRedishash1() {
		String host="192.168.186.128";
		int port=6379;
		Jedis jedis=new Jedis(host, port);
		
		jedis.hset("luo", "id", "52");
		jedis.hset("luo", "name", "yan");
		jedis.hset("luo", "age","18");
		System.out.println(jedis.hgetAll("luo"));
	}
	@Test
	public void testRedislist1() {
		String host="192.168.194.128";
		int port=6379;
		Jedis jedis=new Jedis(host, port);
		
		jedis.lpush("list", "1","2","3");
		System.out.println(jedis.rpop("list"));
	}
	
	@Test
	public void testTx() {
		Jedis jedis=new Jedis("192.168.194.128",6379);
		Transaction transaction = jedis.multi();  //开启事务
		try{
			transaction.set("yy", "pp");  //执行操作
			transaction.exec();   //提交事务
		}catch(Exception e) {
			e.printStackTrace();
			transaction.discard();  //出异常则回滚
		}
	}
	
	/*
	 * @Autowired private Jedis jedis;
	 * 
	 * @Test public void testRedisItemDesc() { String key="100"; String
	 * result=jedis.get(key); }
	 */
}
