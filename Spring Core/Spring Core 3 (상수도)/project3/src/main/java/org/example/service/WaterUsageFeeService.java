package org.example.service;

import java.util.List;
import org.example.domain.WaterBill;

public interface WaterUsageFeeService {
    List<WaterBill> calculateFare(long usage);
}
