package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import org.example.config.AppConfig;
import org.example.domain.WaterBill;
import org.example.service.DataLoadService;
import org.example.service.ResultReport;
import org.example.service.WaterUsageFeeService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BootStrap {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        DataLoadService dataLoadService = ac.getBean("fileDataLoadService", DataLoadService.class);

        dataLoadService.load();

        WaterUsageFeeService waterUsageFeeService =
                ac.getBean("normalWaterUsageFeeService", WaterUsageFeeService.class);
        int usageWater;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("물 사용량을 입력해주세요");
            String water = br.readLine();
            usageWater = Integer.parseInt(water);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<WaterBill> waterBillList = waterUsageFeeService.calculateFare(usageWater);

        ResultReport resultReport = ac.getBean("normalResultReport", ResultReport.class);
        resultReport.report(waterBillList);


    }
}