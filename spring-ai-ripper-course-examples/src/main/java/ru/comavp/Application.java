package ru.comavp;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpClient;
import java.time.Duration;

@SpringBootApplication
public class Application {

//    @Bean
//    public HttpClient httpClient() {
//        return HttpClient.newBuilder()
//                .version(HttpClient.Version.HTTP_1_1)
//                .connectTimeout(Duration.ofSeconds(5))
//                .build();
//    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder
                .defaultOptions(ChatOptions.builder()
                        .model("qwen3-30b-a3b")
                        .build())
                .build();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        ChatClient chatClient = context.getBean(ChatClient.class);
        System.out.println("Waiting for LLM response ...");
        System.out.println(chatClient.prompt().user("Дай первую строку богемской рапсодии").call().content());
    }
}