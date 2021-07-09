package com.upperlink.fcmb.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.upperlink.fcmb.Repository.TransactionRepository;
import com.upperlink.fcmb.domain.request.BalanceRequest;
import com.upperlink.fcmb.domain.request.NameEnquiryRequest;
import com.upperlink.fcmb.domain.request.PaymentRequest;
import com.upperlink.fcmb.domain.response.BalanceEnquiryResponse;
import com.upperlink.fcmb.domain.response.NameEnquiryResponse;
import com.upperlink.fcmb.domain.response.PaymentResponse;
import com.upperlink.fcmb.exceptions.BadRequestException;
import com.upperlink.fcmb.model.Transactions;
import com.upperlink.fcmb.producerModel.ProducerNameEnquiry;
import com.upperlink.fcmb.producerModel.ProducerTransactionProcessing;
import com.upperlink.fcmb.util.CustomResponseCode;
import com.upperlink.fcmb.util.Utility;
import com.upperlink.fcmb.validations.Validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("ALL")
@Slf4j
@Service
public class FcmbServices {

    @Autowired
    KafkaTemplate<String, ProducerNameEnquiry> kafkaTemplate;

    @Autowired
    KafkaTemplate<String, ProducerTransactionProcessing> transactionCompletionProducerTemplate;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private Validation validation;

    @Value("${nameEnquiry.baseUrl}")
    private String nameEnquiry;

    @Value("${payment.baseUrl}")
    private String payment;

    @Value("${balance.baseUrl}")
    private String balanceEnquiry;

    @Value("${x-ibm-client-id-name}")
    private String nameEnquiryId;

    @Value("${x-ibm-client-id-payment}")
    private String paymentId;

    @Value("${x-ibm-client-id-balance}")
    private String balanceId;

    @Value("${debitAccount}")
    private String debitAccount;

    @Value("${currency}")
    private String currency;

    @Value("${remark}")
    private String remark;

    @Value("${isFees}")
    private String isFees;

    @Value("${ref_Prefix}")
    private String refPrefix;

    @Value("${bank.code}")
    private String bankCode;

    @Value("${bank.name}")
    private String bankName;

    @Value("${name-enquiry-completed}")
    private String nameEnquiryCompleted;

    @Value("${transaction-completed}")
    private String transactionCompleted;






    public NameEnquiryResponse nameEnquiry (ProducerNameEnquiry producerNameEnquiry) throws IOException {

        validation.validateNameEnquiry(producerNameEnquiry);

        String uri = nameEnquiry ;
        log.info("::::::::::  URL %s"+ uri);
        NameEnquiryResponse result = new NameEnquiryResponse();

        NameEnquiryRequest nameEnquiryRequest = NameEnquiryRequest.builder()
                .accountNumber(producerNameEnquiry.getBeneficiaryAccountNumber())
                .channelCode("")
                .destinationInstitutionCode(bankCode)
                .build();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ibm-client-id",nameEnquiryId);
        ObjectMapper objectMapper = new ObjectMapper();

        try{

            HttpEntity<String> entity = new HttpEntity(nameEnquiryRequest,headers);
            log.info(":::::::::: NAME ENQUIRY REQUEST  %s"+ entity);
            ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
            log.info(":::::::::: NAME ENQUIRY RESPONSE   %s"+ response);
            result = objectMapper.readValue(response.getBody(), NameEnquiryResponse.class);
            if(result.getStatusCode().equals("00")){

                kafkaTemplate.send(nameEnquiryCompleted,producerNameEnquiry);
                log.info("publishing name enquiry completed");
            }else{
                log.info("::: Failed name enquiry :::" + result.getResponseData().getDescription().getAccountNumber());
            }

        }catch (HttpClientErrorException ex) {
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "unable to get response ");
        }
        return result;
    }








    public PaymentResponse payments (ProducerTransactionProcessing paymentRequest) {

        validation.validateTransaction(paymentRequest);
        String uri = payment;
        log.info("::::::::::  URL %s"+ uri);
        PaymentResponse result = new PaymentResponse();

        String reference = Utility.getTimeYearMonthDay("yyyyMMddHHmmss")+Utility.getRandomNumber();
        PaymentRequest request = PaymentRequest.builder()
                .creditAccountNo(paymentRequest.getBeneficiaryAccountNumber())
                .amount(Double.parseDouble(paymentRequest.getAmount()))
                .narration(paymentRequest.getNarration())
                .currency(currency)
                .remark(remark)
                .isFees(Boolean.parseBoolean(isFees))
                .debitAccountNo(debitAccount)
                .referenceNumber(refPrefix+reference)
                .build();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ibm-client-id",paymentId);
        ObjectMapper objectMapper = new ObjectMapper();

        try{

            HttpEntity<String> entity = new HttpEntity(request, headers);
            log.info("::::::::::REQUEST  %s"+ entity);
            ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
            log.info(":::::::::: RESPONSE   %s"+ response);

            result = objectMapper.readValue(response.getBody(), PaymentResponse.class);

            if(result.getStatusCode().equals("00")){
                transactionCompletionProducerTemplate.send(transactionCompleted,paymentRequest);
                log.info("publishing transaction  completed");
            }else{
                log.info("::: Failed payment :::");
            }
            saveTransaction(request,result,paymentRequest);
        }catch (Exception ex) {
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "unable to get response ");
        }
        return result;
    }



    public BalanceEnquiryResponse balanceEnquiry (BalanceRequest balanceRequest) throws IOException {

        String accountNumber = balanceRequest.getBeneficiaryAccountNumber();
        log.info("::::::::::  REQUEST ::::::  %s" + accountNumber);


        String uri = balanceEnquiry + accountNumber;
        log.info("::::::::::  URL %s :: " + uri);
        BalanceEnquiryResponse result = new BalanceEnquiryResponse();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ibm-client-id",balanceId);
        ObjectMapper objectMapper = new ObjectMapper();

        try{
            HttpEntity<String> entity = new HttpEntity(null,headers);
            log.info("::::::::::  REQUEST  %s"+ entity);
            ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
            log.info("::::::::::  RESPONSE   %s"+ response);
            result = objectMapper.readValue(response.getBody(), BalanceEnquiryResponse.class);

        }catch (HttpClientErrorException ex) {
            throw new BadRequestException(CustomResponseCode.BAD_REQUEST, "unable to get response ");
        }
        return result;
    }


    private void saveTransaction(PaymentRequest request, PaymentResponse paymentResponse, ProducerTransactionProcessing paymentRequest) {
        Transactions transactions = Transactions.builder()
                .amount(BigDecimal.valueOf(request.getAmount()))
                .beneficiaryAccountNumber(request.getCreditAccountNo())
                .beneficiaryBankCode(bankCode)
                .debitAccountNo(debitAccount)
                .narration(request.getNarration())
                .responseCode(paymentResponse.getStatusCode())
                .responseMessage(paymentResponse.getDescription())
                .transactionDate(new Date())
                .referenceNumber(request.getReferenceNumber())
                .scheduleId(paymentRequest.getReferenceNumber())
                .bankName(bankName)
                .build();
        validation.validateSavedTransaction(transactions);
        transactionRepository.save(transactions);

    }


}
