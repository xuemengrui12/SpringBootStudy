package com.xmr;

import com.xmr.rabbitmq.mq.AmqpSender;
import com.xmr.rabbitmq.Application;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
class ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private AmqpSender sender;


    @Test
    public void testSimpleSend() {
        for (int i = 1; i < 6; i++) {
            sender.simpleSend("test simpleSend " + i);
        }
    }


    @Test
    public void testPsSend() {
        for (int i = 1; i < 6; i++) {
            sender.psSend("test psSend " + i);
        }
    }


    @Test
    public void testRoutingSend() {
        for (int i = 1; i < 6; i++) {
            sender.routingSend("order", "test routingSend " + i);
        }
    }


    @Test
    public void testTopicSend() {
        for (int i = 1; i < 6; i++) {
            sender.topicSend("user.add", "test topicSend " + i);
        }
    }
}
