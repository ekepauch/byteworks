package com.xpresspayments.ZenithBank.model.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BatchStatusResponse {

    private String ProcessorId;
    private String BatchId;
    private String SuccessfulCount;
    private BigDecimal SuccessfulAmount;
    private String Status;
    private String ResponseCode;
    private String ResponseMessage;


    public static BatchStatusResponse fromBatchStatusResponse(BatchStatusResponse response) {

        BatchStatusResponse batchStatusResponse = new BatchStatusResponse();
        batchStatusResponse.setProcessorId(response.getProcessorId());
        batchStatusResponse.setBatchId(response.getBatchId());
        batchStatusResponse.setSuccessfulCount(response.getSuccessfulCount());
        batchStatusResponse.setSuccessfulAmount(response.getSuccessfulAmount());
        batchStatusResponse.setStatus(response.getStatus());
        batchStatusResponse.setResponseCode(response.getResponseCode());
        batchStatusResponse.setResponseMessage(response.getResponseMessage());

        return batchStatusResponse;
    }
}
