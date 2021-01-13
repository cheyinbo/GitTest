package com.example.demo;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;

import javax.jms.Queue;

@SpringBootTest
public class ActiveMQTest {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Test
    public void testQueueMsg(){
        //创建名称为cybQueue的队列
        Queue queue = new ActiveMQQueue("cybQueue");
        //向队友发送消息
        jmsMessagingTemplate.convertAndSend(queue,"这是一个队列消息");
    }

}
