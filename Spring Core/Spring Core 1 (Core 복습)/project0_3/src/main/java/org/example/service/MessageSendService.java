package org.example.service;

import org.example.domain.User;
import org.example.message.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MessageSendService {

    private final MessageSender messageSender;

    @Autowired
    public MessageSendService(@Qualifier("doorayMessageSender") MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void doSendMessage(User user, String message) {
        this.messageSender.sendMessage(user, message);
    }

    public MessageSender getMessageSender() {
        return messageSender;
    }
}
