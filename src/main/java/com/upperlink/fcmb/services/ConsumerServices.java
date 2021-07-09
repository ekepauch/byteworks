package com.upperlink.fcmb.services;


import com.upperlink.fcmb.exceptions.BadRequestException;
import com.upperlink.fcmb.producerModel.ProducerNameEnquiry;
import com.upperlink.fcmb.producerModel.ProducerTransactionProcessing;
import com.upperlink.fcmb.util.CustomResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ConsumerServices {



    @Autowired
    private FcmbServices fcmbServices;

    @Value("${bank.code}")
    private String bankCode;






    @KafkaListener(topics = "name-enquiry", groupId = "Name-Enquiry-FCMB",containerFactory = "containerFactory")
    public void nameEnquiry(ProducerNameEnquiry producerNameEnquiry) {
        log.info(":::::::::::: kafka name enquiry records" + producerNameEnquiry);
        try {
            List<String> bank = Collections.singletonList(producerNameEnquiry.getBeneficiaryBankCode())
                    .stream()
                    .filter(c ->  c.equals(bankCode))
                    .collect(Collectors.toList());

            for (String tran : bank) {
                if(tran.equals(bankCode)){
                    fcmbServices.nameEnquiry(producerNameEnquiry);
                    log.info(":::::::::::: record sent to name enquiry service" + producerNameEnquiry);
                }
            }

        } catch (Exception e) {
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "error sending to name enquiry service");

        }

    }






    @KafkaListener(topics = "transaction-processing", groupId = "Transaction-Processing-FCMB",containerFactory = "transactionProcessingContainerFactory")
    public void transaction(ProducerTransactionProcessing producerTransactionProcessing)  {
        log.info(":::::::::::: kafka transaction records" + producerTransactionProcessing);
        try {
            List<String> bank = Collections.singletonList(producerTransactionProcessing.getBeneficiaryBankCode())
                    .stream()
                    .filter(c ->  c.equals(bankCode))
                    .collect(Collectors.toList());
            for (String tran : bank
                    ) {
                if(tran.equals(bankCode)){
                    fcmbServices.payments(producerTransactionProcessing);
                    log.info(":::::::::::: record sent to transaction service" + producerTransactionProcessing);
                }
            }
        } catch (Exception e) {
            log.error(":::::::::::::: error sending to transaction service ::::::::::",e);
        }

    }
}
