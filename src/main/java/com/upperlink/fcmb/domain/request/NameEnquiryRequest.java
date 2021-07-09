package com.upperlink.fcmb.domain.request;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NameEnquiryRequest {

    private String destinationInstitutionCode;
    private String channelCode;
    private String accountNumber;



}
