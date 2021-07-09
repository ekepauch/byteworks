package com.upperlink.fcmb.domain.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BalanceEnquiryResponse {

    private BalanceData responseData;
    private String description;
    private String statusCode;




}
