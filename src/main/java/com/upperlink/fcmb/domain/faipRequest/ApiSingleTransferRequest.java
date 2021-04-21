package com.upperlink.fcmb.domain.faipRequest;


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
    private String narration;
    private String currency;
    private String token;
    private String bankCode;
    private String commission;
    private String scheduleId;
    private String beneficiaryName;
    private String referenceNumber;


}
