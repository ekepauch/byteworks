package com.upperlink.polarisbank.domain.faipResponse;


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



}
