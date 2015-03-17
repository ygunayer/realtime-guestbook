package guestbook.controllers;


import java.util.Calendar;
import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GuestbookController {

    /**
     * A simple DTO class to encapsulate messages along with their timestamps.
     */
    public static class MessageDTO {

        public Date date;
        public String content;

        public MessageDTO(String message) {
            this.date = Calendar.getInstance().getTime();
            this.content = message;
        }
    }

    /**
     * Listens the /app/guestbook endpoint and when a message is received, encapsulates it in a MessageDTO instance and relays the resulting object to
     * the clients listening at the /topic/entries endpoint.
     * 
     * @param message the message
     * @return the encapsulated message
     */
    @MessageMapping("/guestbook")
    @SendTo("/topic/entries")
    public MessageDTO guestbook(String message) {
        System.out.println("Received message: " + message);
        return new MessageDTO(message);
    }
}
