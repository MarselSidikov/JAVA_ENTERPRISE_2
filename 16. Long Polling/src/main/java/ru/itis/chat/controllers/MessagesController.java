package ru.itis.chat.controllers;

import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.chat.dto.MessageDto;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MessagesController {
    private final Map<String, List<MessageDto>> messages = new HashMap<>();

    @PostMapping("/messages")
    public ResponseEntity<Object> receiveMessage(@RequestBody MessageDto message) {
        if (!messages.containsKey(message.getPageId())) {
            messages.put(message.getPageId(), new ArrayList<>());
        }
        for (List<MessageDto> pageMessages : messages.values()) {
            synchronized (pageMessages) {
                pageMessages.add(message);
                pageMessages.notifyAll();
            }
        }
        return ResponseEntity.ok().build();
    }

    @SneakyThrows
    @GetMapping("/messages")
    public ResponseEntity<List<MessageDto>> getMessagesForPage(@RequestParam("pageId") String pageId) {
       synchronized (messages.get(pageId)) {
           if (messages.get(pageId).isEmpty()) {
               messages.get(pageId).wait();
           }
           List<MessageDto> response = new ArrayList<>(messages.get(pageId));
           messages.get(pageId).clear();
           return ResponseEntity.ok(response);
       }
    }


}
