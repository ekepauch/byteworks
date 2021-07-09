package com.upperlink.fcmb.domain.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NameEnquiryData {


    private String status;
    private NameEnquiryResponseDesc description;
}
