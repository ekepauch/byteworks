package com.xpresspayments.ZenithBank.model.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BatchResponse {

    private String ProcessorId;
    private String BatchId;
    private BigDecimal TotalAmount;
    private String TransactionCount;
    private String MerchantAccount;
    private String ResponseCode;
    private String ResponseMessage;


    public static BatchResponse fromBatchResponse(BatchResponse response) {

        BatchResponse batchResponse = new BatchResponse();
        batchResponse.setProcessorId(response.getProcessorId());
        batchResponse.setBatchId(response.getBatchId());
        batchResponse.setTotalAmount(response.getTotalAmount());
        batchResponse.setTransactionCount(response.getTransactionCount());
        batchResponse.setMerchantAccount(response.getMerchantAccount());
        batchResponse.setResponseCode(response.getResponseCode());
        batchResponse.setResponseMessage(response.getResponseMessage());

        return batchResponse;
    }
}
