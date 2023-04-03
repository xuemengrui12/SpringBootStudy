package com.xmr.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by xmr on 2020/2/1.
 */
@Component
public class AmqpReceiver {

    /**
     * 简单模式接收
     *
     * @param message
     */
    @RabbitListener(queues = RabbitConfig.SIMPLE_QUEUE)
    public void simpleReceive(String message) {
        System.out.println("接收消息:" + message);
    }

    /**
     * 路由模式接收
     *
     * @param message
     */
    @RabbitListener(queues = RabbitConfig.ROUTING_QUEUE_1)
    public void routingReceive1(String message) {
        System.out.println(RabbitConfig.ROUTING_QUEUE_1 + "接收消息:" + message);
    }


    @RabbitListener(queues = RabbitConfig.ROUTING_QUEUE_2)
    public void routingReceive2(String message) {
        System.out.println(RabbitConfig.ROUTING_QUEUE_2 + "接收消息:" + message);
    }

    /**
     * 主题模式接收
     *
     * @param message
     */
    @RabbitListener(queues = RabbitConfig.TOPIC_QUEUE_1)
    public void topicReceive1(String message) {
        System.out.println(RabbitConfig.TOPIC_QUEUE_1 + "接收消息:" + message);
    }

    @RabbitListener(queues = RabbitConfig.TOPIC_QUEUE_2)
    public void topicReceive2(String message) {
        System.out.println(RabbitConfig.TOPIC_QUEUE_2 + "接收消息:" + message);
    }

    /**
     * 发布/订阅模式接收
     *
     * @param message
     */
    @RabbitListener(queues = RabbitConfig.PS_QUEUE_1)
    public void psReceive1(String message) {
        System.out.println(RabbitConfig.PS_QUEUE_1 + "接收消息:" + message);
    }

    @RabbitListener(queues = RabbitConfig.PS_QUEUE_2)
    public void psReceive2(String message) {
        System.out.println(RabbitConfig.PS_QUEUE_2 + "接收消息:" + message);
    }

}
