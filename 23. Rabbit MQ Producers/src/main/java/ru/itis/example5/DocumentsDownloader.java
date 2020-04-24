package ru.itis.example5;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class DocumentsDownloader {

    private final static String DOCUMENTS_ROUTING_KEY = "files.documents.*";
    private final static String FILES_EXCHANGE = "files_topic_exchange";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(3);

            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, FILES_EXCHANGE, DOCUMENTS_ROUTING_KEY);

            channel.basicConsume(queueName, false, (consumerTag, message) -> {
                String fileUrl = new String(message.getBody());
                System.out.println("Start  load file " + fileUrl);
                URL url = new URL(fileUrl);
                String fileName = url.getFile();
                try {
                    ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
                    File output = new File("downloaded/documents/" + UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf(".")));
                    FileOutputStream fileOutputStream = new FileOutputStream(output);
                    fileOutputStream.getChannel()
                            .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);

                    System.out.println("Finish load document " + fileUrl);
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
