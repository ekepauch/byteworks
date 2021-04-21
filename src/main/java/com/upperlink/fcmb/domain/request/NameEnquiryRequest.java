package com.upperlink.fcmb.domain.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NameEnquiryRequest {

    private String destinationInstitutionCode;
    private String channelCode;
    private String accountNumber;
}
