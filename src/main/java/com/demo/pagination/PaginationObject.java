package com.demo.pagination;

public abstract class PaginationObject {
    private int rowNumber;
    private int totalCount;

    int getRowNumber() {
        return rowNumber;
    }

    void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    int getTotalCount() {
        return totalCount;
    }

    void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
