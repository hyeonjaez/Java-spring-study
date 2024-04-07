package org.example.config;

import com.nhn.dooray.client.DoorayHookSender;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages = "org.example")
@EnableAspectJAutoProxy
public class JavaConfig {

    private static final String URL =
            "https://hook.dooray.com/services/3204376758577275363/3707922642738216364/WdJUIozGSgGdnxhQnS1lSA";

    @Bean
    public DoorayHookSender doorayHookSender(@Qualifier("template") RestTemplate template) {
        return new DoorayHookSender(template, URL);
    }

    @Bean
    public RestTemplate template() {
        return new RestTemplate();
    }


}
