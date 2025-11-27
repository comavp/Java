package ru.comavp.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import ru.comavp.model.LoadedDocument;
import ru.comavp.repository.DocumentRepository;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class DocumentLoaderService implements CommandLineRunner {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private ResourcePatternResolver resolver;

    @Autowired
    private VectorStore vectorStore;

    @SneakyThrows
    public void loadDocuments() {
        try {
            List<Resource> resources = Arrays.stream(resolver.getResources("classpath:/knowledgebase/**/*.txt")).toList();
            resources.stream()
                    .map(resource -> Pair.of(resource, calcContentHash(resource)))
                    .filter(pair -> !documentRepository.existsByFilenameAndContentHash(pair.getFirst().getFilename(), pair.getSecond()))
                    .forEach(pair -> {
                        Resource resource = pair.getFirst();
                        List<Document> documents = new TextReader(resource).get();
                        TokenTextSplitter textSplitter = TokenTextSplitter.builder().withChunkSize(500).build();
                        List<Document> chunks = textSplitter.apply(documents);
                        vectorStore.accept(chunks);

                        LoadedDocument loadedDocument = LoadedDocument.builder()
                                .documentType("txt")
                                .chunkCount(chunks.size())
                                .filename(resource.getFilename())
                                .contentHash(pair.getSecond())
                                .build();
                        documentRepository.save(loadedDocument);
                    });
        } catch (FileNotFoundException e) {
            log.warn("Файлы для загрузки файлов в RAG не найдены, шаг будет пропущен");
        }
    }

    @SneakyThrows
    private String calcContentHash(Resource resource) {
        return DigestUtils.md5DigestAsHex(resource.getInputStream());
    }

    @Override
    public void run(String... args) {
        loadDocuments();
    }
}
