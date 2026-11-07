package dev.udayshetty.aiplatform.api;

import dev.udayshetty.aiplatform.service.AiChatService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/ai", produces = MediaType.APPLICATION_JSON_VALUE)
public class AiChatController {

    private final AiChatService aiChatService;

    public AiChatController(AiChatService aiChatService) {
        this.aiChatService = aiChatService;
    }

    @PostMapping("/chat")
    public ChatResponse chat(@Valid @RequestBody ChatRequest request) {
        return new ChatResponse(aiChatService.ask(request.message()), "configured-provider");
    }
}