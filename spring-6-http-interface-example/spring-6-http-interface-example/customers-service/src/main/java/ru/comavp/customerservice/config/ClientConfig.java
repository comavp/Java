package ru.comavp.customerservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import ru.comavp.customerservice.service.BookClient;

@Configuration
public class ClientConfig {

    @Bean
    BookClient bookClient() {
        WebClient client = WebClient.builder()
                .baseUrl("http://localhost:8080/api")
                .build();

        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();
        return factory.createClient(BookClient.class);
    }
}
