package com.bookkeeping.kg.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalaryInfoDto {
    private String workerId;
    private String workerName;
    private Double product;
    private Double workerBrak;
    private Double productMadeCurrency;
    private Double brakMadeCurrency;
    private Double workerMadeCurrency;
}
