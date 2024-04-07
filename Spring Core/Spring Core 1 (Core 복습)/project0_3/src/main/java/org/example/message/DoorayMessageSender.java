package org.example.message;

import com.nhn.dooray.client.DoorayHook;
import com.nhn.dooray.client.DoorayHookSender;
import java.util.Objects;
import org.example.annotation.TimeChecker;
import org.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DoorayMessageSender implements MessageSender {
    private final DoorayHookSender doorayHookSender;

    @Autowired
    public DoorayMessageSender(@Qualifier("doorayHookSender") DoorayHookSender doorayHookSender) {
        this.doorayHookSender = doorayHookSender;
    }

    @TimeChecker
    @Override
    public boolean sendMessage(User user, String message) {
        if (Objects.isNull(user)) {
            throw new IllegalArgumentException("input Null");
        }
        doorayHookSender.send(
                DoorayHook.builder()
                        .botName(user.getName())
                        .text(message)
                        .build());
        return true;
    }

    public DoorayHookSender getDoorayHookSender() {
        return doorayHookSender;
    }
}