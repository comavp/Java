package ru.comavp;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.comavp.advisors.expansion.ExpansionQueryAdvisor;
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

    @Autowired
    private ChatModel chatModel;

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder
                .defaultAdvisors(
                        getExpansionQueryAdvisor(0),
                        getHistoryAdvisor(10),
                        getLoggerAdvisor(20),
                        getRagAdvisor(30),
                        getLoggerAdvisor(40))
                .defaultOptions(OllamaOptions.builder()
                        .temperature(0.3)
                        .topP(0.7)
                        .topK(20)
                        .repeatPenalty(1.1)
                        .build())
                .build();
    }

    private Advisor getHistoryAdvisor(int order) {
        return MessageChatMemoryAdvisor.builder(getChatMemory()).order(order).build();
    }

    private Advisor getRagAdvisor(int order) {
        return QuestionAnswerAdvisor.builder(vectorStore)
                .order(order)
                .promptTemplate(new PromptTemplate(PROMPT_TEMPLATE))
                .searchRequest(SearchRequest.builder()
                        .topK(4)
                        .similarityThreshold(0.65)
                        .build())
                .build();
    }

    private Advisor getLoggerAdvisor(int order) {
        return SimpleLoggerAdvisor.builder().order(order).build();
    }

    private Advisor getExpansionQueryAdvisor(int order) {
        return ExpansionQueryAdvisor.builder(chatModel)
                .order(order)
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