package ru.itis.example6;

import com.rabbitmq.client.*;

import com.rabbitmq.client.AMQP.BasicProperties;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.UUID;

public class Server {
    private final static String REQUEST_QUEUE_NAME = "calls";

    private Long downLoadFileAndGetSize(String fileUrl) {
        try {
            URL url = new URL(fileUrl);
            URLConnection urlConnection = url.openConnection();
            long size = urlConnection.getContentLength();
            ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
            File output = new File("downloaded/" + UUID.randomUUID().toString() + fileUrl.substring(fileUrl.lastIndexOf(".")));
            FileOutputStream fileOutputStream = new FileOutputStream(output);
            fileOutputStream.getChannel()
                    .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            return size;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Server() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(REQUEST_QUEUE_NAME, false, false, false, null);
            channel.queuePurge(REQUEST_QUEUE_NAME);
            channel.basicQos(1);

            Object monitor = new Object();

            DeliverCallback deliverCallback = (consumerTag, message) -> {
                BasicProperties replyProperties = new BasicProperties
                        .Builder()
                        .correlationId(message.getProperties().getCorrelationId())
                        .build();

                String fileUrl = new String(message.getBody());
                Long fileSize = downLoadFileAndGetSize(fileUrl);
                channel.basicPublish("", message.getProperties().getReplyTo(), replyProperties,
                        fileSize.toString().getBytes());
                channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                synchronized (monitor) {
                    monitor.notify();
                }
            };

            channel.basicConsume(REQUEST_QUEUE_NAME, false, deliverCallback, consumerTag -> {});

            while (true) {
                synchronized (monitor) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
