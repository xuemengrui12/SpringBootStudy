package com.xmr;

import com.xmr.util.RedisUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

/**
 * Created by xmr on 2020/2/23.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRedis {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test() throws Exception {
        stringRedisTemplate.opsForValue().set("aaa", "111");
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void testObj() throws Exception {
        User user = new User("aa123456", 30);
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("user1", user);
        operations.set("user2", user, 1, TimeUnit.SECONDS);
        Thread.sleep(1000);
        //redisTemplate.delete("user2");
        boolean exists = redisTemplate.hasKey("user2");
        if (exists) {
            System.out.println("exists is true");
        } else {
            System.out.println("exists is false");
        }
        // Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
    }
    @Test
    public void testUtil(){
        redisUtil.set("str","ssss");
        System.out.println(redisUtil.get("str"));
    }
}
