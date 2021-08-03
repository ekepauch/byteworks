package com.bytework.paystack;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentResponse {

    private String status;
    private String message;
    private DataResponse data;
}
