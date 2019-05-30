package com.xpresspayments.ZenithBank.model.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BatchRequest {

    private BigDecimal totalAmount;
    private String processorId;
    private String transactionCount;
    private String merchantAccount;
}
