package com.rabbitmq.day1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Provider {


    //生产消息
    @Test
    public void testProviderMessage() throws IOException, TimeoutException {
        //创建连接mq的连接工厂对象
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置连接rabbitmq主机
        connectionFactory.setHost("192.168.58.129");
        //设置端口号
        connectionFactory.setPort(5672);
        //设置连接哪个虚拟主机
        connectionFactory.setVirtualHost("/ems");
        //设置访问虚拟主机的用户名和密码
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");
        //获取连接对象
        Connection connection = connectionFactory.newConnection();
        //获取连接中通道
        Channel channel = connection.createChannel();
        //通道绑定对应消息队列
        //参数1：队列名称，如果队列不存在则自动创建
        //参数2：用来定义队列特性是否需要持久化， true-持久化， false-不持久化
        //参数3：exclusive 是否独占队列， true-独占队列， false-不独占
        //参数4：autoDelete：是否在消费完成后自动删除队列， true-自动删除， false-不自动删除
        //参数5：额外附加参数
        channel.queueDeclare("hello", false, false, false, null);
        //发布消息
        //参数1: 交换机名称； 参数2：队列名称；参数3：传递消息额外设置； 参数4：消息的具体内容
        channel.basicPublish("", "hello", null, "hello rabbitmq".getBytes());
        //关闭通道连接
        channel.close();
        //关闭服务连接
        connection.close();

    }

}
