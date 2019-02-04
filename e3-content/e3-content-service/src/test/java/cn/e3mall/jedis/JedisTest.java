package cn.e3mall.jedis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {
	
	@Test
	public void testJedis(){
		//创建一个连接对象 参数 host port
		Jedis jedis = new Jedis("192.168.25.131", 6379);
		//直接使用jedis操作redis. 所有的jedis命令都对应一个方法
		jedis.set("key123", "my first jedis");
		String string = jedis.get("key123");
		System.out.println(string);
		//关闭连接
		jedis.close();
	}
	
	@Test
	public void testJedisPool(){
		//创建一个池对象  两个参数host port
		JedisPool jedisPool = new JedisPool("192.168.25.131", 6379);
		//从连接池中获取一个连接,就是一个jedis对象
		Jedis jedis = jedisPool.getResource();
		//使用jedis操作redis
		String string = jedis.get("key123");
		System.out.println(string);
		//关闭连接 , 每次使用完成后关闭连接, 连接池回收资源
		jedis.close();
		//关闭连接
		jedisPool.close();
	}
	
	@Test
	public void testJedisCluster(){
		//创建JedisCluster对象, 有一个参数 nodes, 是一个set类型
		//set中包含若干个HostAndPort
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		nodes.add(new HostAndPort("192.168.25.131", 7001));
		nodes.add(new HostAndPort("192.168.25.131", 7002));
		nodes.add(new HostAndPort("192.168.25.131", 7003));
		nodes.add(new HostAndPort("192.168.25.131", 7004));
		nodes.add(new HostAndPort("192.168.25.131", 7005));
		nodes.add(new HostAndPort("192.168.25.131", 7006));
		JedisCluster cluster = new JedisCluster(nodes);
		cluster.set("test1", "123");
		System.out.println(cluster.get("test1"));
		//关闭JedisCluster对象
		cluster.close();
	}

}
