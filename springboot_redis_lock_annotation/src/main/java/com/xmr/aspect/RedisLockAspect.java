package com.xmr.aspect;

import com.xmr.CacheLockException;
import com.xmr.RedisLock;
import com.xmr.annotation.CacheLock;
import com.xmr.annotation.LockedComplexObject;
import com.xmr.annotation.LockedObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * Created by xmr on //.
 */
@Slf4j
@Aspect
@Component
public class RedisLockAspect {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisLock redisLock;

    @Autowired
    @Pointcut("@annotation(com.xmr.annotation.CacheLock)")
    public void cacheLock() {
    }

    @Around("cacheLock()")
    public Object interceptor(ProceedingJoinPoint joinPoint) throws CacheLockException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        CacheLock lock = method.getAnnotation(CacheLock.class);
        String lockPrefix = lock.lockedPrefix();
        if (StringUtils.isEmpty(lockPrefix)) {
            throw new RuntimeException("lock key can't be null...");
        }
        //获取上锁超时时间
        long timeOut = lock.timeOut();
        long expireTime = lock.expireTime();
        TimeUnit timeUnit = lock.timeUnit();
        String className = joinPoint.getTarget().getClass().getName();
        Object[] args = joinPoint.getArgs();
        String[] paramNames = signature.getParameterNames();
        //参数写入SpringEl域中
        EvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < args.length; i++) {
            context.setVariable(paramNames[i], args[i]);
        }
        //获得方法中参数的注解
        Annotation[][] annotations = method.getParameterAnnotations();

        //根据获取到的参数注解和参数列表获得加锁的参数
        Object lockedObject = getLockedObject(annotations, args);
        String objectValue = lockedObject.toString();
        String lockKey = lockPrefix + "_" + objectValue;
        long acquireTime = System.currentTimeMillis();
        try {
            final Boolean success = redisLock.tryLock(lockKey, acquireTime, expireTime, timeUnit, timeOut);
            if (success) {
                log.info("获取分布式锁成功,class={},method={},key={}", className, method, lockKey);
                //执行方法
                return  joinPoint.proceed();

            } else {
                //按理来说 我们应该抛出一个自定义的 CacheLockException 异常;
                throw new RuntimeException("请勿重复请求");
            }
        } catch (Throwable throwable) {
            throw new RuntimeException("系统异常");
        } finally {
            //如果演示的话需要注释该代码;实际应该放开
            redisLock.unlock(lockKey, String.valueOf(acquireTime));
            // lockRedisTemplate.delete(lockKey);
        }

    }

    /**
     *
     * @param annotations 参数注解，1维是参数，2维是注解
     * @param args
     * @return
     * @throws CacheLockException
     */
    private Object getLockedObject(Annotation[][] annotations, Object[] args) throws CacheLockException {
        if (null == args || args.length == 0) {
            throw new CacheLockException("方法参数为空，没有被锁定的对象");
        }
        if (null == annotations || annotations.length == 0) {
            throw new CacheLockException("没有被注解的参数");
        }
        int index = -1;//标记参数的位置指针
        //不支持多个参数加锁，只支持第一个注解为lockedObject或者lockedComplexObject的参数
        for (int i = 0; i < annotations.length; i++) {
            Object param = args[i];
            Annotation[] paramAnn = annotations[i];
            //参数为空，直接下一个参数
            if (param == null || paramAnn.length == 0) {
                continue;
            }
            for (Annotation annotation : paramAnn) {
                //这里判断当前注解是否为Test.class
                if (annotation.annotationType().equals(LockedObject.class)) {
                    //校验该参数，验证一次退出该注解
                    //TODO 校验参数
                    index = i;
                    break;
                }
                if (annotation.annotationType().equals(LockedObject.class)) {
                    //校验该参数，验证一次退出该注解
                    //TODO 校验参数
                    try {
                        return args[i].getClass().getField(((LockedComplexObject) annotation).name());
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }

                }
            }
            //找到第一个后直接break，不支持多参数加锁
            if (index != -1) {
                break;
            }
        }
        if (index == -1) {
            throw new CacheLockException("请指定被锁定参数");
        }
        return args[index];

    }
}