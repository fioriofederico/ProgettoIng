package com.ProgettoIng.FedericoIoan.service;

import com.ProgettoIng.FedericoIoan.model.ChatMessage;
import com.ProgettoIng.FedericoIoan.model.User;
import com.ProgettoIng.FedericoIoan.model.dto.ChatMessageDto;
import com.ProgettoIng.FedericoIoan.repository.ChatMessageRepository;
import com.ProgettoIng.FedericoIoan.repository.UserRepository;
import com.ProgettoIng.FedericoIoan.service.IService.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ChatMessage> findChat(Long senderId, Long receiverId) {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        return chatMessageRepository.findChatMessagesBySenderAndReceiver(sender, receiver);
    }

    public ChatMessage findChatMessage(Long id) {
        return chatMessageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chat message not found"));
    }

    public ChatMessage sendMessage(Long senderId, Long receiverId, ChatMessageDto message) {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSender(sender);
        chatMessage.setReceiver(receiver);
        chatMessage.setMessage(message.getMessage());

        return chatMessageRepository.save(chatMessage);
    }

    public void deleteChat(Long senderId, Long receiverId) {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        chatMessageRepository.deleteAllBySenderAndReceiver(sender, receiver);
    }
}