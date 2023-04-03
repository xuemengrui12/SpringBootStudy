package com.xmr;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @Author: Administrator
 * @Date: 2022/7/3
 * @LastEditTime: 2022/7/3 21:55
 * @LastEditors: Administrator
 * @Description:
 */
@Component
public class MyHealth implements HealthIndicator {
    @Override
    public Health health() {
//        int errorCode = check(); // perform some specific health check
//        if (errorCode != 0) {
//            return Health.down().withDetail("Error Code", errorCode).build();
//        }
        return Health.up().build();
    }
}
