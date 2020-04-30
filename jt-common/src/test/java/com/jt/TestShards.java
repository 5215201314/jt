package com.jt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

public class TestShards {
	@Test
	public void testShards() {
		List<JedisShardInfo> shards=new ArrayList<JedisShardInfo>();
		String host="192.168.194.128";
		shards.add(new JedisShardInfo(host,6379));
		shards.add(new JedisShardInfo(host,6380));
		shards.add(new JedisShardInfo(host,6381));
		ShardedJedis jedis=new ShardedJedis(shards);
		jedis.set("1903", "分片测试");
		System.out.println(jedis.get("aa"));
		
	}
	/*
	 * 测试redis哨兵机制
	 * 链接哨兵时，链接的是哨兵的地址
	 */
	@Test
	public void testSentinel(){
		Set<String> sentinels=new HashSet<>();
		sentinels.add("192.168.194.128:26379");
		JedisSentinelPool pool=new JedisSentinelPool("mymaster",sentinels);
		Jedis jedis = pool.getResource();
		jedis.set("aaa","哨兵测试");
		System.out.println(jedis.get("aaa"));
	}
	
}
