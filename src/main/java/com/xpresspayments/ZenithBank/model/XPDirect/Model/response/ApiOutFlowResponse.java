package com.xpresspayments.ZenithBank.model.XPDirect.Model.response;


import com.xpresspayments.ZenithBank.model.response.OutFlowResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ApiOutFlowResponse {


    private String scheduleId;
    private String amount;
    private String beneficiaryName;
    private String creditAccountNumber;
    private String debitAccountNumber;
    private String narration;
    private String status;
    private String statusMessage;
    private String createdAt;
    private String updatedAt;

  public static ApiOutFlowResponse fromOutFlow(OutFlowResponse outFlowResponse) {
    return ApiOutFlowResponse.builder()
        .scheduleId(outFlowResponse.getTransactionId())
        .amount(outFlowResponse.getTransactionAmount())
        .creditAccountNumber(outFlowResponse.getCreditAccount())
        .debitAccountNumber(outFlowResponse.getMerchantAccount())
        .narration(outFlowResponse.getPaymentReference())
            //.status(outFlowResponse.getResponseCode())
            .status((getSuccessValue(outFlowResponse.getResponseCode())) ? "SUCCESSFUL" : "FAILED")
        .statusMessage(outFlowResponse.getResponseMessage())
        .build();
}

    private static boolean getSuccessValue(String responseCode) {
        if (responseCode.equals("00")) {
            return true;

        }
        return false;
    }
}
