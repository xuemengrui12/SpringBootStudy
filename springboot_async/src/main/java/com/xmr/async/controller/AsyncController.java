package com.xmr.async.controller;

import com.xmr.async.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by xmr on 2019/8/19.
 */
@Slf4j
@RestController
public class AsyncController {
    @Autowired
    private AsyncService syncService;

    @GetMapping("/async")
    public String doAsync() throws InterruptedException {
        long start = System.currentTimeMillis();
        //调用同步方法
        syncService.syncEvent();
        long syncEndTime = System.currentTimeMillis();
        log.info("同步方法用时：{}", syncEndTime - start);
        //调用异步方法
        syncService.asyncEvent();//异步方法无返回值
        long endTime = System.currentTimeMillis();
        log.info("异步方法用时：{}", endTime - syncEndTime);
        return "async!!!";
    }

    @GetMapping("/async_return")
    public String doAsyncWithReturn() throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        //调用同步方法
        syncService.syncEvent();
        long syncEndTime = System.currentTimeMillis();
        log.info("同步方法用时：{}", syncEndTime - start);
        //调用异步方法
        // 异步方法有返回值
        Future<String> doFutrue = syncService.asyncEventWithReturn();
        while (true) {
            //判断异步任务是否完成
            if (doFutrue.isDone()) {
                log.info("异步回调结果：" + doFutrue.get());
                break;
            }
            Thread.sleep(100);
        }
        long endTime = System.currentTimeMillis();
        log.info("异步方法用时：{}", endTime - syncEndTime);
        return doFutrue.get();
    }
}
