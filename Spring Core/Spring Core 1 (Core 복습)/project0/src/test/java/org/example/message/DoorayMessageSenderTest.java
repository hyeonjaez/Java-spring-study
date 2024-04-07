package org.example.message;

import com.nhn.dooray.client.DoorayHookSender;
import org.example.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class DoorayMessageSenderTest {

    @Mock
    private DoorayHookSender doorayHookSender;

    private DoorayMessageSender doorayMessageSender;

    @BeforeEach
    public void makeMock() {
        MockitoAnnotations.initMocks(this);
        doorayMessageSender = new DoorayMessageSender(doorayHookSender);
    }

    @DisplayName("생성자 테스트 입니다")
    @Test
    void constructorTest() {
        Assertions.assertEquals(doorayMessageSender.getDoorayHookSender(), doorayHookSender);
    }

    @DisplayName("sendMessage() precondition 테스트 입니다")
    @Test
    void sendMessageErrorTest() {
        User user = null;
        Assertions.assertThrows(IllegalArgumentException.class, () -> doorayMessageSender.sendMessage(user, "test"));
    }

    @DisplayName("sendMessage() 테스트입니다")
    @Test
    void sendMessageTest() {
        User user = new User("정재현");
        String message = "test 입니다";
        boolean result = doorayMessageSender.sendMessage(user, message);

        Assertions.assertTrue(result);

    }

}