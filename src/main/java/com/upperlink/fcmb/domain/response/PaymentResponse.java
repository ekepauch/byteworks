package com.upperlink.fcmb.domain.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentResponse {

    private String statusCode;
    private String description;
    private PaymentResponseData responseData;

}
