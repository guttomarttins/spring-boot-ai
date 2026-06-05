package br.com.dev.msiallm.controller;

import br.com.dev.msiallm.dto.ActorFilms;
import br.com.dev.msiallm.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/chat")
    public String chat(@RequestParam(value="message") String message){
        return chatService.chat(message);
    }

    @GetMapping("/chat-livre")
    public String chatLivre(@RequestParam(value="message") String message){
        return chatService.chatLivre(message);
    }

    @GetMapping
    List<ActorFilms> generation(){
        return chatService.filmes();
    }
}
