package com.xpresspayments.ZenithBank.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InflowResponse {


    private String MerchantAccount;
    private String DebitAccount;
    private String SourceBankCode;
    private String TransactionAmount;
    private String PaymentReference;
    private String TransactionId;
    private String ProcessorId;
    private String OTP;
    private String ResponseCode;
    private String ResponseMessage;




    public static InflowResponse frominFlowResponse(InflowResponse response) {

        InflowResponse inflowResponse = new InflowResponse();
        inflowResponse.setMerchantAccount(response.getMerchantAccount());
        inflowResponse.setDebitAccount(response.getDebitAccount());
        inflowResponse.setSourceBankCode(response.getSourceBankCode());
        inflowResponse.setTransactionAmount(response.getTransactionAmount());
        inflowResponse.setPaymentReference(response.getPaymentReference());
        inflowResponse.setTransactionId(response.getTransactionId());
        inflowResponse.setProcessorId(response.getProcessorId());
        inflowResponse.setResponseCode(response.getResponseCode());
        inflowResponse.setResponseMessage(response.getResponseMessage());
        return inflowResponse;


    }
}
