package com.xpresspayments.ZenithBank.model.XPDirect.Model.request;


import com.xpresspayments.ZenithBank.model.request.TokenRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ApiTokenRequest {

    private String accountNumber;
    private String accountName;
    private String scheduleId;


    public TokenRequest toToken(){
        TokenRequest tokenRequest = new TokenRequest();
        tokenRequest.setAccountNumber(accountNumber);
        return tokenRequest;
    }

}
