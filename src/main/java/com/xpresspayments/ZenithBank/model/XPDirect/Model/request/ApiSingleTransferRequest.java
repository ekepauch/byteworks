package com.xpresspayments.ZenithBank.model.XPDirect.Model.request;


import com.xpresspayments.ZenithBank.model.constant.TransferType;
import com.xpresspayments.ZenithBank.model.request.TransferRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ApiSingleTransferRequest {

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


    public TransferRequest toTransfer(){
        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setDebitAccount(debitAccountNumber);
        transferRequest.setSourceBankCode(bankCode);
        transferRequest.setCreditAccount(creditAccountNumber);
        transferRequest.setDestinationBankCode(bankCode);
        transferRequest.setTransactionAmount(amount);
        transferRequest.setPaymentReference(narration);
        transferRequest.setTransactionId(scheduleId);
        if(transferType.equals(TransferType.INFLOW)){
            transferRequest.setAuthType("S");
        }else if(transferType.equals(TransferType.OUTFLOW)){
            transferRequest.setAuthType("H");
        }
        transferRequest.setOtp(token);

        return transferRequest;
    }
}
