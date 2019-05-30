package com.xpresspayments.ZenithBank.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionStatusResponse {

    private String AccountNumber;
    private String ProcessorId;
    private String PaymentReference;
    private String Status;
    private String StatusMessage;
    private String ResponseCode;
    private String ResponseMessage;
}
