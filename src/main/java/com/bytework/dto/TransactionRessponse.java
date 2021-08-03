package com.bytework.dto;


import com.bytework.paystack.DataResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionRessponse {

    private String status;
    private String message;
    private DataResponse data;
}
