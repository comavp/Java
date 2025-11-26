package ru.comavp.service;

import lombok.SneakyThrows;
import org.springframework.ai.chat.client.ChatClient;
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

    @Transactional
    public void addChatEntry(Long chatId, String prompt, Role role) {
        Chat chat = chatRepo.findById(chatId).orElseThrow();
        chat.addChatEntry(ChatEntry.builder().content(prompt).role(role).build());
    }

    @Transactional
    public void proceedInteraction(Long chatId, String prompt) {
        myProxy.addChatEntry(chatId, prompt, USER);
        String answer = chatClient.prompt().user(prompt).call().content();
        myProxy.addChatEntry(chatId, answer, ASSISTANT);
    }

    @SneakyThrows
    private static void processToken(ChatResponse response, SseEmitter emitter, StringBuilder answer) {
        var token = response.getResult().getOutput();
        emitter.send(token);
        answer.append(token.getText());
    }
}
