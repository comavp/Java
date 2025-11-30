package ru.comavp.advisors.rag;

import lombok.Builder;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.AdvisorChain;
import org.springframework.ai.chat.client.advisor.api.BaseAdvisor;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.comavp.advisors.expansion.ExpansionQueryAdvisor.ENRICHED_QUESTION;

@Builder
public class RagAdvisor implements BaseAdvisor {

    private static final PromptTemplate template = PromptTemplate.builder().template("""
                    Context: {context}
                    Question: {question}
                    """).build();

    private VectorStore vectorStore;
    @Builder.Default
    private SearchRequest searchRequest = SearchRequest.builder()
            .topK(4)
            .similarityThreshold(0.62)
            .build();

    private int order;

    public static RagAdvisorBuilder builder(VectorStore vectorStore) {
        return new RagAdvisorBuilder().vectorStore(vectorStore);
    }

    @Override
    public ChatClientRequest before(ChatClientRequest chatClientRequest, AdvisorChain advisorChain) {
        String originalQuestion = chatClientRequest.prompt().getUserMessage().getText();
        String queryToRag = chatClientRequest.context().getOrDefault(ENRICHED_QUESTION, originalQuestion).toString();

        List<Document> documents = vectorStore.similaritySearch(SearchRequest.from(searchRequest)
                .query(queryToRag)
                .topK(searchRequest.getTopK() * 2)
                .build());

        if (CollectionUtils.isEmpty(documents)) {
            return chatClientRequest.mutate().context("context", "Тут пусто - ни один документ не обнаружен.").build();
        }

        var rerankEngine = BM25RerankEngine.builder().build();

        documents = rerankEngine.rerank(documents, queryToRag, searchRequest.getTopK());

        String ragContext = documents.stream().map(Document::getText).collect(Collectors.joining(System.lineSeparator()));
        String finalUserPrompt = template.render(Map.of("context", ragContext, "question", originalQuestion));
        return chatClientRequest.mutate().prompt(chatClientRequest.prompt().augmentUserMessage(finalUserPrompt)).build();
    }

    @Override
    public ChatClientResponse after(ChatClientResponse chatClientResponse, AdvisorChain advisorChain) {
        return chatClientResponse;
    }

    @Override
    public int getOrder() {
        return order;
    }
}
