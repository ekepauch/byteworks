package com.xpresspayments.ZenithBank.model.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenRequest {

    private String accountNumber;
    private String processorId;
}
