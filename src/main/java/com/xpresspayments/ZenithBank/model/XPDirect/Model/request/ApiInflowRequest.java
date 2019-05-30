package com.xpresspayments.ZenithBank.model.XPDirect.Model.request;


import com.xpresspayments.ZenithBank.model.constant.TransferType;
import com.xpresspayments.ZenithBank.model.request.InflowRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ApiInflowRequest {


    private String amount;
    private String debitAccountNumber;
    private String creditAccountNumber;
    private TransferType transferType;
    private String narration;
    private String currency;
    private String token;
    private String bankCode;
    private String commission;
    private String scheduleId;
    private String beneficiaryName;
    private String referenceNumber;



    public InflowRequest toinFlow(){

        InflowRequest inflowRequest = new InflowRequest();
        inflowRequest.setTransactionId(scheduleId);
        inflowRequest.setTransactionAmount(amount);
        inflowRequest.setPaymentReference(narration);
        inflowRequest.setOTP(token);
        inflowRequest.setMerchantAccount(creditAccountNumber);
        inflowRequest.setDebitAccount(debitAccountNumber);
        inflowRequest.setSourceBankCode(bankCode);
        inflowRequest.setAuthType("hhhh");
        return inflowRequest;

    }
}
