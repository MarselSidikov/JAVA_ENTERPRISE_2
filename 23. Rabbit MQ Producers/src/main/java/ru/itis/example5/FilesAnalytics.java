package ru.itis.example5;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeoutException;

public class FilesAnalytics {

    private final static String FILES_ROUTING_KEY = "files.#";
    private final static String FILES_EXCHANGE = "files_topic_exchange";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(3);

            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, FILES_EXCHANGE, FILES_ROUTING_KEY);
            channel.basicConsume(queueName, false, (consumerTag, message) -> {

                String fileUrl = new String(message.getBody());
                System.out.println("Start  load file " + fileUrl);
                URL url = new URL(fileUrl);
                try {
                    URLConnection urlConnection = url.openConnection();
                    long size = urlConnection.getContentLength();
                    System.out.println("File " + fileUrl + " size is " + size);
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                } catch (IOException e) {
                    System.err.println("FAILED");
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                }

            }, consumerTag -> {});
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
