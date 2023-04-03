package com.xmr;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by xmr on 2020/3/6.
 */
@Component
public class RedisLock {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     *  Redis加锁的操作
     *
     * @param key
     * @param acquireTime
     * @return
     */
    public Boolean tryLock(String key, long acquireTime, long expireTime, TimeUnit timeUnit, long timeOut) {
        while (System.currentTimeMillis() - acquireTime < timeOut*1000) {
            if (stringRedisTemplate.opsForValue().setIfAbsent(key, String.valueOf(acquireTime))) {
                stringRedisTemplate.expire(key, expireTime, timeUnit);
                return true;
            }
            String currentValue = stringRedisTemplate.opsForValue().get(key);
            if (StringUtils.isNotEmpty(currentValue) && Long.valueOf(currentValue) < System.currentTimeMillis()) {
//获取上一个锁的时间 如果高并发的情况可能会出现已经被修改的问题所以多一次判断保证线程的安全
                String oldValue = stringRedisTemplate.opsForValue().getAndSet(key, String.valueOf(acquireTime));
                if (StringUtils.isNotEmpty(oldValue) && oldValue.equals(currentValue)) {
                    stringRedisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
                    return true;
                }
            }
        }
        return false;
    }


    /**
     *      * Redis解锁的操作
     *      *
     *      * @param key
     *      * @param value
     *      
     */


    public void unlock(String key, String value) {
        String currentValue = stringRedisTemplate.opsForValue().get(key);
        try {
            if (StringUtils.isNotEmpty(currentValue) && currentValue.equals(value)) {
                stringRedisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
        }
    }

}
