package org.example.repository;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import org.example.annotation.TimeChecker;
import org.example.domain.WaterBill;
import org.example.service.DataParserService;
import org.example.service.DataParserServiceSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class NormalPriceListRepository implements PriceListRepository {
    private DataParserServiceSelector dataParserServiceSelector;
    private Path path;
    private List<WaterBill> waterBillList;

    @Autowired
    public NormalPriceListRepository(DataParserServiceSelector dataParserServiceSelector,
                                     @Qualifier("filePath") Path path) {
        this.dataParserServiceSelector = dataParserServiceSelector;
        this.path = path;
    }

    @TimeChecker
    @Override
    public void dataFileLoad() {
        DataParserService dataParserService = dataParserServiceSelector.selectParser(this.path);
        waterBillList = dataParserService.parse(this.path);
    }

    @TimeChecker
    @Override
    public List<WaterBill> getUsagePriceList(long usageWater) {
        return waterBillList.stream()
                .filter(waterBill -> waterBill.getSectionStart() <= usageWater &&
                        waterBill.getSectionEnd() >= usageWater)
                .collect(Collectors.toList());
    }

    public List<WaterBill> getWaterBillList() {
        return waterBillList;
    }
}
