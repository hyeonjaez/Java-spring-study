package org.example.service;

import org.example.domain.User;
import org.example.message.MessageSender;

public class MessageSendService {
    private final MessageSender messageSender;

    public MessageSendService(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void doSendMessage(User user, String message) {
        this.messageSender.sendMessage(user, message);
    }

    public MessageSender getMessageSender() {
        return messageSender;
    }
}
