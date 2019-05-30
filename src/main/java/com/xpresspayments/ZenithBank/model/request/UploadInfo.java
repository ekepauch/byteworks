package com.xpresspayments.ZenithBank.model.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UploadInfo {

    private String MerchantAccount;
    private String CreditAccount;
    private String DestinationBankCode;
    private BigDecimal transactionAmount;
    private String paymentReference;
    private String transactionId;
    private String processorId;

}
