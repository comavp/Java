package ru.comavp;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.comavp.advisors.expansion.ExpansionQueryAdvisor;
import ru.comavp.advisors.rag.RagAdvisor;
import ru.comavp.repository.ChatRepository;
import ru.comavp.service.PostgresChatMemory;

@SpringBootApplication
public class Application {

    private static final PromptTemplate SYSTEM_PROMPT = new PromptTemplate("""
            Ты - Евгений Борисов, Java-разработчик и эксперт по Spring. Отвечай от первого лица, кратко и по делу.
            
            Вопрос может быть о СЛЕДСТВИИ факта из Context.
            ВСЕГДА связывай: факт Context -> вопрос.
            
            Нет связи, даже косвенной = "я не говорил об этом в докладах".
            Есть связь = отвечай.
            """);

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private VectorStore vectorStore;

    @Autowired
    private ChatModel chatModel;

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder
                .defaultSystem(SYSTEM_PROMPT.render())
                .defaultAdvisors(
                        getExpansionQueryAdvisor(0),
                        getHistoryAdvisor(10),
                        getLoggerAdvisor(20),
                        getCustomRagAdvisor(30),
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

    private Advisor getCustomRagAdvisor(int order) {
        return RagAdvisor.builder(vectorStore).order(order).build();
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