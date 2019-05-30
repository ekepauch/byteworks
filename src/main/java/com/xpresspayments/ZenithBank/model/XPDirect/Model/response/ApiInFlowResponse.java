package com.xpresspayments.ZenithBank.model.XPDirect.Model.response;


import com.xpresspayments.ZenithBank.model.response.InflowResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ApiInFlowResponse {

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


    public static ApiInFlowResponse frominFlow(InflowResponse inflowResponse) {
        return ApiInFlowResponse
                .builder()
                .amount(inflowResponse.getTransactionAmount())
                .creditAccountNumber(inflowResponse.getMerchantAccount())
                .debitAccountNumber(inflowResponse.getDebitAccount())
                .narration(inflowResponse.getPaymentReference())
                .scheduleId(inflowResponse.getTransactionId())
                .status(inflowResponse.getResponseCode())
                .statusMessage(inflowResponse.getResponseMessage())
                .build();

    }
}
