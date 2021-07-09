package com.upperlink.fcmb.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Builder
@Data
@Entity
@Table(name = "transactions")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transactions {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String beneficiaryName;

    private BigDecimal amount;

    private String beneficiaryAccountNumber;

    private String debitAccountNo;

    private String beneficiaryBankCode;

    private String narration;

    private String responseCode;

    private String responseMessage;

    private Date transactionDate;

    private String referenceNumber;

    private String scheduleId;

    private String bankName;

}
