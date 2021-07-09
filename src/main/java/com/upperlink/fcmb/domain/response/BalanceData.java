package com.upperlink.fcmb.domain.response;




import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BalanceData {

    private String accountID;
    private String accountNumber;
    private String error;

    private String customerId;
    private String accountStatus;
    private String availableBalance;
    private String lienAmount;
    private String accountName;
    private String reservedBalance;
    private String clearedBalance;
    private String unclearedBalance;
    private String currencyCode;
    private String productCode;
    private String accountDescription;
    private String productName;
    private String customerName;

    private String closed;
    private String blocked;
    private String schemeType;
    private String drawingPower;
    private String freezeReason;
    private String emailIndemnity;
    private String odLimit;
    private String totalDebit;
    private String totalCredit;
    private String totalLien;
    private String brokerName;
    private String brokerCode;
    private String accountOfficer;
    private String misCode;
}



