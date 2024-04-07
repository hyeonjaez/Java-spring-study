package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.example.domain.WaterBill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
@ComponentScan("org.example")
@PropertySource("application.properties")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {

    @Value("${fileName}")
    private String fileName;

    @Bean
    public List<WaterBill> waterBillList() {
        return new ArrayList<>();
    }

    @Bean("filePath")
    public Path path() {
        try {
            Resource resource = new ClassPathResource(fileName);
            return Paths.get(resource.getURI());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
