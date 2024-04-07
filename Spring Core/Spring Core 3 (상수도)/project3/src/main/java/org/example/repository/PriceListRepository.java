package org.example.repository;

import java.util.List;
import org.example.domain.WaterBill;

public interface PriceListRepository {
    void dataFileLoad();
    List<WaterBill> getUsagePriceList(long usageWater);




}
