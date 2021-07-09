package com.upperlink.fcmb.domain.response;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NameEnquiryResponseDesc {


    @JsonProperty("ReferenceNumber")
    private String referenceNumber;
    @JsonProperty("DestinationInstitutionCode")
    private String destinationInstitutionCode;
    @JsonProperty("ChannelCode")
    private String channelCode;
    @JsonProperty("AccountNumber")
    private String accountNumber;
    @JsonProperty("AccountName")
    private String accountName;
    @JsonProperty("ResponseCode")
    private String responseCode;
}
