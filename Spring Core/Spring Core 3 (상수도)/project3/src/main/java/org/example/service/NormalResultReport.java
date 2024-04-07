package org.example.service;

import java.util.List;
import java.util.stream.Collectors;
import org.example.annotation.TimeChecker;
import org.example.domain.WaterBill;
import org.springframework.stereotype.Service;

@Service
public class NormalResultReport implements ResultReport {
    @TimeChecker
    @Override
    public void report(List<WaterBill> data) {

        getResultOrderByTotalBillLimit5(data).stream().forEach(System.out::println);
    }

    @TimeChecker
    @Override
    public List<WaterBill> getResultOrderByTotalBillLimit5(List<WaterBill> data) {
        return data.stream()
                .sorted((o1, o2) -> Math.toIntExact(o1.getBillTotal() - o2.getBillTotal())).limit(5)
                .collect(Collectors.toList());

    }


}
