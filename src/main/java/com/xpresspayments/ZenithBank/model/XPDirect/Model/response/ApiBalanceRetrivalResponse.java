package com.xpresspayments.ZenithBank.model.XPDirect.Model.response;

import com.xpresspayments.ZenithBank.model.response.BalanceEnquiryResponse;
import com.xpresspayments.ZenithBank.zenithbank.constant.GapsStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ApiBalanceRetrivalResponse {

    private boolean success;
    private String status;
    private String statusMessage;
    private String accountNumber;
    private String bankCode;
    private String accountName;
    private String availableBalance;
    private String currentBalance;
    private String createdAt;
    private String currency;

    public static ApiBalanceRetrivalResponse fromGetBalance(BalanceEnquiryResponse balanceEnquiryResponse){


       return  ApiBalanceRetrivalResponse
               .builder()
               .success(getSuccessValue(balanceEnquiryResponse.getResponseCode()))
               .accountNumber(balanceEnquiryResponse.getAccountNumber())
               .availableBalance(String.valueOf(balanceEnquiryResponse.getAvailableBalance()))
             //  .status(balanceEnquiryResponse.getResponseCode())
             //  .status((getSuccessValue(balanceEnquiryResponse.getResponseCode())) ? "SUCCESSFUL" : "FAILED")
               .status(GapsStatus.getStatusMessage(balanceEnquiryResponse.getResponseCode()))
               .statusMessage(balanceEnquiryResponse.getResponseMessage())
               .build();
    }


    private static boolean getSuccessValue(String responseCode) {
        if (responseCode.equals("00")) {
            return true;

        }
        return false;
    }

}
