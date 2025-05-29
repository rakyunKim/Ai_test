package com.sight.sk_test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatController {
    private final OpenAIChatService openAIChatService;

    public ChatController(OpenAIChatService openAIChatService) {
        this.openAIChatService = openAIChatService;
    }

    @GetMapping("/chat")
    public String showChatPage() {
        return "chat";
    }

    @PostMapping("/chat")
    public String handleChat(@RequestParam("prompt") String prompt, Model model ) {
        String response = openAIChatService.ask(prompt);
        model.addAttribute("response", response);
        model.addAttribute("prompt", prompt);
        return "chat";
    }
}
