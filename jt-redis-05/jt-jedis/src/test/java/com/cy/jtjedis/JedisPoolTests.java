package com.cy.jtjedis;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;

@SpringBootTest
public class JedisPoolTests {

    @Test
    void testJedisPool(){
        //1.创建池对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大连接数
        jedisPoolConfig.setMaxTotal(1000);
        //最大空闲时间
        jedisPoolConfig.setMaxIdle(1000);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"192.168.126.128",6379);
        //2.从池中获取连接
        Jedis jedis = jedisPool.getResource();
        //3.操作redis数据
        Set<String> keys = jedis.keys("*");
        //4.释放资源
        System.out.println(keys);
        jedis.close();
        //jedisPool.close();
    }

}
