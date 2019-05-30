package com.xpresspayments.ZenithBank.model.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OutFlowRequest {

    private String merchantAccount;
    private String creditAccount;
    private String destinationBankCode;
    private String transactionAmount;
    private String paymentReference;
    private String transactionId;
    private String processorId;
}
