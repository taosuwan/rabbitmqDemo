package com.rabbitmq.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.day1.utils.RabbitMQUtils;

import java.io.IOException;

public class WorkProvider {

    public static void main(String[] args) throws IOException {
        //调用方法创建连接
        Connection connection = RabbitMQUtils.getConnection();

        Channel channel = connection.createChannel();
        //连接消息队列
        channel.queueDeclare("work", true, false, false, null);
        //消息发布
        for (int i = 0; i < 20; i++) {
            channel.basicPublish("", "work", null, (i+"work queue").getBytes());
        }
        //资源关闭
        RabbitMQUtils.closeConnection(channel, connection);
    }
}
