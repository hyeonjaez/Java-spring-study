package org.example.service;

import org.example.annotation.TimeChecker;
import org.example.repository.PriceListRepository;
import org.springframework.stereotype.Service;

@Service
public class FileDataLoadService implements DataLoadService{
    PriceListRepository priceListRepository;

    public FileDataLoadService(PriceListRepository priceListRepository) {
        this.priceListRepository = priceListRepository;
    }

    @TimeChecker
    @Override
    public void load() {
        priceListRepository.dataFileLoad();
    }
}
