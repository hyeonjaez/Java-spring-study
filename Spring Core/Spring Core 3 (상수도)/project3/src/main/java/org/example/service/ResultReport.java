package org.example.service;

import java.util.List;
import org.example.domain.WaterBill;

public interface ResultReport{
    void report(List<WaterBill> data);
    List<WaterBill> getResultOrderByTotalBillLimit5(List<WaterBill> data);
}
