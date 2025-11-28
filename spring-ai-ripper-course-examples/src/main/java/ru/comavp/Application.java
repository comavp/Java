package ru.comavp;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.comavp.repository.ChatRepository;
import ru.comavp.service.PostgresChatMemory;

@SpringBootApplication
public class Application {

    private static String PROMPT_TEMPLATE = """
    {query}
    
    Контекстная информация приведена ниже, окружена ---------------------
    ---------------------
    {question_answer_context}
    ---------------------
    
    Учитывая контекст и предоставленную историческую информацию, а не prior knowledge,
    ответьте на комментарий пользователя. Если ответа нет в контексте, сообщите
    пользователю, что вы не можете ответить на вопрос.""";

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private VectorStore vectorStore;

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder
                .defaultAdvisors(
                        getHistoryAdvisor(),
                        SimpleLoggerAdvisor.builder().build(),
                        getRagAdvisor())
                .build();
    }

    private Advisor getHistoryAdvisor() {
        return MessageChatMemoryAdvisor.builder(getChatMemory()).build();
    }

    private Advisor getRagAdvisor() {
        return QuestionAnswerAdvisor.builder(vectorStore)
                .promptTemplate(new PromptTemplate(PROMPT_TEMPLATE))
                .searchRequest(SearchRequest.builder()
                        .topK(4)
                        .build())
                .build();
    }

    private ChatMemory getChatMemory() {
        return PostgresChatMemory.builder()
                .maxMessages(8)
                .chatMemoryRepository(chatRepository)
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args).getBean(ChatClient.class);
    }
}