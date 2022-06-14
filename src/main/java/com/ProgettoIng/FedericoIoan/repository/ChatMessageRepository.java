package com.ProgettoIng.FedericoIoan.repository;

import com.ProgettoIng.FedericoIoan.model.ChatMessage;
import com.ProgettoIng.FedericoIoan.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findChatMessagesBySenderAndReceiver(User sender, User receiver);
    void deleteAllBySenderAndReceiver(User sender, User receiver);
}
