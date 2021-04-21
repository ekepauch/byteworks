package com.upperlink.fcmb.domain.faipResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ApiBalanceRetrivalResponse {

    private boolean success;
    private String status;
    private String statusMessage;
    private String accountNumber;
    private String bankCode;
    private String accountName;
    private String availableBalance;
    private String currentBalance;
    private String createdAt;
    private String currency;


}
