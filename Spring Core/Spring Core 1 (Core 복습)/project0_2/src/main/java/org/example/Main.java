package org.example;

import org.example.domain.User;
import org.example.service.MessageSendService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.example");
        MessageSendService messageSendService = context.getBean("messageSendService", MessageSendService.class);
        User user = new User("정재현");
        messageSendService.doSendMessage(user, "안녕하세여");
    }
}