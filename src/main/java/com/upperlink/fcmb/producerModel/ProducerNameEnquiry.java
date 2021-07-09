package com.upperlink.fcmb.producerModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProducerNameEnquiry {

    private String beneficiaryAccountNumber;
    private String beneficiaryBankCode;

}
