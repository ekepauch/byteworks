package com.xpresspayments.ZenithBank.model.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OutFlowResponse {

    private String MerchantAccount;
    private String CreditAccount;
    private String DestinationBankCode;
    private String TransactionAmount;
    private String PaymentReference;
    private String TransactionId;
    private String ProcessorId;
    private String ResponseCode;
    private String ResponseMessage;



    public static OutFlowResponse fromOutFlowResponse(OutFlowResponse response) {

        OutFlowResponse outFlowResponse = new OutFlowResponse();
        outFlowResponse.setMerchantAccount(response.getMerchantAccount());
        outFlowResponse.setCreditAccount(response.getCreditAccount());
        outFlowResponse.setDestinationBankCode(response.getDestinationBankCode());
        outFlowResponse.setTransactionAmount(response.getTransactionAmount());
        outFlowResponse.setPaymentReference(response.getPaymentReference());
        outFlowResponse.setTransactionId(response.getTransactionId());
        outFlowResponse.setProcessorId(response.getProcessorId());
        outFlowResponse.setResponseCode(response.getResponseCode());
        outFlowResponse.setResponseMessage(response.getResponseMessage());
       return outFlowResponse;


    }
}
