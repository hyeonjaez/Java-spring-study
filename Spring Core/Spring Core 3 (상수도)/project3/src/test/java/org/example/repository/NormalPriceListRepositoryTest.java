package org.example.repository;

import java.util.List;
import org.example.config.AppConfig;
import org.example.domain.WaterBill;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(AppConfig.class)
class NormalPriceListRepositoryTest {

    @Autowired
    NormalPriceListRepository normalPriceListRepository;

    @BeforeEach
    public void setUp() {
        normalPriceListRepository.dataFileLoad();
    }

    @Test
    public void dataFileLoadTest() {
        List<WaterBill> waterBillList = normalPriceListRepository.getWaterBillList();
        Assertions.assertNotNull(waterBillList);
        WaterBill firstWaterBill = waterBillList.get(0);
        Assertions.assertEquals(1, firstWaterBill.getNumber());
        Assertions.assertEquals("동두천시", firstWaterBill.getCity());
        Assertions.assertEquals("가정용", firstWaterBill.getSector());
        Assertions.assertEquals(1, firstWaterBill.getStep());
        Assertions.assertEquals(1, firstWaterBill.getSectionStart());
        Assertions.assertEquals(20, firstWaterBill.getSectionEnd());
        Assertions.assertEquals(690, firstWaterBill.getUnitPrice());
    }

    @Test
    public void getUsagePriceListTest(){
        List<WaterBill> waterBillList = normalPriceListRepository.getUsagePriceList(1000);
        Assertions.assertNotNull(waterBillList);
        WaterBill firstWaterBill = waterBillList.get(0);
        Assertions.assertEquals(3, firstWaterBill.getNumber());
        Assertions.assertEquals("동두천시", firstWaterBill.getCity());
        Assertions.assertEquals("가정용", firstWaterBill.getSector());
        Assertions.assertEquals(3, firstWaterBill.getStep());
        Assertions.assertEquals(31, firstWaterBill.getSectionStart());
        Assertions.assertEquals(999999, firstWaterBill.getSectionEnd());
        Assertions.assertEquals(1530, firstWaterBill.getUnitPrice());
    }
}