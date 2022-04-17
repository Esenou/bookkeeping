package com.bookkeeping.kg.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
public class SalaryDto {
    private String productId;
    private String productName;
    private String productType;
    private String workerId;
    private String workerName;
    private Double product;
    private Double workerBrak;
    private Double productMadeCurrency;
    private Double brakMadeCurrency;
    private Double workerMadeCurrency;
    private Date createDate;
}
