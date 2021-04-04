package com.upperlink.polarisbank.domain.faipResponse;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ApiTokenResponse {


    private String status;
    private String statusMessage;




}
