package com.upperlink.polarisbank.domain.faipRequest;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ApiTokenRequest {

    private String accountNumber;
    private String accountName;
    private String scheduleId;




}
