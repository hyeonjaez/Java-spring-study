package org.example.functional;

import static org.example.Mathx.sum;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Experiments<T extends Number> implements Iterator<T> {
    private final Iterator<T> iterator;
    private final String experiment;
    private final String distributionName;
    private final List<T> dataList;

    public Experiments(Iterator<T> iterator, String experiment, String distributionName) {
        this.iterator = iterator;
        this.experiment = experiment;
        this.distributionName = distributionName;
        this.dataList = new ArrayList<>();
    }


    @Override
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Experiments No Such Element");
        }
        T data = iterator.next();
        this.dataList.add(data);
        return data;
    }

    public void report() {
        System.out.println(sum(dataList.iterator()) / this.dataList.size());
    }

}
