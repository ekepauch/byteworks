package com.upperlink.fcmb.domain.faipResponse;


import com.upperlink.fcmb.domain.response.NameEnquiryResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ApiAccountValidationResponse {

    private boolean success;
    private String bankCode;
    private String accountNumber;
    private String name;
    private String phone;
    private String address;
    private String status;
    private String statusMessage;


    public static ApiAccountValidationResponse fromGetValidation(NameEnquiryResponse nameEnquiryResponse) {
        ApiAccountValidationResponse apiAccountValidationResponse = new ApiAccountValidationResponse();
        apiAccountValidationResponse.setStatus(nameEnquiryResponse.getResponseCode());
        apiAccountValidationResponse.setStatusMessage(nameEnquiryResponse.getResponseMessage());
        apiAccountValidationResponse.setAccountNumber(nameEnquiryResponse.getAccountNumber());
        apiAccountValidationResponse.setName(nameEnquiryResponse.getAccountName());
        return apiAccountValidationResponse;

    }

}
