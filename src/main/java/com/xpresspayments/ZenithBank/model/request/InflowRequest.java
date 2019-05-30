package com.xpresspayments.ZenithBank.model.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InflowRequest {


    private String merchantAccount;
    private String debitAccount;
    private String sourceBankCode;
    private String transactionAmount;
    private String paymentReference;
    private String transactionId;
    private String processorId;
    private String OTP;
    private String AuthType ;

}
