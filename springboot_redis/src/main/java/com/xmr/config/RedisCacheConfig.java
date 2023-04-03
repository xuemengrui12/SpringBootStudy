package com.xmr.config;

/**
 * @Author: Administrator
 * @Date: 2022/7/4
 * @LastEditTime: 2022/7/4 22:57
 * @LastEditors: Administrator
 * @Description:
 */

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.time.Duration;

@Configuration
@EnableCaching//启用缓存，这个注解很重要；
public class RedisCacheConfig extends CachingConfigurerSupport {
    /**
     * 缓存管理器.
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
        RedisConnectionFactory redisConnectionFactory = redisTemplate.getConnectionFactory();
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(1)); // 设置缓存有效期一小时
        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
                .cacheDefaults(redisCacheConfiguration).build();
    }

    /**
     * redis 模板操作类,类似于 jdbcTemplate 的一个类;
     * * 虽然 CacheManager 也能获取到 Cache 对象，但是操作起来没有那么灵活；
     * * 这里在扩展下：RedisTemplate 这个类不见得很好操作，我们可以在进行扩展一个我们
     * * 自己的缓存类，比如：RedisStorage 类;
     * *
     * * @param factory : 通过 Spring 进行注入，参数在 application.properties 进行配置；
     * * @return
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
        redisTemplate.setConnectionFactory(factory);
        //key 序列化方式;（不然会出现乱码;）,但是如果方法上有 Long 等非 String 类型的话，会报类型转换错误；
        //所以在没有自己定义 key 生成策略的时候，以下这个代码建议不要这么写，可以不配置或者自己实现 ObjectRedisSerializer
        //或者 JdkSerializationRedisSerializer 序列化方式;
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();//Long 类型不可以,会出现异常信息;
        redisTemplate.setKeySerializer(redisSerializer);
        redisTemplate.setHashKeySerializer(redisSerializer);
        return redisTemplate;
    }

    /**
     * 自定义 key.
     * 此方法将会根据类名+方法名+所有参数的值生成唯一的一个 key,即使@Cacheable中的value属性一样，key也会不一样。
     */
    @Override
    public KeyGenerator keyGenerator() {
        System.out.println("RedisCacheConfig.keyGenerator()");
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
// This will generate a unique key of the class name, the method name
//and all method parameters appended.
                StringBuilder sb = new StringBuilder();
                sb.append(o.getClass().getName());
                sb.append(method.getName());
                for (Object obj : objects) {
                    sb.append(obj.toString());
                }
                System.out.println("keyGenerator=" + sb.toString());
                return sb.toString();
            }
        };
    }

    /**
     * 在单独使用redisTemplate的时候 重新定义序列化方式
     *
     * @param lettuceConnectionFactory
     * @return
     */
//    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {

        RedisSerializer<String> stringSerializer = keySerializer();
        RedisSerializer<Object> jackson2JsonRedisSerializer = valueSerializer();

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(lettuceConnectionFactory);
        template.setEnableTransactionSupport(true);
        template.setKeySerializer(stringSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    private RedisSerializer<String> keySerializer() {
        return new StringRedisSerializer();
    }

    private RedisSerializer<Object> valueSerializer() {
        // 设置序列化
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        return jackson2JsonRedisSerializer;

        //或者使用GenericJackson2JsonRedisSerializer
        //return new GenericJackson2JsonRedisSerializer();
    }
}
