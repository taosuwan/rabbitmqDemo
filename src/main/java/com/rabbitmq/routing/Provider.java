package com.rabbitmq.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.day1.utils.RabbitMQUtils;

import java.io.IOException;

public class Provider {

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();

        Channel channel = connection.createChannel();
        //参数1：交换机名称；  参数2：交换机类型， direct-路由模式中的直连模型
        channel.exchangeDeclare("logs_direct", "direct");
        //发送消息
        String routingKey = "error";
        channel.basicPublish("logs_direct", routingKey,null, ("这是direct：["+routingKey+"]发送的消息").getBytes());
        //关闭资源
        RabbitMQUtils.closeConnection(channel, connection);
    }
}
