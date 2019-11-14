package ru.itis.websockets.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;

/**
 * 20.03.2018
 * MessageController
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Controller
public class MessageController {

    @Autowired
    SimpMessagingTemplate template;

    // если сообщение пришло на /app/hello
    @MessageMapping("/hello")
    public void getMessage(Message<?> message) {
        // печатаем сообщение
        System.out.println(message);
        // отправляем hello всем, кто подписан на /topic/chat
        template.convertAndSend("/topic/chat/", "hello!");
    }

    // если сообщение пришло на /app/bye
    @MessageMapping("/bye")
    // оно отправляется сразу всем, кто подписан на /topic/chat
    @SendTo("/topic/chat")
    public TextMessage byeMessage(Message<?> message) {
        return new TextMessage("Bye bye!");
    }
}
