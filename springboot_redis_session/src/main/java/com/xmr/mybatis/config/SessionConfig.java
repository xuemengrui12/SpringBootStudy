package com.xmr.mybatis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by xmr on 2020/2/23.
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800)
public class SessionConfig {
    /**
     * maxInactiveIntervalInSeconds: 设置 Session 失效时间，使用 Redis Session 之后，原 Boot 的 server.session.timeout 属性不再生效。
     */
}