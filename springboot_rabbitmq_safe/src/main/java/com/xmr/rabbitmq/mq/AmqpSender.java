package com.xmr.rabbitmq.mq;

import com.xmr.rabbitmq.config.RabbitConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xmr on 2020/2/1.
 */
@Component
public class AmqpSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 简单模式发送
     *
     * @param message
     */
    public void simpleSend(String message) {
        this.amqpTemplate.convertAndSend(RabbitConfig.SIMPLE_QUEUE, message);
    }


    /**
     * 发布/订阅模式发送
     *
     * @param message
     */
    public void psSend(String message) {
        this.amqpTemplate.convertAndSend(RabbitConfig.FANOUT_EXCHANGE, "", message);
    }


    /**
     * 路由模式发送
     *
     * @param message
     */
    public void routingSend(String routingKey, String message) {
        this.amqpTemplate.convertAndSend(RabbitConfig.DIRECT_EXCHANGE, routingKey, message);
    }


    /**
     * 主题模式发送
     *
     * @param routingKey
     * @param message
     */
    public void topicSend(String routingKey, String message) {
        this.amqpTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE, routingKey, message);
    }

}