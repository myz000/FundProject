package com.demo.pagination;

import java.util.List;

public class Page<T extends PaginationObject> {
    private int total;
    private List<T> data;
    private int pageNum;
    private int pageSize;

    public Page(List<T> data, int pageNum, int pageSize) {
        this.data = data;
        this.total = (data == null || data.isEmpty()) ? 0 : data.get(0).getTotalCount();
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
