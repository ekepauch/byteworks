package com.xpresspayments.ZenithBank.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UploadResponse {

    private String BatchId;
    private String ProcessorId;
    private BigInteger TotalUploadCount;
    private BigDecimal TotalUploadAmount;
    private String ResponseCode;
    private String ResponseMessage;



    public static UploadResponse fromUploadResponse(UploadResponse response) {

        UploadResponse uploadResponse = new UploadResponse();
        uploadResponse.setProcessorId(response.getProcessorId());
        uploadResponse.setBatchId(response.getBatchId());
        uploadResponse.setTotalUploadCount(response.getTotalUploadCount());
        uploadResponse.setTotalUploadAmount(response.TotalUploadAmount);
        uploadResponse.setResponseCode(response.getResponseCode());
        uploadResponse.setResponseMessage(response.getResponseMessage());

        return uploadResponse;
    }
}
