package com.xmr.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by xmr on 2019/8/19.
 */
@Slf4j
@Service
public class AsyncService {
    @Async
//    @Async("asyncPoolTaskExecutor") //使用自定义线程池
    public void asyncEvent() throws InterruptedException {
        //休眠1s
        TimeUnit.SECONDS.sleep(1);
        log.info("异步方法内部线程名称：{}!", Thread.currentThread().getName());
    }
    @Async
    public Future<String> asyncEventWithReturn() throws InterruptedException {
        //休眠1s
        TimeUnit.SECONDS.sleep(1);
        log.info("异步方法内部线程名称：{}!", Thread.currentThread().getName());
        return new AsyncResult<>("异步方法有返回值");
    }

    public void syncEvent() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        log.info("同步方法内部线程名称：{}!", Thread.currentThread().getName());
    }
}
