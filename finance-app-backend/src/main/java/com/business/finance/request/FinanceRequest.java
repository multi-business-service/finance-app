package com.business.finance.request;

import com.business.finance.common.LendingDetail;
import lombok.Data;

import java.util.List;

@Data
public class FinanceRequest {
    private String customerId;
    private List<LendingDetail> lendingDetails;
}
