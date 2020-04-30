package com.jt.config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

@Configuration // 标识此类为配置类
@PropertySource("classpath:/properties/redis.properties")
public class RedisConfig {
	/*
	 * 把类交给spring容器管理 1.xml配置文件中添加bean标签 2.配置类的形式
	 * 
	 * 配置： 将Jedis对象交给spring 容器管理
	 * 
	 * @Value("${redis.host}") String host;
	 * 
	 * @Value("${redis.port}") int port;
	 * 
	 * @Value("${redis.nodes}") String nodes;
	 * 
	 * @Bean public Jedis jedis() { return new Jedis(host,port); }
	 * 
	 * 
	 * //将ShardedJedis类交给spring容器管理
	 * 
	  @Bean 
	  public ShardedJedis shardedJedis() { 
	  	List<JedisShardInfo> shards=new
	 	ArrayList<JedisShardInfo>(); 
	 	String[] node=nodes.split("'"); 
	  	String host;
	  	String p; 
	  	for(String s:node) { 
		  String[] ss=s.split(":"); 
		  host=ss[0]; p=ss[1];
		  int port=Integer.parseInt(p);
		   shards.add(new JedisShardInfo(host,port)); 
		}
	  	ShardedJedis shardedJedis=new ShardedJedis(shards); 
	  	return shardedJedis; 
	  }
	 */
/*
	@Value("${redis.sentinel.masterName}")
	private String masterName;
	@Value("${redis.sentinels}")
	private String nodes;

	@Bean(name="jedsenpool")  //将池交给spring容器管理，避免多次创建池，
	public JedisSentinelPool jedisSentinelPool() {
		Set<String> sentinels = new HashSet<>();
		sentinels.add(nodes);
		JedisSentinelPool pool = new JedisSentinelPool(masterName, sentinels);
		return pool;
	}

	// 实现哨兵的配置
	@Bean
	@Scope("prototype") // 实现多例创建
	public Jedis jedis1(@Qualifier("jedsenpool")JedisSentinelPool pool) { 
		//(@Qualifier("jedsenpool")  :实现将jedisSentinelPool()方法返回的pool池作为本方法的参数
		
		Jedis jedis = pool.getResource();
		return jedis;
	}
*/
	
	@Value("${redis.nodes}")
	String node;
	//redis集群搭建
	@Bean
	public JedisCluster jedisCluster() {
		Set<HostAndPort> nodes=new HashSet<HostAndPort>();
		String[] nods = node.split(",");
		for(String n:nods) {
			String[] s = n.split(":");
			String host=s[0];
			int port=Integer.parseInt(s[1]);
			nodes.add(new HostAndPort(host, port));
		}
		JedisCluster jedisCluster=new JedisCluster(nodes);
		return jedisCluster;
	}
	
	
	
	
	
	
}
