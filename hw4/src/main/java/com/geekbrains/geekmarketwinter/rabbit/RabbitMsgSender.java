package com.geekbrains.geekmarketwinter.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.lang.NonNull;

import java.nio.charset.StandardCharsets;

/**
 * Отправляет сообщение в RabbitMq.
 */
public class RabbitMsgSender {
    public static final RabbitMsgSender INSTANCE = new RabbitMsgSender();
    private final static String Exchange_NAME = "direct_exchange";
    private final ConnectionFactory factory = new ConnectionFactory();

    private RabbitMsgSender() {
        factory.setHost("localhost");
    }

    public void send(@NonNull String routingKey, @NonNull String msg) {
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(Exchange_NAME, "fanout");

            channel.basicPublish(Exchange_NAME, routingKey, null, msg.getBytes(StandardCharsets.UTF_8));
            System.out.println("sent " + routingKey + " " + msg);
        } catch (Exception e) {
            //something went wrong, do nothing about that
        }
    }
}
