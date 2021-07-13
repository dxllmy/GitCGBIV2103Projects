package com.cy.jttemplate.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author tarena
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<Object,Object> redisTemplate(
            RedisConnectionFactory redisConnectionFactory){
        //1.构建RedisTemplate对象
        RedisTemplate<Object,Object> redisTemplate=new RedisTemplate<>();
        //2.设置连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //3.定义序列化方式
        Jackson2JsonRedisSerializer redisSerializer=
                new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL,//所有访问修饰符修饰的方法
                JsonAutoDetect.Visibility.ANY);//any表示任意级别访问修饰符修饰的属性
        redisSerializer.setObjectMapper(objectMapper);
        //4.设置RedisTemplate的序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(redisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
