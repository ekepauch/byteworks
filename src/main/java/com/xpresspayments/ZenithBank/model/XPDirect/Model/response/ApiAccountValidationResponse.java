package com.xpresspayments.ZenithBank.model.XPDirect.Model.response;


import com.xpresspayments.ZenithBank.model.response.AccountValidationResponse;
import com.xpresspayments.ZenithBank.zenithbank.constant.GapsStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ApiAccountValidationResponse {

    private boolean success;
    private String bankCode;
    private String accountNumber;
    private String name;
    private String phone;
    private String address;
    private String status;
    private String statusMessage;


    public static ApiAccountValidationResponse fromGetValidation(AccountValidationResponse accountValidationResponse){

        return ApiAccountValidationResponse
                .builder()
                .success(getSuccessValue(accountValidationResponse.getResponseCode()))
                .accountNumber(accountValidationResponse.getAccountNumber())
                .name(accountValidationResponse.getAccountName())
                //  .status(balanceEnquiryResponse.getResponseCode())
                //  .status((getSuccessValue(balanceEnquiryResponse.getResponseCode())) ? "SUCCESSFUL" : "FAILED")
                .status(GapsStatus.getStatusMessage(accountValidationResponse.getResponseCode()))
                .statusMessage(accountValidationResponse.getResponseMessage())
                .build();
    }




    private static boolean getSuccessValue(String responseCode) {
        if (responseCode.equals("00")) {
            return true;

        }
        return false;
    }
}
