package com.cy.jttemplate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class JtTemplateApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;



    /**
     * 测试连接
     */
    @Test
    void testConnection(){
        String pong =stringRedisTemplate.getConnectionFactory().getConnection().ping();
        System.out.println(pong);
    }

    @Test
    void testStringTemplate() throws InterruptedException {
        ValueOperations<String, String> vo =stringRedisTemplate.opsForValue();

        vo.set("key1","100");
        vo.set("key2", "200");
        vo.increment("key2");
        vo.set("key3", "300",1, TimeUnit.SECONDS);

        String v1 = vo.get("key1");
        String v2 = vo.get("key2");

        System.out.println("v1="+v1+";v2="+v2);

        TimeUnit.SECONDS.sleep(2);

        String v3 =vo.get("key3");
        System.out.println("v3="+v3);

    }


    /**
     * 通过此对象操作redis中复杂数据类型的数据，例如hash结构
     */
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void testSetData(){
        SetOperations setOperations = redisTemplate.opsForSet();
        setOperations.add("setKey1", "A","B","C","C");
        Object members =setOperations.members("setKey1");
        System.out.println("setKeys"+members);
    }

    @Test
    void testListData(){
        //向list集合放数据
        ListOperations listOperations = redisTemplate.opsForList();
        listOperations.leftPush("lstKey1", "100"); //lpush
        listOperations.leftPushAll("lstKey1", "200","300");
        listOperations.leftPush("lstKey1", "100", "105");
        listOperations.rightPush("lstKey1", "700");
        Object value= listOperations.range("lstKey1", 0, -1);
        System.out.println(value);
        //从list集合取数据
        Object v1=listOperations.leftPop("lstKey1");//lpop
        System.out.println("left.pop.0="+v1);
        value= listOperations.range("lstKey1", 0, -1);
        System.out.println(value);
    }

    /**通过此方法操作redis中的hash数据*/
    @Test
    void testHashData(){
        HashOperations hashOperations = redisTemplate.opsForHash();//hash
        Map<String,String> blog=new HashMap<>();
        blog.put("id", "1");
        blog.put("title", "hello redis");
        hashOperations.putAll("blog", blog);
        hashOperations.put("blog", "content", "redis is very good");
        Object hv=hashOperations.get("blog","id");
        System.out.println(hv);
        Object entries=hashOperations.entries("blog");
        System.out.println("entries="+entries);
    }




}
