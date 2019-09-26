package ru.itis.chat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;

@Controller
public class ChatController {
    @GetMapping("/chat")
    public String getChatPage(Model model) {
        model.addAttribute("pageId", UUID.randomUUID().toString());
        return "chat";
    }
}
