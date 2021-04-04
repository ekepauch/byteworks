package com.upperlink.polarisbank.domain.faipResponse;


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



}
