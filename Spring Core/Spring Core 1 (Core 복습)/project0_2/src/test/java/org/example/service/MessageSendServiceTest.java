package org.example.service;

import org.example.message.MessageSender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class MessageSendServiceTest {

    @Mock
    MessageSender messageSender;

    @InjectMocks
    MessageSendService messageSendService;

    @BeforeEach
    public void makeMock() {
        MockitoAnnotations.initMocks(this);
    }

    @DisplayName("생성자 테스트 입니다")
    @Test
    void constructorTest() {
        Assertions.assertEquals(messageSendService.getMessageSender(), messageSender);
    }

}