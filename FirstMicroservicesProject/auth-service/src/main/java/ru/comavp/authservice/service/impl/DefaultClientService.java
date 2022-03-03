package ru.comavp.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import ru.comavp.authservice.dao.ClientEntity;
import ru.comavp.authservice.dao.ClientRepository;
import ru.comavp.authservice.exception.LoginException;
import ru.comavp.authservice.exception.RegistrationException;
import ru.comavp.authservice.service.ClientService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultClientService implements ClientService {

    private final ClientRepository userRepository;

    @Override
    public void register(String clientId, String clientSecret) {
        if (userRepository.findById(clientId).isPresent()) {
            throw new RegistrationException("Client with id: " + clientId + " already registered");
        }

        String hash = BCrypt.hashpw(clientSecret, BCrypt.gensalt());
        userRepository.save(new ClientEntity(clientId, hash));
    }

    @Override
    public void checkCredentials(String clientId, String clientSecret) {
        Optional<ClientEntity> optionalUserEntity = userRepository.findById(clientId);
        if (optionalUserEntity.isEmpty()) {
            throw new LoginException("Client with id: " + clientId + " not found");
        }

        ClientEntity clientEntity = optionalUserEntity.get();

        if (!BCrypt.checkpw(clientSecret, clientEntity.getHash())) {
            throw new LoginException("Secret is incorrect");
        }
    }
}
