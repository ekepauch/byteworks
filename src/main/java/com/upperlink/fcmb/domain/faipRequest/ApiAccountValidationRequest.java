package com.upperlink.fcmb.domain.faipRequest;

import com.upperlink.fcmb.domain.request.NameEnquiryRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ApiAccountValidationRequest {

    private String accountNumber;
    private String bankCode;



    public NameEnquiryRequest toGetAccount(){
        NameEnquiryRequest nameEnquiryRequest = new NameEnquiryRequest();
        nameEnquiryRequest.setAccountNumber(accountNumber);
        nameEnquiryRequest.setDestinationInstitutionCode(bankCode);
        return nameEnquiryRequest;
    }


}
