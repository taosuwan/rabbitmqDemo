package com.rabbitmq.routing;

import com.rabbitmq.client.*;
import com.rabbitmq.day1.utils.RabbitMQUtils;

import java.io.IOException;

public class Consumer2 {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机和交换类型
        channel.exchangeDeclare("logs_direct","direct");
        //创建一个临时队列
        String queue = channel.queueDeclare().getQueue();
        //基于routing key绑定队列和交换机
        channel.queueBind(queue, "logs_direct", "info");
        channel.queueBind(queue, "logs_direct", "error");
        channel.queueBind(queue, "logs_direct", "warning");
        //获取消费的消息
        channel.basicConsume(queue, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1："+new String(body));
            }
        });
    }
}
