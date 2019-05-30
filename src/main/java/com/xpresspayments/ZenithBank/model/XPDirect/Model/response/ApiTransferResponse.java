package com.xpresspayments.ZenithBank.model.XPDirect.Model.response;


import com.xpresspayments.ZenithBank.model.request.TransferRequest;
import com.xpresspayments.ZenithBank.model.response.TransferResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ApiTransferResponse {


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

    public static ApiTransferResponse fromTransfer(TransferResponse transferResponse) {
        return ApiTransferResponse.builder()
                .scheduleId(transferResponse.getTransactionId())
                .amount(transferResponse.getTransactionAmount())
                .creditAccountNumber(transferResponse.getCreditAccount())
                .debitAccountNumber(transferResponse.getDebitAccount())
                .narration(transferResponse.getPaymentReference())
               // .beneficiaryName(transferRequest.getDestinationBankCode())
                //.status(outFlowResponse.getResponseCode())
                .status((getSuccessValue(transferResponse.getResponseCode())) ? "SUCCESSFUL" : "FAILED")
                .statusMessage(transferResponse.getResponseMessage())

                .build();
    }

    private static boolean getSuccessValue(String responseCode) {
        if (responseCode.equals("00")) {
            return true;

        }
        return false;
    }
}
