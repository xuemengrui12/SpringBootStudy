package com.xmr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by xmr on 2020/2/23.
 */
@Configuration
@EnableRedisHttpSession
//        (maxInactiveIntervalInSeconds = 86400 * 30)
public class SessionConfig {
}