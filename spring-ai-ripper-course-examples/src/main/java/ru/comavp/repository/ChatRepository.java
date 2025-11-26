package ru.comavp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.comavp.model.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
