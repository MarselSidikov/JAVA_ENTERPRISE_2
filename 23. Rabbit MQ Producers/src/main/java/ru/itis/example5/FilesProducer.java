package ru.itis.example5;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class FilesProducer {

    private final static String PNG_ROUTING_KEY = "files.images.png";
    private final static String JPG_ROUTING_KEY = "files.images.jpg";
    private final static String PDF_ROUTING_KEY = "files.documents.pdf";
    private final static String HTML_ROUTING_KEY = "files.documents.html";

    private final static String FILES_EXCHANGE = "files_topic_exchange";
    private final static String EXCHANGE_TYPE = "topic";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(FILES_EXCHANGE, EXCHANGE_TYPE);

            Scanner scanner  = new Scanner(System.in);
            scanner.nextLine();

            File file = new File("files.txt");

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String currentFile;
            while ((currentFile = reader.readLine()) != null) {
                String currentRouting = currentFile.substring(currentFile.lastIndexOf("."));

                if (currentRouting.equals(".jpeg")) {
                    currentRouting = ".jpg";
                }

                switch (currentRouting) {
                    case ".jpg":
                        currentRouting = JPG_ROUTING_KEY;
                        break;
                    case ".png":
                        currentRouting = PNG_ROUTING_KEY;
                        break;
                    case ".html":
                        currentRouting = HTML_ROUTING_KEY;
                        break;
                    default:
                        currentRouting = PDF_ROUTING_KEY;
                        break;
                }

                channel.basicPublish(FILES_EXCHANGE, currentRouting, null, currentFile.getBytes());
            }
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
