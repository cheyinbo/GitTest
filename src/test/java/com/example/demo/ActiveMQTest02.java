package com.example.demo;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ActiveMQTest02 {

    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static final int SENDNUM = 10;


    public static void main(String[] args) throws JMSException {
        //实例化工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQTest02.USERNAME,ActiveMQTest02.PASSWORD,ActiveMQTest02.BROKEURL);
        //通过连接工厂来获取连接
        Connection connection = connectionFactory.createConnection();
        //启动连接
        connection.start();
        //创建session,第一个参数为是否开启事务
        Session session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
        //创建消息队列
        Destination destination = session.createQueue("CheQueue11");
        //创建消息生产者
        MessageProducer messageProducer = session.createProducer(destination);
        //发送消息
        TextMessage textMessage = session.createTextMessage("这是CheQueue发送的队列消息1111");
        messageProducer.send(textMessage);

        session.commit();
        connection.close();
    }

}
