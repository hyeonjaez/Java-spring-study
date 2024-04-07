package org.example.service;

import java.util.List;
import org.example.annotation.TimeChecker;
import org.example.domain.WaterBill;
import org.example.repository.PriceListRepository;
import org.springframework.stereotype.Service;

@Service
public class NormalWaterUsageFeeService implements WaterUsageFeeService {
    PriceListRepository priceListRepository;

    public NormalWaterUsageFeeService(PriceListRepository priceListRepository) {
        this.priceListRepository = priceListRepository;
    }
    @TimeChecker
    @Override
    public List<WaterBill> calculateFare(long usage) {

        List<WaterBill> waterBillList = priceListRepository.getUsagePriceList(usage);
        waterBillList.stream()
                .forEach(waterBill -> waterBill.setBillTotal(waterBill.getUnitPrice() * usage));

        return waterBillList;
    }
}
