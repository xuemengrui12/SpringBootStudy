package com.xmr.rabbitmq.service;

import com.xmr.rabbitmq.common.ServerResponse;
import com.xmr.rabbitmq.pojo.User;

/**
 * Created by xmr on 2020/3/4.
 */
public interface IMqService {
    ServerResponse send(User user);
}
