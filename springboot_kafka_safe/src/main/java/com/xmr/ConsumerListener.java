package com.xmr;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * Created by xmr on 2020/3/5.
 */
@Component
public class ConsumerListener {

    @KafkaListener(topics = "testTopic")
    public void onMessage(String message, Acknowledgment ack) {
        //insertIntoDb(buffer);//这里为插入数据库代码
        System.out.println(message);
        // TODO: 2020/3/5 需要通过方法实现消息的幂等性，然后再提交
        //手动提交offset
        ack.acknowledge();
    }

}
