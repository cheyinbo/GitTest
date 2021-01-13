package com.example.demo.activeMQ;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Consumer {

    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,sss");

    /**
     * destination 目标地址即队列
     */
    @JmsListener(destination = "cybQueue")
    public void receiveMessage(String text){
        System.out.println("接收队列消息时间:"+df.format(new Date()) + ",接收到消息内容:" + text);
    }

}
