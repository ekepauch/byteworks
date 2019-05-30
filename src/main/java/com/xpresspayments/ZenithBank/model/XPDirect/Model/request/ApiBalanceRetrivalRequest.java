package com.xpresspayments.ZenithBank.model.XPDirect.Model.request;

import com.xpresspayments.ZenithBank.model.request.BalanceEnquiryRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ApiBalanceRetrivalRequest {

    private String accountNumber;

    public BalanceEnquiryRequest toGetBalance(){
        BalanceEnquiryRequest balanceEnquiryRequest = new BalanceEnquiryRequest();
        balanceEnquiryRequest.setAccountNumber(accountNumber);
        return balanceEnquiryRequest;
    }
}
