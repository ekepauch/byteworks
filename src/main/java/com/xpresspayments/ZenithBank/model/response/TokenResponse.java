package com.xpresspayments.ZenithBank.model.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenResponse {

    private String AccountNumber;
    private String ResponseCode;
    private String ResponseMessage;

}
