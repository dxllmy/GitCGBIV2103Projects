package com.cy.jttemplate;

import com.cy.jttemplate.pojo.Blog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 07.12重写的，老师账号丢了，部分内容与09一样
 */
@SpringBootTest
public class RedisTemplateTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void testConnection(){
        String result=
                redisTemplate.getConnectionFactory().getConnection().ping();
        System.out.println(result);
    }
    //定义清除数据库数据的方法

    @Test
    void testFlushdb(){
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                //redisConnection.flushDb();
                redisConnection.flushAll();
                return "flush ok";
            }
        });
    }

    @Test
    void testStringOper01(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //RedisTemplate存储数据时会默认基于JDK的序列化机制,将数据序列化以后再进行存储
        valueOperations.set("address","beijing");
        //对数据进行反序列化实现
        Object value=valueOperations.get("address");
        System.out.println(value);
    }

    /**
     * 测试自己写一个pojo类，new里面的对象，那么这个pojo类必须要实现序列化接口，要么会报错
     */
    @Test
    void testStringOper02(){
        ValueOperations valueOperations = redisTemplate.opsForValue();

        //调用无参构造。这个pojo类必须要实现序列化接口，要么会报错
        //Blog blog=new Blog();
        //blog.setId(10);
        //blog.setTitle("study redis");

        //调用含参构造
        Blog blog=new Blog(10,"study redis");
        //序列化
        valueOperations.set("blog",blog);
        //反序列化
        LinkedHashMap map=(LinkedHashMap)valueOperations.get("blog");
        System.out.println("blog="+map);
    }

    //对hash类型数据进行测试
    @Test
    void testHashOper01(){
        //获取操作hash数据的对象
        HashOperations hashOperations = redisTemplate.opsForHash();
        //向redis存一个对象
        Map<String,String> blog=new HashMap<>();
        blog.put("id", "1");
        blog.put("title", "hello redis");
        hashOperations.putAll("blog", blog);
        hashOperations.put("blog","content","redis hash type");
        //从redis中获取存储的hash数据
        Object contentValue=hashOperations.get("blog","content");
        System.out.println(contentValue);
        //获取blog这个key对应的所有属性以及属性的值
        blog=hashOperations.entries("blog");
        System.out.println(blog);
    }

    @Test
    void testListOper(){
        ListOperations listOperations = redisTemplate.opsForList();
        //实现一个先进先出队列
        listOperations.leftPush("lst1","100");
        listOperations.leftPush("lst1","200");
        listOperations.leftPush("lst1","300");
        Object v1=listOperations.rightPop("lst1");
        Object v2=listOperations.rightPop("lst1");
        Object v3=listOperations.rightPop("lst1");
        System.out.println(v1+"/"+v2+"/"+v3);
        //从right位置开始放
        listOperations.rightPush("lst2","A");
        listOperations.rightPush("lst2","B");
        listOperations.rightPush("lst2","C");
        listOperations.rightPush("lst2","D","E");
        //获取lst2集合中指定位置的数据
        System.out.println(listOperations.range("lst2",0,-1));
    }

    @Test
    void testSetOper(){
        SetOperations setOperations = redisTemplate.opsForSet();
        setOperations.add("set01","A","B","C","A");
        Set set01 = setOperations.members("set01");
        System.out.println(set01);
    }
}
