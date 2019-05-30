package com.xpresspayments.ZenithBank.model.entity;


import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "out_flow")
public class OutFlow1 {

    private Integer id;
    private String merchantAccount;
    private String creditAccount;
    private String destinationBankCode;
    private String transactionAmount;
    private String paymentReference;
    private String transactionId;
    private String processorId;
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
    @Column(name = "credit_account", nullable = true)
    public String getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(String creditAccount) {
        this.creditAccount = creditAccount;
    }

    @Basic
    @Column(name = "destination_bank_code", nullable = true)
    public String getDestinationBankCode() {
        return destinationBankCode;
    }

    public void setDestinationBankCode(String destinationBankCode) {
        this.destinationBankCode = destinationBankCode;
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
        OutFlow1 outFlow1 = (OutFlow1) o;
        return Objects.equals(getId(), outFlow1.getId()) &&
                Objects.equals(getMerchantAccount(), outFlow1.getMerchantAccount()) &&
                Objects.equals(getCreditAccount(), outFlow1.getCreditAccount()) &&
                Objects.equals(getDestinationBankCode(), outFlow1.getDestinationBankCode()) &&
                Objects.equals(getTransactionAmount(), outFlow1.getTransactionAmount()) &&
                Objects.equals(getPaymentReference(), outFlow1.getPaymentReference()) &&
                Objects.equals(getTransactionId(), outFlow1.getTransactionId()) &&
                Objects.equals(getProcessorId(), outFlow1.getProcessorId()) &&
                Objects.equals(getResponseCode(), outFlow1.getResponseCode()) &&
                Objects.equals(getResponseMessage(), outFlow1.getResponseMessage()) &&
                Objects.equals(getCreatedAt(), outFlow1.getCreatedAt());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getId(), getMerchantAccount(), getCreditAccount(), getDestinationBankCode(), getTransactionAmount(), getPaymentReference(), getTransactionId(), getProcessorId(), getResponseCode(), getResponseMessage(), getCreatedAt());
    }

    @PrePersist
    public void beforeSave() {
        this.createdAt = new Timestamp(new Date().getTime());
    }
}
