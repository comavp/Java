package ru.comavp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.comavp.dto.ClientDto;
import ru.comavp.dto.OrderDto;
import ru.comavp.entity.Client;
import ru.comavp.entity.Order;
import ru.comavp.repository.ClientRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
    
    private final ClientRepository clientRepository;
    
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    
    /**
     * Получить всех клиентов (с проблемой N+1)
     * Будет выполнен 1 запрос для загрузки клиентов + N запросов для загрузки заказов
     */
    @Transactional(readOnly = true)
    public List<ClientDto> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        //List<Client> clients = clientRepository.findAllWithOrders();
        //List<Client> clients = clientRepository.findAllUsingEntityGraph();
        return clients.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    /**
     * Получить всех клиентов (БЕЗ проблемы N+1)
     * Раскомментируйте этот метод и используйте вместо getAllClients для решения проблемы
     */
    // @Transactional(readOnly = true)
    // public List<ClientDto> getAllClientsOptimized() {
    //     List<Client> clients = clientRepository.findAllWithOrders();
    //     return clients.stream()
    //             .map(this::convertToDto)
    //             .collect(Collectors.toList());
    // }
    
    /**
     * Получить клиента по ID (с проблемой N+1)
     */
    @Transactional(readOnly = true)
    public ClientDto getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
        return convertToDto(client);
    }
    
    /**
     * Получить клиента по ID (БЕЗ проблемы N+1)
     * Раскомментируйте этот метод и используйте вместо getClientById для решения проблемы
     */
    // @Transactional(readOnly = true)
    // public ClientDto getClientByIdOptimized(Long id) {
    //     Client client = clientRepository.findByIdWithOrders(id)
    //             .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
    //     return convertToDto(client);
    // }
    
    // Маппинг Entity -> DTO
    private ClientDto convertToDto(Client client) {
        System.out.println("=== Маппинг Entity -> DTO ===");
        List<OrderDto> orderDtos = client.getOrders().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        
        return new ClientDto(
                client.getId(),
                client.getName(),
                client.getEmail(),
                orderDtos
        );
    }
    
    private OrderDto convertToDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getProductName(),
                order.getAmount(),
                order.getOrderDate()
        );
    }
}
