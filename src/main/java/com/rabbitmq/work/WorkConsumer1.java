package com.rabbitmq.work;

import com.rabbitmq.client.*;
import com.rabbitmq.day1.utils.RabbitMQUtils;

import java.io.IOException;

public class WorkConsumer1 {

    public static void main(String[] args) throws IOException {
        //工厂连接
        Connection connection = RabbitMQUtils.getConnection();

        final Channel channel = connection.createChannel();

        //自动消息确认机制
        channel.basicQos(1);  //一次只接受一条未确认的消息（如果不加这个条件，默认为自动循环分发）

        channel.queueDeclare("work", true, false, false, null);

        //
        channel.basicConsume("work", false, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("工作队列消费=========="+new String(body));
                //参数1：确认执行的是队列中的哪一条消息
                //参数2：是否开启多个消息自动确认
                channel.basicAck(envelope.getDeliveryTag(), false);  //手动确认

            }
        });

    }
}
