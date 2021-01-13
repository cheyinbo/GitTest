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
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQTest02.USERNAME,ActiveMQTest02.PASSWORD,ActiveMQTest02.BROKEURL);
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("CheQueue");
        MessageProducer messageProducer = session.createProducer(destination);
        TextMessage textMessage = session.createTextMessage("这是CheQueue发送的队列消息");
        messageProducer.send(textMessage);
        connection.close();
    }

}
