package org.example.config;

import com.nhn.dooray.client.DoorayHookSender;
import org.example.aop.ElapsedTimeAspect;
import org.example.message.DoorayMessageSender;
import org.example.message.MessageSender;
import org.example.service.MessageSendService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAspectJAutoProxy
public class JavaConfig {

    private static final String URL =
            "https://hook.dooray.com/services/3204376758577275363/3707922642738216364/WdJUIozGSgGdnxhQnS1lSA";

    @Bean
    public DoorayMessageSender doorayMessageSender(@Qualifier("doorayHookSender") DoorayHookSender doorayHookSender) {
        return new DoorayMessageSender(doorayHookSender);
    }

    @Bean
    public MessageSendService messageSendService(@Qualifier("doorayMessageSender") MessageSender messageSender) {
        return new MessageSendService(messageSender);
    }

    @Bean
    public DoorayHookSender doorayHookSender(@Qualifier("template") RestTemplate restTemplate) {
        return new DoorayHookSender(restTemplate, URL);
    }

    @Bean
    public RestTemplate template() {
        return new RestTemplate();
    }

    @Bean
    public ElapsedTimeAspect elapsedTimeAspect() {
        return new ElapsedTimeAspect();
    }


}
