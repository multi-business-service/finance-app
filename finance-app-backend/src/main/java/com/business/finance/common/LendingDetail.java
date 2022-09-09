package com.business.finance.common;

import lombok.Data;

import java.util.Date;

@Data
public class LendingDetail {
    private String businessType;
    private Date lentDate;
    private Date  repayStartDate;
    private Boolean totalLending;
    private Integer totalTenure;
    private Boolean interestRate;
    private String repayMode;
    private Date repayCycleDate;
}
