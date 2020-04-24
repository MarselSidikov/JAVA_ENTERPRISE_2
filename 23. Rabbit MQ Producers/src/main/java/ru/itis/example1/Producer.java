package ru.itis.example1;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    // три очереди
    private final static String QUEUE_1 = "queue_1";
    private final static String QUEUE_2 = "queue_2";
    private final static String QUEUE_3 = "queue_3";


    public static void main(String[] args) {
        // подключаемся к RabbitMQ
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            // подключение
            Connection connection = connectionFactory.newConnection();
            // канал подключения
            Channel channel = connection.createChannel();
            channel.basicPublish("", QUEUE_1, null, "Hello".getBytes());
            channel.basicPublish("", QUEUE_2, null, "Hello".getBytes());
            channel.basicPublish("", QUEUE_3, null, "Hello".getBytes());
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
