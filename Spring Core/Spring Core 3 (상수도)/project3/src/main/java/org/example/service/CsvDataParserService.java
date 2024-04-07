package org.example.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import org.example.annotation.TimeChecker;
import org.example.domain.WaterBill;
import org.springframework.stereotype.Service;

@Service
public class CsvDataParserService implements DataParserService {

    @TimeChecker
    @Override
    public List<WaterBill> parse(Path path) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            return br.lines().skip(1)
                    .map(this::mapToWaterBill)
                    .collect(Collectors.toList());

        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }

    private WaterBill mapToWaterBill(String line) {
        String[] data = line.split(",");
        return new WaterBill(
                Integer.parseInt(data[0]),
                data[1],
                data[2],
                Integer.parseInt(data[3]),
                Long.parseLong(data[4]),
                Long.parseLong(data[5]),
                Long.parseLong(data[6]),
                null
        );
    }


}
