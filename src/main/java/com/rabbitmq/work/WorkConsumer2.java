package com.rabbitmq.work;

import com.rabbitmq.client.*;
import com.rabbitmq.day1.utils.RabbitMQUtils;

import java.io.IOException;

public class WorkConsumer2 {

    public static void main(String[] args) throws IOException {
        //工厂连接
        Connection connection = RabbitMQUtils.getConnection();

        final Channel channel = connection.createChannel();
        channel.basicQos(1);

        channel.queueDeclare("work", true, false, false, null);

        channel.basicConsume("work", true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("工作队列消费=========="+new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });

    }
}
