package com.xmr;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Created by xmr on 2020/3/5.
 */
@Component
public class ConsumerListener {

    @KafkaListener(topics = "testTopic" )
    public void onMessage(String message){
        //insertIntoDb(buffer);//这里为插入数据库代码
        System.out.println(message);
    }

}
