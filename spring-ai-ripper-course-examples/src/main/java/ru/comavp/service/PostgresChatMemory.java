package ru.comavp.service;

import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.comavp.model.Chat;
import ru.comavp.model.ChatEntry;
import ru.comavp.repository.ChatRepository;

import java.util.Comparator;
import java.util.List;

@Component
public class PostgresChatMemory implements ChatMemory {

    @Autowired
    private ChatRepository chatMemoryRepository;

    @Override
    @Transactional
    public void add(String conversationId, List<Message> messages) {
        Chat chat = chatMemoryRepository.findById(Long.valueOf(conversationId)).orElseThrow();
        for (Message message : messages) {
            chat.addChatEntry(ChatEntry.toChatEntry(message));
        }
        chatMemoryRepository.save(chat);
    }

    @Override
    public List<Message> get(String conversationId, int maxMessages) {
        Chat chat = chatMemoryRepository.findById(Long.valueOf(conversationId)).orElseThrow();
        return chat.getHistory().stream()
                .sorted(Comparator.comparing(ChatEntry::getCreatedAt))
                .map(ChatEntry::toMessage)
                .limit(maxMessages)
                .toList();

    }

    @Override
    public void clear(String conversationId) {
        //not implemented
    }
}
