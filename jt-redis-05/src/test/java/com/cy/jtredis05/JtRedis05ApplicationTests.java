package com.cy.jtredis05;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

import java.util.UUID;

@SpringBootTest
class JtRedis05ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testRedisStringOper(){
        Jedis jedis=new Jedis("192.168.126.128", 6379);
        //jedis.auth("123456");假如你的redis设置了密码
        jedis.set("id", "101");
        jedis.set("name", "tony");
        System.out.println("set ok");
        String id=jedis.get("id");
        String name=jedis.get("name");
        System.out.println("id="+id+";name="+name);

        jedis.incr("id");
        jedis.incrBy("id", 2);
        System.out.println(jedis.strlen("name"));

        jedis.set("token", UUID.randomUUID().toString());
        jedis.expire("token", 60*30);
        String token =jedis.get("token");
        System.out.println("token="+token);

    }

}
