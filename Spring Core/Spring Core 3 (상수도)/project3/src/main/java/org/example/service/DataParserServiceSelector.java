package org.example.service;

import java.nio.file.Path;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class DataParserServiceSelector {
    private final DataParserService csvDataParserService;
    private final DataParserService jsonDataParserService;

    public DataParserServiceSelector(@Qualifier("csvDataParserService") DataParserService csvDataParserService,
                                     @Qualifier("jsonDataParserService") DataParserService jsonDataParserService) {
        this.csvDataParserService = csvDataParserService;
        this.jsonDataParserService = jsonDataParserService;
    }

    public DataParserService selectParser(Path path) {
        String fileName = path.getFileName().toString().toLowerCase();
        if (fileName.endsWith(".csv")) {
            return csvDataParserService;
        } else if (fileName.endsWith(".json")) {
            return jsonDataParserService;
        } else {
            throw new RuntimeException("잘못된 확장자 입니다");
        }
    }
}
