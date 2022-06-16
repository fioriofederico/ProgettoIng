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
        try {
            User sender = userRepository.findById(senderId).orElseThrow(Exception::new);
            User receiver = userRepository.findById(receiverId).orElseThrow(Exception::new);

            return chatMessageRepository.findChatMessagesBySenderAndReceiver(sender, receiver);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ChatMessage findChatMessage(Long id) {
        try {
            return chatMessageRepository.findById(id).orElseThrow(Exception::new);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ChatMessage sendMessage(Long senderId, Long receiverId, ChatMessageDto message) {
        try {
            User sender = userRepository.findById(senderId).orElseThrow(Exception::new);
            User receiver = userRepository.findById(receiverId).orElseThrow(Exception::new);

            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setSender(sender);
            chatMessage.setReceiver(receiver);
            chatMessage.setMessage(message.getMessage());

            return chatMessageRepository.save(chatMessage);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteChat(Long senderId, Long receiverId) {
        try {
            User sender = userRepository.findById(senderId).orElseThrow(Exception::new);
            User receiver = userRepository.findById(receiverId).orElseThrow(Exception::new);

            chatMessageRepository.deleteAllBySenderAndReceiver(sender, receiver);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
