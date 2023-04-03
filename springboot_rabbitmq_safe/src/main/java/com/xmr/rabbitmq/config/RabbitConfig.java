package com.xmr.rabbitmq.config;

import com.xmr.rabbitmq.common.Constant;
import com.xmr.rabbitmq.service.IMsgLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xmr on 2020/2/1.
 */
@Slf4j
@Configuration
public class RabbitConfig {
    //=============简单、工作队列模式===============

    public static final String SIMPLE_QUEUE = "simple_queue";

    @Bean
    public Queue queue() {
        return new Queue(SIMPLE_QUEUE, true);
    }

    //===============路由模式============

    public static final String ROUTING_QUEUE_1 = "routing_queue_1";
    public static final String ROUTING_QUEUE_2 = "routing_queue_2";
    public static final String DIRECT_EXCHANGE = "direct_exchange";
    public static final String ROUTING_KEY_NAME = "routing.key.user";
    @Bean
    public Queue routingQueue1() {
        return new Queue(ROUTING_QUEUE_1, true);
    }

    @Bean
    public Queue routingQueue2() {
        return new Queue(ROUTING_QUEUE_2, true);
    }

    @Bean
    public DirectExchange directExchange() {
        //声明交换机的时候默认也是持久化的
        return new DirectExchange(DIRECT_EXCHANGE,true,false);
    }

    @Bean
    public Binding directBinding1() {
        return BindingBuilder.bind(routingQueue1()).to(directExchange()).with(ROUTING_KEY_NAME);
    }

    @Bean
    public Binding directBinding2() {
        return BindingBuilder.bind(routingQueue2()).to(directExchange()).with("order");
    }

    //===============主题模式============

    public static final String TOPIC_QUEUE_1 = "topic_queue_1";
    public static final String TOPIC_QUEUE_2 = "topic_queue_2";
    public static final String TOPIC_EXCHANGE = "topic_exchange";

    @Bean
    public Queue topicQueue1() {
        return new Queue(TOPIC_QUEUE_1, true);
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue(TOPIC_QUEUE_2, true);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with("user.add");
    }

    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with("user.#");
    }

    //===============发布/订阅模式============

    public static final String PS_QUEUE_1 = "ps_queue_1";
    public static final String PS_QUEUE_2 = "ps_queue_2";
    public static final String FANOUT_EXCHANGE = "fanout_exchange";

    @Bean
    public Queue psQueue1() {
        return new Queue(PS_QUEUE_1, true);
    }

    @Bean
    public Queue psQueue2() {
        return new Queue(PS_QUEUE_2, true);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Binding fanoutBinding1() {
        return BindingBuilder.bind(psQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutBinding2() {
        return BindingBuilder.bind(psQueue2()).to(fanoutExchange());
    }


    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Autowired
    private IMsgLogService msgLogService;

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());

        //关闭rabbitmq自动ack,手动提交ack
        // 消息是否成功发送到Exchange
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (ack) {
                    log.info("消息成功发送到Exchange");
                    String msgId = correlationData.getId();
                    msgLogService.updateStatus(msgId, Constant.MsgLogStatus.DELIVER_SUCCESS);
                } else {
                    log.info("消息发送到Exchange失败, {}, cause: {}", correlationData, cause);
                }
            }
        });

        // 触发setReturnCallback回调必须设置mandatory=true, 否则Exchange没有找到Queue就会丢弃掉消息, 而不会触发回调
        rabbitTemplate.setMandatory(true);
        // 消息是否从Exchange路由到Queue, 注意: 这是一个失败回调, 只有消息从Exchange路由到Queue失败才会回调这个方法
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                log.info("消息从Exchange路由到Queue失败: exchange: {}, route: {}, replyCode: {}, replyText: {}, message: {}",
                        exchange, routingKey, replyCode, replyText, message);
            }
        });

        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

}
