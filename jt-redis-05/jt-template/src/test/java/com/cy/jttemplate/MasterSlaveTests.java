package com.cy.jttemplate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * 主从机命令测试
 */
@SpringBootTest
public class MasterSlaveTests {

    @Autowired
    private RedisTemplate<?,?> redisTemplate;

    /**
     * 用于测试主机，写了读写功能
     * master:6379
     * 运行时要去application.yml里把port改为6379
     * 主机可以写可以读
     */
    @Test
    void testMasterReadWrite(){
        ValueOperations valueOperations=redisTemplate.opsForValue();
        valueOperations.set("city", "beijing");
        Object city =valueOperations.get("city");
        System.out.println(city);
    }

    /**
     * 用于测试从机，写了读的功能
     * slave:6380
     * 运行时要去application.yml里把port改为6380
     * slave:6381
     * 运行时要去application.yml里把port改为6381
     * 从机只能读，不能写
     */
    @Test
    void testMasterReadWrite2(){
        ValueOperations valueOperations=redisTemplate.opsForValue();
        //valueOperations.set("city", "shanghai");
        Object city =valueOperations.get("city");
        System.out.println(city);
    }


}
