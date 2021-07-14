package com.hdu.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author raptor
 * @description DeadLetterQueueConsumer
 * @date 2021/7/12 19:51
 */
@Component
public class DeadLetterQueueConsumer {

    @RabbitListener(queues = "QD")
    public void receive(Message message, Channel channel) {
        String msg = new String(message.getBody());
        System.out.println(new Date() + "收到死信队列的消息" + msg);
    }

}
