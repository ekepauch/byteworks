package com.upperlink.fcmb.domain.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NameEnquiryResponse {

    private NameEnquiryData responseData;
    private String statusCode;
    private String description;

}
