package com.rabbitmq.day1.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQUtils {

    private static ConnectionFactory connectionFactory;

    static {
        //重量级资源，类加载时执行一次
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("106.15.182.29");
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");
    }

    //定义提供连接对象的方法
    public static Connection getConnection() {
        try {
            return connectionFactory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    //关闭通道和关闭连接的方法
    public static void closeConnection(Channel channel, Connection connection){
        try{
            if(channel != null) channel.close();
            if(connection != null) connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
