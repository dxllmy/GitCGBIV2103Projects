package comcy.jtcommonredis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;

/**
 * @author tarena
 */
@EnableCaching
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

    /**
     * 如下代码为扩展，加入我们需要自己定义CacheManager，序列化方式，key的有效市场，可以采用这样的
     * 下面这段代码是可以不写的。不写
     */
    @Bean
    public RedisCacheManager cacheManager(
            RedisConnectionFactory redisConnectionFactory) {
        //初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter =
                RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        //设置CacheManager的值序列化方式为json序列化
        RedisSerializer<Object> jsonSerializer =
                new GenericJackson2JsonRedisSerializer();
        RedisSerializationContext.SerializationPair<Object> pair =

                RedisSerializationContext.SerializationPair
                        .fromSerializer(jsonSerializer);
        RedisCacheConfiguration defaultCacheConfig =
                RedisCacheConfiguration.defaultCacheConfig()
                        .serializeValuesWith(pair);
        //设置默认超过时期是1天
        defaultCacheConfig.entryTtl(Duration.ofDays(1));
        //初始化RedisCacheManager
        return new RedisCacheManager(redisCacheWriter,
                defaultCacheConfig);
    }


}
