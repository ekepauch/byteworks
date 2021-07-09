package com.upperlink.fcmb.domain.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentRequest {




    private String debitAccountNo;
    private String creditAccountNo;
    private double amount;
    private boolean isFees;
    private String narration;
    private String currency;
    private String remark;
    private String referenceNumber;

}
