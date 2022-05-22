package com.bookkeeping.kg.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class SalaryDetailInfoDto extends SalaryInfoDto {
    private String productId;
    private String productName;
    private String productType;
    private Date createDate;
}
