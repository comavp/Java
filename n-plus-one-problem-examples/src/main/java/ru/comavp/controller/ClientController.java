package ru.comavp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.comavp.dto.ClientDto;
import ru.comavp.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    
    private final ClientService clientService;
    
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    
    /**
     * GET /api/clients
     * Получить всех клиентов с их заказами
     * 
     * Демонстрирует проблему N+1:
     * - 1 запрос для получения всех клиентов
     * - N запросов для получения заказов каждого клиента
     */
    @GetMapping
    public ResponseEntity<List<ClientDto>> getAllClients() {
        System.out.println("=== Вызов getAllClients() ===");
        List<ClientDto> clients = clientService.getAllClients();
        System.out.println("=== Завершение getAllClients() ===");
        return ResponseEntity.ok(clients);
    }
    
    /**
     * GET /api/clients/{id}
     * Получить клиента по ID с его заказами
     * 
     * Демонстрирует проблему N+1:
     * - 1 запрос для получения клиента
     * - 1 запрос для получения заказов этого клиента
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClient(@PathVariable Long id) {
        System.out.println("=== Вызов getClient(" + id + ") ===");
        ClientDto client = clientService.getClientById(id);
        System.out.println("=== Завершение getClient(" + id + ") ===");
        return ResponseEntity.ok(client);
    }
}
