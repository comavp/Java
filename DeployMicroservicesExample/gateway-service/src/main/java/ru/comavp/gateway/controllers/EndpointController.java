package ru.comavp.gateway.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.comavp.gateway.services.BackendAdapter;

@RestController
@RequiredArgsConstructor
public class EndpointController {

    private final BackendAdapter backendAdapter;

    @Value("${instance.id}")
    private int instanceId;

    @Value("${secret}")
    private String secret;

    @GetMapping("/")
    public String getRequestsCount() {
        return String.format("Number of requests %s (gateway %d, secret %s)", backendAdapter.getRequests(), instanceId, secret);
    }

}
