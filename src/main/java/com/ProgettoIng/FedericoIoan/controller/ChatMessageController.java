package com.ProgettoIng.FedericoIoan.controller;

import com.ProgettoIng.FedericoIoan.model.ChatMessage;
import com.ProgettoIng.FedericoIoan.model.User;
import com.ProgettoIng.FedericoIoan.model.dto.ChatMessageDto;
import com.ProgettoIng.FedericoIoan.service.ChatMessageServiceImpl;
import com.ProgettoIng.FedericoIoan.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/chat")
public class ChatMessageController {

    @Autowired
    private ChatMessageServiceImpl chatMessageService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/{receiverId}")
    public ResponseEntity<?> getChat(@PathVariable Long receiverId) {
        try {
            User sender = userService.getUserWithAuthorities().get();

            List<ChatMessage> messages = chatMessageService.findChat(sender.getId(), receiverId);

            return ResponseEntity.ok(messages);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

/*    @GetMapping("/{messageId}")
    public ResponseEntity<?> getChatMessage(@PathVariable Long messageId) {
        try {
            ChatMessage message = chatMessageService.findChatMessage(messageId);

            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }*/

    @PostMapping("/message/{receiverId}")
    public ResponseEntity<?> sendMessage(@PathVariable Long receiverId,
                                         @Valid @RequestBody ChatMessageDto chatMessageDto) {
        try {
            User sender = userService.getUserWithAuthorities().get();

            ChatMessage message = chatMessageService.sendMessage(sender.getId(), receiverId, chatMessageDto);

            return ResponseEntity.ok(message);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{receiverId}")
    public ResponseEntity<?> sendMessage(@PathVariable Long receiverId) {
        try {
            User sender = userService.getUserWithAuthorities().get();

            chatMessageService.deleteChat(sender.getId(), receiverId);

            return ResponseEntity.ok("All messages between " + sender.getId() + " and " + receiverId
                    + " are now deleted");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
