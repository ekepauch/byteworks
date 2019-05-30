package com.xpresspayments.ZenithBank.model.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransferRequest {


    private String debitAccount;
    private String sourceBankCode;
    private String creditAccount;
    private String destinationBankCode;
    private String transactionAmount;
    private String paymentReference;
    private String transactionId;
    private String processorId;
    private String ChannelId ;
    private String authType ;
    private String otp;
   // private String description;

}
