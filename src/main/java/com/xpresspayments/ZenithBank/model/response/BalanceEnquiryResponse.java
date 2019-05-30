package com.xpresspayments.ZenithBank.model.response;



import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BalanceEnquiryResponse {

    private String AccountNumber;
    private BigDecimal AvailableBalance;
    private String ResponseCode;
    private String ResponseMessage;
}
