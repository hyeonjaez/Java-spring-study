package org.example.message;

import org.example.domain.User;

public interface MessageSender {
    boolean sendMessage(User user, String message);
}
