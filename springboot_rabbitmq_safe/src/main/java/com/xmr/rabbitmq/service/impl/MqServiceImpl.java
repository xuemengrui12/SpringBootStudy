package com.xmr.rabbitmq.service.impl;

import com.xmr.rabbitmq.common.ResponseCode;
import com.xmr.rabbitmq.common.ServerResponse;
import com.xmr.rabbitmq.config.RabbitConfig;
import com.xmr.rabbitmq.dao.MsgLogMapper;
import com.xmr.rabbitmq.mq.MessageHelper;
import com.xmr.rabbitmq.pojo.MsgLog;
import com.xmr.rabbitmq.pojo.User;
import com.xmr.rabbitmq.service.IMqService;
import com.xmr.rabbitmq.util.RandomUtil;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by xmr on 2020/3/4.
 */
@Service
public class MqServiceImpl implements IMqService {
    @Autowired
    private MsgLogMapper msgLogMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public ServerResponse send(User user) {
        String msgId = RandomUtil.UUID32();
        MsgLog msgLog = new MsgLog(msgId, user, RabbitConfig.DIRECT_EXCHANGE, RabbitConfig.ROUTING_KEY_NAME);
        msgLogMapper.insert(msgLog);// 消息入库

        CorrelationData correlationData = new CorrelationData(msgId);
        rabbitTemplate.convertAndSend(RabbitConfig.DIRECT_EXCHANGE, RabbitConfig.ROUTING_KEY_NAME, MessageHelper.objToMsg(user), correlationData);// 发送消息

        return ServerResponse.success(ResponseCode.MAIL_SEND_SUCCESS.getMsg());
    }
}
