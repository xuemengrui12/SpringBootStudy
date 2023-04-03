package com.xmr.rabbitmq.mq;

import com.rabbitmq.client.Channel;
import com.xmr.rabbitmq.common.Constant;
import com.xmr.rabbitmq.config.RabbitConfig;
import com.xmr.rabbitmq.pojo.MsgLog;
import com.xmr.rabbitmq.pojo.User;
import com.xmr.rabbitmq.service.IMsgLogService;
import com.xmr.rabbitmq.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * Created by xmr on 2020/2/1.
 */
@Slf4j
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
//    @RabbitListener(queues = RabbitConfig.ROUTING_QUEUE_1)
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


    @Autowired
    private IMsgLogService msgLogService;
    @Autowired
    private IUserService userService;

    @RabbitListener(queues = RabbitConfig.ROUTING_QUEUE_1)
    public void consume(Message message, Channel channel) throws IOException {
        User user = MessageHelper.msgToObj(message, User.class);
        log.info("收到消息: {}", user.toString());

        String correlationId = getCorrelationId(message);
        if (isConsumed(correlationId)) {// 消费幂等性, 防止消息被重复消费
            log.info("重复消费, correlationId: {}", correlationId);
            return ;
        }
        MessageProperties properties = message.getMessageProperties();
        long tag = properties.getDeliveryTag();

        /*
        * 我们在这里，真正的业务是mq消费端接收数据后存入数据库
        * */
        int count=userService.insert(user);
        if (count>0) {
            //如果数据操作成功，则更新数据日志记录表
            msgLogService.updateStatus(correlationId, Constant.MsgLogStatus.CONSUMED_SUCCESS);
            channel.basicAck(tag, false);// 消费确认
        } else {
            channel.basicNack(tag, false, true);
        }
    }

    /**
     * 获取CorrelationId
     *
     * @param message
     * @return
     */
    private String getCorrelationId(Message message) {
        String correlationId = null;

        MessageProperties properties = message.getMessageProperties();
        Map<String, Object> headers = properties.getHeaders();
        for (Map.Entry entry : headers.entrySet()) {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            if (key.equals("spring_returned_message_correlation")) {
                correlationId = value;
            }
        }

        return correlationId;
    }
    /**
     * 消息是否已被消费
     *
     * @param correlationId
     * @return
     */
    private boolean isConsumed(String correlationId) {
        MsgLog msgLog = msgLogService.selectByMsgId(correlationId);
        if (null == msgLog || msgLog.getStatus().equals(Constant.MsgLogStatus.CONSUMED_SUCCESS)) {
            return true;
        }
        return false;
    }
}
