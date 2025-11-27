package ru.comavp.service;

import lombok.SneakyThrows;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import ru.comavp.model.Chat;
import ru.comavp.model.ChatEntry;
import ru.comavp.model.Role;
import ru.comavp.repository.ChatRepository;

import java.util.List;

import static ru.comavp.model.Role.ASSISTANT;
import static ru.comavp.model.Role.USER;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepo;

    @Autowired
    private ChatClient chatClient;

    @Autowired
    private ChatService myProxy;

    @Autowired
    private PostgresChatMemory postgresChatMemory;

    public List<Chat> getAllChats() {
        return chatRepo.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public Chat createNewChat(String title) {
        Chat chat = Chat.builder().title(title).build();
        chatRepo.save(chat);
        return chat;
    }

    public Chat getChat(Long chatId) {
        return chatRepo.findById(chatId).orElseThrow();
    }

    public void deleteChat(Long chatId) {
        chatRepo.deleteById(chatId);
    }

    public SseEmitter proceedInteractionWithStreaming(Long chatId, String userPrompt) {
        SseEmitter sseEmitter = new SseEmitter(0L);
        final StringBuilder answer = new StringBuilder();

        chatClient.prompt().user(userPrompt)
                .advisors(MessageChatMemoryAdvisor.builder(postgresChatMemory)
                        .conversationId(String.valueOf(chatId))
                        .build())
                .stream()
                .chatResponse()
                .subscribe((ChatResponse response) -> processToken(response, sseEmitter, answer),
                        sseEmitter::completeWithError);

        return sseEmitter;
    }

    @SneakyThrows
    private static void processToken(ChatResponse response, SseEmitter emitter, StringBuilder answer) {
        var token = response.getResult().getOutput();
        emitter.send(token);
        answer.append(token.getText());
    }
}
