package com.upperlink.fcmb.validations;


import com.upperlink.fcmb.exceptions.BadRequestException;
import com.upperlink.fcmb.model.Transactions;
import com.upperlink.fcmb.producerModel.ProducerNameEnquiry;
import com.upperlink.fcmb.producerModel.ProducerTransactionProcessing;
import com.upperlink.fcmb.util.CustomResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class Validation {





    public void validateTransaction(ProducerTransactionProcessing transactionProcessing) {

        if (transactionProcessing.getBeneficiaryAccountNumber() == null || transactionProcessing.getBeneficiaryAccountNumber().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Account number cannot be empty");

        if (transactionProcessing.getAmount() == null || transactionProcessing.getAmount().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Amount cannot be empty");

        if (transactionProcessing.getNarration() == null || transactionProcessing.getNarration().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Narration cannot be empty");
    }


    public void validateNameEnquiry(ProducerNameEnquiry producerNameEnquiry) {
        if (producerNameEnquiry.getBeneficiaryAccountNumber() == null || producerNameEnquiry.getBeneficiaryAccountNumber().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Account number cannot be empty");

    }



    public void validateSavedTransaction(Transactions transactions) {
        if (transactions.getBeneficiaryAccountNumber() == null || transactions.getBeneficiaryAccountNumber().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Account number cannot be empty");

        if (transactions.getBeneficiaryBankCode() == null || transactions.getBeneficiaryBankCode().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Bank code cannot be empty");

        if (transactions.getAmount() == null )
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Amount cannot be empty");

        if (transactions.getNarration() == null || transactions.getNarration().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "narration cannot be empty");

        if (transactions.getScheduleId() == null || transactions.getScheduleId().isEmpty())
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "Schedule id cannot be empty");
    }
}
