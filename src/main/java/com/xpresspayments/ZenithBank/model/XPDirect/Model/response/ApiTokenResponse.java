package com.xpresspayments.ZenithBank.model.XPDirect.Model.response;


import com.xpresspayments.ZenithBank.model.response.TokenResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ApiTokenResponse {


    private String status;
    private String statusMessage;


    public static ApiTokenResponse fromToken(TokenResponse tokenResponse){

        return ApiTokenResponse
                .builder()
               // .status(tokenResponse.getResponseCode().equals("00") ? "SUCCESS":"FAILED")
                .status((getSuccessValue(tokenResponse.getResponseCode())) ? "SUCCESSFUL" : "FAILED")
                .statusMessage(tokenResponse.getResponseMessage())
                .build();
    }

    private static boolean getSuccessValue(String responseCode) {
        if (responseCode.equals("00")) {
            return true;

        }
        return false;
    }

}
