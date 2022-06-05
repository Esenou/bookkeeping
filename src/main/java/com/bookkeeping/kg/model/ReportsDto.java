package com.bookkeeping.kg.model;

import java.util.List;

public class ReportsDto {
    List<ProductDto> productDtoList;
    List<WorkerDto> workerDtoList;

    public List<ProductDto> getProductDtoList() {
        return productDtoList;
    }

    public void setProductDtoList(List<ProductDto> productDtoList) {
        this.productDtoList = productDtoList;
    }

    public List<WorkerDto> getWorkerDtoList() {
        return workerDtoList;
    }

    public void setWorkerDtoList(List<WorkerDto> workerDtoList) {
        this.workerDtoList = workerDtoList;
    }
}
