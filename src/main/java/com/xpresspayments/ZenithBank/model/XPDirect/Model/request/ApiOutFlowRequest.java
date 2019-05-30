package com.xpresspayments.ZenithBank.model.XPDirect.Model.request;


import com.xpresspayments.ZenithBank.model.constant.TransferType;
import com.xpresspayments.ZenithBank.model.request.InflowRequest;
import com.xpresspayments.ZenithBank.model.request.OutFlowRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ApiOutFlowRequest {

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

    public OutFlowRequest toOutFlow(){
        OutFlowRequest outFlowRequest = new OutFlowRequest();
        outFlowRequest.setMerchantAccount(debitAccountNumber);
        outFlowRequest.setCreditAccount(creditAccountNumber);
        outFlowRequest.setPaymentReference(narration);
        outFlowRequest.setDestinationBankCode(bankCode);
        outFlowRequest.setTransactionAmount(amount);
        outFlowRequest.setTransactionId(scheduleId);
        return outFlowRequest;
    }

}
