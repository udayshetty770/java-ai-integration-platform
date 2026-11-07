package dev.udayshetty.aiplatform.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiChatService {

    private final ChatClient chatClient;

    public AiChatService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .defaultSystem("""
                        You are a helpful technical assistant.
                        Give accurate, practical answers.
                        If a question is ambiguous, state your assumptions.
                        Never claim to have executed code or accessed external systems.
                        """)
                .build();
    }

    public String ask(String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }
}