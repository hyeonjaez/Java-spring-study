package com.nhnacademy.shoppingmall.common.page;

import java.util.List;
import lombok.Getter;

@Getter
public class Page<T> {

    private final List<T> content;
    private final int totalCount;
    private final int pageSize;
    private int totalPage;

    public Page(List<T> content, int pageSize, int totalCount) {
        this.content = content;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        calculateTotalPage();
    }

    private void calculateTotalPage() {
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
    }


}