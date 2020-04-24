package ru.itis.example4;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ImagesProducer {
    // есть очередь для PNG
    private final static String PNG_QUEUE = "images_png_queue";
    // есть отдельная очередь для JPG
    private final static String JPG_QUEUE = "images_jpg_queue";

    // роутинг по png
    private final static String PNG_ROUTING_KEY = "png";
    // роутинг по jpg
    private final static String JPG_ROUTING_KEY = "jpg";
    // есть новый экчендж
    private final static String IMAGES_EXCHANGE = "images_direct_exchange";
    // новый
    private final static String EXCHANGE_TYPE = "direct";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(IMAGES_EXCHANGE, EXCHANGE_TYPE);
            channel.queueBind(PNG_QUEUE, IMAGES_EXCHANGE, PNG_ROUTING_KEY);
            channel.queueBind(JPG_QUEUE, IMAGES_EXCHANGE, JPG_ROUTING_KEY);

            File file = new File("images.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String currentFile;
            while ((currentFile = reader.readLine()) != null) {
                // в зависимости от типа файла отправляем его в конкретную очередь
                String currentRouting = currentFile.substring(currentFile.lastIndexOf(".") + 1);

                if (currentRouting.equals("jpeg")) {
                    currentRouting = "jpg";
                }

                channel.basicPublish(IMAGES_EXCHANGE, currentRouting, null, currentFile.getBytes());
            }
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
