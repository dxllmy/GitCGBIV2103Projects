package com.cy.jttemplate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

@SpringBootTest
public class JedisTransactionTests {

    /**
     * Jedis测试 事务
     * 事务：要么都执行，要么都不执行
     */
    @Test
    void testTx(){
        Jedis jedis = new Jedis("192.168.126.128",6379);
        Transaction multi = jedis.multi();
        try{
            multi.set("w","100");
            multi.set("z","200");

            multi.exec();
        }catch (Exception e){
            e.printStackTrace();
            multi.discard();
        }finally {
            jedis.close();
        }
    }
}
