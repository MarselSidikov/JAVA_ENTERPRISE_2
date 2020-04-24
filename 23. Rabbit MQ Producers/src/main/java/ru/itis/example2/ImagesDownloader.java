package ru.itis.example2;

import com.rabbitmq.client.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class ImagesDownloader {

    private final static String IMAGES_QUEUE = "images_queue";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            // сколько неподтвержденных задач может брать на себя Consumer
            channel.basicQos(3);
            channel.basicConsume(IMAGES_QUEUE, false, (consumerTag, message) -> {
                // получаем URL-изображения
                String imageUrl = new String(message.getBody());
                System.out.println("Start  load image " + imageUrl);
                URL url = new URL(imageUrl);
                // получили название файла
                String fileName = url.getFile();
                try {
                    // скачиваем картинку
                    BufferedImage image = ImageIO.read(url);
                    // сохраняем в файл с расширением jpg
                    File output = new File("downloaded/" + UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf(".")));
                    ImageIO.write(image, "jpg", output);
                    System.out.println("Finish load image " + imageUrl);
                    // подтверждение того, что сообщение было обработано
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                } catch (IOException e) {
                    System.err.println("FAILED");
                    // reject - отмена выполнения сообщения, false - нельзя назначать другим
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                }

            }, consumerTag -> {});
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
