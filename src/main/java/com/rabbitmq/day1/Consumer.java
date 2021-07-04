package com.rabbitmq.day1;

import com.rabbitmq.client.*;
import com.rabbitmq.day1.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {


    public static void main(String[] args) throws IOException, TimeoutException {

       /* ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("106.15.182.29");
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");

        Connection connection = connectionFactory.newConnection();*/

        //使用连接工具类连接工厂
        Connection connection = RabbitMQUtils.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare("hello", false, false,false, null);

        //消费消息
        //参数1：所要消费的队列名称
        //参数2：开始消息的自动确认机制
        //参数3：消费时的回调接口
        channel.basicConsume("hello", true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者消费============"+new String(body));
            }
        });

//        channel.close();
//        connection.close();
    }
}
