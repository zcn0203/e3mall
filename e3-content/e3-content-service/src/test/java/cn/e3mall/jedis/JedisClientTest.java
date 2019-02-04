package cn.e3mall.jedis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.e3mall.common.jedis.JedisClient;
import cn.e3mall.common.jedis.JedisClientPool;

public class JedisClientTest {
	
	@Test
	public void testJedisClient() {
		//初始化spring容器
		ApplicationContext applicationContext = 
				new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
		//从容器中获得jedis对象
		JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
		jedisClient.set("mytest", "mytest haha");
		String string = jedisClient.get("mytest");
		System.out.println(string);
	}

}
