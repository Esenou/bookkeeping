package com.bookkeeping.kg.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SalaryDto {
    private List<SalaryInfoDto> salaryInfoDtoList;
    private List<SalaryDetailInfoDto> salaryDetailInfoDtoList;
}
