package com.xpresspayments.ZenithBank.model.XPDirect.Model.request;

import com.xpresspayments.ZenithBank.model.request.AccountValidationRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ApiAccountValidationRequest {

    private String accountNumber;
    private String bankCode;


    public AccountValidationRequest toGetAccount(){
        AccountValidationRequest accountValidationRequest = new AccountValidationRequest();
        accountValidationRequest.setAccountNumber(accountNumber);
        return accountValidationRequest;
    }
}
