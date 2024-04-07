package org.example.service;

import java.nio.file.Path;
import java.util.List;
import org.example.domain.WaterBill;

public interface DataParserService {
    List<WaterBill> parse(Path path);
}