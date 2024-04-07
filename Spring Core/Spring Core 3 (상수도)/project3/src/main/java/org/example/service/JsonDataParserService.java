package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.example.annotation.TimeChecker;
import org.example.domain.WaterBill;
import org.springframework.stereotype.Service;

@Service
public class JsonDataParserService implements DataParserService {
    private ObjectMapper objectMapper;

    public JsonDataParserService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @TimeChecker
    @Override
    public List<WaterBill> parse(Path path) {
        try {
            byte[] jsonData = Files.readAllBytes(path);
            return objectMapper.readValue(jsonData, new TypeReference<List<WaterBill>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
