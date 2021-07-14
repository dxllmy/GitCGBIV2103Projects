package com.cy.jttemplate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class RedisClusterTests {

    @Test
    void testJedisCluster()throws Exception{
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.126.129",8010));
        nodes.add(new HostAndPort("192.168.126.129",8011));
        nodes.add(new HostAndPort("192.168.126.129",8012));
        nodes.add(new HostAndPort("192.168.126.129",8013));
        nodes.add(new HostAndPort("192.168.126.129",8014));
        nodes.add(new HostAndPort("192.168.126.129",8015));
        JedisCluster jedisCluster = new JedisCluster(nodes);
        //使用jedisCluster操作redis
        jedisCluster.set("test", "cluster");
        String str = jedisCluster.get("test");
        System.out.println(str);
        //关闭连接池
        jedisCluster.close();
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void testMasterReadWrite(){
        //1.获取数据操作对象
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //2.读写数据
        valueOperations.set("city","beijing");
        Object city=valueOperations.get("city");
        System.out.println(city);
    }

}
