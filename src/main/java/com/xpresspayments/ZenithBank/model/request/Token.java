package com.xpresspayments.ZenithBank.model.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Token {

    private String data;
}
