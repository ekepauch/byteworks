package com.xpresspayments.ZenithBank.model.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountValidationResponse {


    private String AccountNumber;
    private String AccountName;
    private String ResponseCode;
    private String ResponseMessage;
}
