package com.xpresspayments.ZenithBank.model.entity;


import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "in_flow")
public class InFlow1 {

    private Integer id;
    private String merchantAccount;
    private String debitAccount;
    private String sourceBankCode;
    private String transactionAmount;
    private String paymentReference;
    private String transactionId;
    private String processorId;
    private String OTP;
    private String AuthType ;
    private String responseCode;
    private String responseMessage;
    private Timestamp createdAt;



    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "merchant_account", nullable = true)
    public String getMerchantAccount() {
        return merchantAccount;
    }

    public void setMerchantAccount(String merchantAccount) {
        this.merchantAccount = merchantAccount;
    }

    @Basic
    @Column(name = "debit_account", nullable = true)
    public String getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(String debitAccount) {
        this.debitAccount = debitAccount;
    }

    @Basic
    @Column(name = "source_bank_code", nullable = true)
    public String getSourceBankCode() {
        return sourceBankCode;
    }

    public void setSourceBankCode(String sourceBankCode) {
        this.sourceBankCode = sourceBankCode;
    }

    @Basic
    @Column(name = "transaction_amount", nullable = true)
    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    @Basic
    @Column(name = "payment_reference", nullable = true)
    public String getPaymentReference() {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference) {
        this.paymentReference = paymentReference;
    }

    @Basic
    @Column(name = "transaction_id", nullable = true)
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Basic
    @Column(name = "processor_id", nullable = true)
    public String getProcessorId() {
        return processorId;
    }

    public void setProcessorId(String processorId) {
        this.processorId = processorId;
    }

    @Basic
    @Column(name = "otp", nullable = true)
    public String getOTP() {
        return OTP;
    }

    public void setOTP(String OTP) {
        this.OTP = OTP;
    }

    @Basic
    @Column(name = "auth_type", nullable = true)
    public String getAuthType() {
        return AuthType;
    }

    public void setAuthType(String authType) {
        AuthType = authType;
    }


    @Basic
    @Column(name = "response_code", nullable = true)
    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    @Basic
    @Column(name = "response_message", nullable = true)
    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    @Basic
    @Column(name = "created_at", nullable = false)
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        InFlow1 inFlow1 = (InFlow1) o;
        return Objects.equals(getId(), inFlow1.getId()) &&
                Objects.equals(getMerchantAccount(), inFlow1.getMerchantAccount()) &&
                Objects.equals(getDebitAccount(), inFlow1.getDebitAccount()) &&
                Objects.equals(getSourceBankCode(), inFlow1.getSourceBankCode()) &&
                Objects.equals(getTransactionAmount(), inFlow1.getTransactionAmount()) &&
                Objects.equals(getPaymentReference(), inFlow1.getPaymentReference()) &&
                Objects.equals(getTransactionId(), inFlow1.getTransactionId()) &&
                Objects.equals(getProcessorId(), inFlow1.getProcessorId()) &&
                Objects.equals(getOTP(), inFlow1.getOTP()) &&
                Objects.equals(getAuthType(), inFlow1.getAuthType()) &&
                Objects.equals(getResponseCode(), inFlow1.getResponseCode()) &&
                Objects.equals(getResponseMessage(), inFlow1.getResponseMessage()) &&
                Objects.equals(getCreatedAt(), inFlow1.getCreatedAt());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getId(), getMerchantAccount(), getDebitAccount(), getSourceBankCode(), getTransactionAmount(), getPaymentReference(), getTransactionId(), getProcessorId(), getOTP(), getAuthType(), getResponseCode(), getResponseMessage(), getCreatedAt());
    }


    @PrePersist
    public void beforeSave() {
        this.createdAt = new Timestamp(new Date().getTime());
    }
}
