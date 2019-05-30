package com.xpresspayments.ZenithBank.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransferResponse {


    private String DebitAccount;
    private String SourceBankCode;
    private String CreditAccount;
    private String DestinationBankCode;
    private String TransactionAmount;
    private String PaymentReference;
    private String TransactionId;
    private String ProcessorId;
    private String ChannelId;
    private String ResponseCode;
    private String ResponseMessage;
}
