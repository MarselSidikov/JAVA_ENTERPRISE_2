package ru.itis.example6;

import java.util.Scanner;

public class MainClient {
    public static void main(String[] args) {
        Client client = new Client();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String fileName = scanner.nextLine();
            System.out.println(client.downloadFileAndGetSize(fileName));
        }
    }
}
