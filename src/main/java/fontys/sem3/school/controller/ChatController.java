package fontys.sem3.school.controller;

import fontys.sem3.school.domain.NotificationMessage;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("notifications")
@CrossOrigin(origins = "http://localhost:5173")
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping
    public ResponseEntity<Void> sendNotificationToUsers(@RequestBody NotificationMessage message) {
        messagingTemplate.convertAndSend("/topic/publicmessages", message);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/private")
    public ResponseEntity<Void> sendPrivateNotificationToUser(@RequestBody NotificationMessage message) {
        messagingTemplate.convertAndSendToUser(message.getTo(), "/queue/inboxmessages", message);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
