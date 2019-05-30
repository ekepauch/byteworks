package com.xpresspayments.ZenithBank.zenithbank.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.xpresspayments.ZenithBank.Repository.InFlowRepository;
import com.xpresspayments.ZenithBank.Repository.OutFlowRepository;
import com.xpresspayments.ZenithBank.model.XPDirect.Model.request.*;
import com.xpresspayments.ZenithBank.model.XPDirect.Model.response.*;
import com.xpresspayments.ZenithBank.model.entity.InFlow1;
import com.xpresspayments.ZenithBank.model.entity.OutFlow1;
import com.xpresspayments.ZenithBank.model.request.*;
import com.xpresspayments.ZenithBank.model.response.*;
import com.xpresspayments.ZenithBank.util.PGPUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.util.List;


@Service
public class ZenithServices {

  private static final Logger logger = LoggerFactory.getLogger(ZenithServices.class);

  @Value("${token.base_url}")
  private String token;
  @Value("${balance.base_url}")
  private String balance;
  @Value("${out_flow.base_url}")
  private String outflow;
  @Value("${in_flow.base_url}")
  private String inflow;
  @Value("${name_enquiry.base_url}")
  private String nameEnquiry;
  @Value("${requery.base_url}")
  private String requery;
  @Value("${transfer.base_url}")
  private String transfer;

  @Value("${batch.base_url}")
  private String batch;
  @Value("${upload.base_url}")
  private String upload;
  @Value("${batchStatus.base_url}")
  private String batchStatus;

  @Value("${zenith.processId}")
  private String processId;
  @Value("${zenith.merchantAccount}")
  private String account;
  @Value("${zenith.channelId}")
  private String channelId;

  @Value("${zenith.maximum_records}")
  private String MAXIMUM_RECORDS;

  @Value("${zt.public_key}")
  private String publickey;
  @Value("${zt.private_key}")
  private String privatekey;



  @Autowired
  OutFlowRepository outFlowRepository;

  @Autowired
  InFlowRepository inFlowRepository;


  public ApiTokenResponse TokenRequest(ApiTokenRequest apiTokenRequest) throws Exception {

    TokenRequest tokenRequest = apiTokenRequest.toToken();
    tokenRequest.setProcessorId(processId);
    String uri = token;
    logger.info(String.format(":::::::::: tokenRequest URL %s", uri));
    TokenResponse result = new TokenResponse();

    // =================  converting encryption to JSON
    // ------------------------------------------------------------
    Gson gs = new Gson();
    String request1 = gs.toJson(tokenRequest);
    logger.info(String.format(":::::::::: REQUEST %s", tokenRequest));
    PGPUtils pgpUtils = new PGPUtils();

    request1 = pgpUtils.encryptMessage(request1,publickey);
    logger.info(String.format(":::::::::: ENCRYPTEDREQUEST %s", request1));
    request1 = Hex.encodeHexString(request1.getBytes());
    Token encryptedRequest = new Token();
    encryptedRequest.setData(request1);
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      HttpEntity<String> entity = new HttpEntity(encryptedRequest, headers);
      logger.info(String.format(":::::::::: ENCRYPTEDREQUEST %s", encryptedRequest));

      ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
      logger.info(String.format("::::::::::OutFlow1 RESPONSE FROM BANK %s", response));

      TokenData mainResponse = new Gson().fromJson(response.getBody(), TokenData.class);

      String encodedHexB64 = this.getBase64FromHEX(mainResponse.getResponse());
      logger.info(String.format(":::::::::: decodeHEX response %s", encodedHexB64));

      String mainEncryptionSting =
              String.format(
                      "-----BEGIN PGP MESSAGE-----\n"
                              + "Version: BCPG v1.54\n\n\n"
                              + "%s"
                              + "\n"
                              + "-----END PGP MESSAGE-----",
                      encodedHexB64);
      logger.info(String.format(":::::::::: pgp file %s", mainEncryptionSting));
      System.out.println("::::::::: pgp file : " + mainEncryptionSting);

      String response1 = pgpUtils.decryptMessage(mainEncryptionSting,privatekey);
      System.out.println("::::::::: decrypted response : " + response1);
      logger.info(String.format(":::::::::: decrypted response  %s", response1));

      result = gs.fromJson(response1, TokenResponse.class);
      System.out.println("After casting: "+ result.toString());

      System.out.println("::::::::: decrypted response : " + result.getResponseMessage());

    }catch (Exception ex) {
      logger.info(String.format(":::: ERROR %s",ex.getMessage()));
    }
    return ApiTokenResponse.fromToken(result);

  }






  public ApiBalanceRetrivalResponse Balance(ApiBalanceRetrivalRequest apiBalanceRetrivalRequest) throws Exception {
    BalanceEnquiryRequest balanceEnquiryRequest = apiBalanceRetrivalRequest.toGetBalance();
    balanceEnquiryRequest.setProcessorId(processId);
    String uri = balance;
    System.out.println("::::::::: RESPONSE FROM balanceRequest URL : " + uri);
    logger.info(String.format(":::::::::: balanceRequest URL %s", uri));
    BalanceEnquiryResponse result = new BalanceEnquiryResponse();
    // =================  converting encryption to JSON
    // ------------------------------------------------------------
    Gson gs = new Gson();
    String request1 = gs.toJson(balanceEnquiryRequest);
    System.out.println("::::::::: REQUEST  : " + balanceEnquiryRequest);
    System.out.println(":::: Zenith service key "+publickey);
    logger.info(String.format(":::::::::: REQUEST %s", balanceEnquiryRequest));
    PGPUtils pgpUtils = new PGPUtils();
    request1 = pgpUtils.encryptMessage(request1,publickey);
    System.out.println("PGP: " + request1);
    logger.info(String.format(":::::::::: PGP %s", request1));
    request1 = Hex.encodeHexString(request1.getBytes());
    System.out.println("Base64 PGP (HEX): " + request1);
    Balance encryptedRequest = new Balance();
    encryptedRequest.setData(request1);
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      HttpEntity<String> entity = new HttpEntity(encryptedRequest, headers);
      System.out.println("::::::::: ENCRYPTEDREQUEST  : " + request1);
      logger.info(String.format(":::::::::: ENCRYPTEDREQUEST %s", request1));
      logger.info(String.format(":::::::::: ENCRYPTEDREQUEST %s", request1.length()));


        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
        System.out.println("::::::::: Balance RESPONSE FROM ZENITH : " + response);
        logger.info(String.format(":::::::::: Balance RESPONSE FROM ZENITH %s", response));

        BalanceResponse mainResponse = new Gson().fromJson(response.getBody(), BalanceResponse.class);
        System.out.println("Balance response: "+ mainResponse.getResponse());

       String encodedHexB64 = this.getBase64FromHEX(mainResponse.getResponse());
      System.out.println("::::::::: encode to base64 : " + encodedHexB64);
      logger.info(String.format(":::::::::: decodeHEX response %s", encodedHexB64));

      String mainEncryptionSting =
              String.format(
                      "-----BEGIN PGP MESSAGE-----\n"
                              + "Version: BCPG v1.54\n\n\n"
                              + "%s"
                              + "\n"
                              + "-----END PGP MESSAGE-----",
                      encodedHexB64);
      logger.info(String.format(":::::::::: pgp file %s", mainEncryptionSting));
      System.out.println("::::::::: pgp file : " + mainEncryptionSting);

      String response1 = pgpUtils.decryptMessage(mainEncryptionSting,privatekey);
      System.out.println("::::::::: decrypted response : " + response1);
      logger.info(String.format(":::::::::: decrypted response  %s", response1));

      result = gs.fromJson(response1, BalanceEnquiryResponse.class);
      System.out.println("After casting: "+ result.toString());

      }catch (Exception ex) {
        logger.info(String.format(":::: ERROR %s",ex.getMessage()));
      }
    return ApiBalanceRetrivalResponse.fromGetBalance(result);
  }



  public ApiOutFlowResponse outflow(ApiOutFlowRequest apiOutFlowRequest) throws Exception {
    OutFlowRequest outFlowRequest = apiOutFlowRequest.toOutFlow();
    outFlowRequest.setProcessorId(processId);
    outFlowRequest.setMerchantAccount(account);
    String uri = outflow;
    System.out.println("::::::::: RESPONSE FROM Request URL : " + uri);
    logger.info(String.format(":::::::::: Request URL %s", uri));
    OutFlowResponse result = new OutFlowResponse();
    // =================  converting encryption to JSON
    // ------------------------------------------------------------
    Gson gs = new Gson();
    String request1 = gs.toJson(outFlowRequest);
    System.out.println("::::::::: REQUEST  : " + outFlowRequest);
    logger.info(String.format(":::::::::: REQUEST %s", outFlowRequest));
    PGPUtils pgpUtils = new PGPUtils();

    request1 = pgpUtils.encryptMessage(request1,publickey);
    System.out.println("PGP: " + request1);
    request1 = Hex.encodeHexString(request1.getBytes());
    System.out.println("Base64 PGP (HEX): " + request1);
    OutFlow encryptedRequest = new OutFlow();
    encryptedRequest.setData(request1);
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    try {
      HttpEntity<String> entity = new HttpEntity(encryptedRequest, headers);
      System.out.println("::::::::: ENCRYPTEDREQUEST  : " + request1);
      logger.info(String.format(":::::::::: ENCRYPTEDREQUEST %s", request1));
      logger.info(String.format(":::::::::: ENCRYPTEDREQUEST %s", request1.length()));

      ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
      System.out.println("::::::::: Out flow RESPONSE FROM ZENITH : " + response);
      logger.info(String.format(":::::::::: Out flow RESPONSE FROM ZENITH %s", response));

      OutFlowData mainResponse = new Gson().fromJson(response.getBody(), OutFlowData.class);
      System.out.println("Out flow response: "+ mainResponse.getResponse());

      String encodedHexB64 = this.getBase64FromHEX(mainResponse.getResponse());
      System.out.println("::::::::: encode to base64 : " + encodedHexB64);
      logger.info(String.format(":::::::::: decodeHEX response %s", encodedHexB64));

      String mainEncryptionSting =
              String.format(
                      "-----BEGIN PGP MESSAGE-----\n"
                              + "Version: BCPG v1.54\n\n\n"
                              + "%s"
                              + "\n"
                              + "-----END PGP MESSAGE-----",
                      encodedHexB64);
      logger.info(String.format(":::::::::: pgp file %s", mainEncryptionSting));
      System.out.println("::::::::: pgp file : " + mainEncryptionSting);

      String response1 = pgpUtils.decryptMessage(mainEncryptionSting,privatekey);
      System.out.println("::::::::: decrypted response : " + response1);
      logger.info(String.format(":::::::::: decrypted response  %s", response1));

      result = gs.fromJson(response1, OutFlowResponse.class);
      System.out.println("After casting: "+ result.toString());

      System.out.println("::::::::: decrypted response : " + result.getResponseMessage());
      OutFlowResponse outFlowResponse = OutFlowResponse.fromOutFlowResponse(result);
      saveOutFlow(outFlowRequest,outFlowResponse);

    }catch (Exception ex) {
      logger.info(String.format(":::: ERROR %s",ex.getMessage()));
    }

    OutFlowResponse outFlowResponse = OutFlowResponse.fromOutFlowResponse(result);
    return ApiOutFlowResponse.fromOutFlow(outFlowResponse);
  }


  private void saveOutFlow(OutFlowRequest outFlowRequest, OutFlowResponse outFlowResponse) {
    OutFlow1 outFlow1 = new OutFlow1();
    outFlow1.setResponseMessage(outFlowResponse.getResponseMessage());
    outFlow1.setResponseCode(outFlowResponse.getResponseCode());
    outFlow1.setTransactionId(outFlowRequest.getTransactionId());
    outFlow1.setTransactionAmount(outFlowRequest.getTransactionAmount());
    outFlow1.setProcessorId(outFlowRequest.getProcessorId());
    outFlow1.setPaymentReference(outFlowRequest.getPaymentReference());
    outFlow1.setMerchantAccount(outFlowRequest.getMerchantAccount());
    outFlow1.setDestinationBankCode(outFlowRequest.getDestinationBankCode());
    outFlow1.setCreditAccount(outFlowRequest.getCreditAccount());
    outFlowRepository.save(outFlow1);
  }



  public InflowResponse InFlow(InflowRequest inflowRequest) throws Exception {
    //InflowRequest inflowRequest = apiInflowRequest.toinFlow();
    inflowRequest.setProcessorId(processId);
    inflowRequest.setMerchantAccount(account);

    String uri = inflow;
    System.out.println("::::::::: RESPONSE FROM Request URL : " + uri);
    logger.info(String.format(":::::::::: Request URL %s", uri));
    InflowResponse result = new InflowResponse();

    // =================  converting encryption to JSON
    // ------------------------------------------------------------
    Gson gs = new Gson();
    String request1 = gs.toJson(inflowRequest);
    System.out.println("::::::::: REQUEST  : " + inflowRequest);
    logger.info(String.format(":::::::::: REQUEST %s", inflowRequest));
    PGPUtils pgpUtils = new PGPUtils();

    request1 = pgpUtils.encryptMessage(request1,publickey);
    System.out.println("PGP: " + request1);
    request1 = Hex.encodeHexString(request1.getBytes());
    System.out.println("Base64 PGP (HEX): " + request1);
    InFlow encryptedRequest = new InFlow();
    encryptedRequest.setData(request1);
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    try {
      HttpEntity<String> entity = new HttpEntity(encryptedRequest, headers);
      System.out.println("::::::::: ENCRYPTEDREQUEST  : " + request1);
      logger.info(String.format(":::::::::: ENCRYPTEDREQUEST %s", request1));

      ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
      System.out.println("::::::::: Out flow RESPONSE FROM ZENITH : " + response);
      logger.info(String.format(":::::::::: Out flow RESPONSE FROM ZENITH %s", response));

      InFlowData mainResponse = new Gson().fromJson(response.getBody(), InFlowData.class);
      System.out.println("Out flow response: "+ mainResponse.getResponse());

      String encodedHexB64 = this.getBase64FromHEX(mainResponse.getResponse());
      System.out.println("::::::::: encode to base64 : " + encodedHexB64);
      logger.info(String.format(":::::::::: decodeHEX response %s", encodedHexB64));

      String mainEncryptionSting =
              String.format(
                      "-----BEGIN PGP MESSAGE-----\n"
                              + "Version: BCPG v1.54\n\n\n"
                              + "%s"
                              + "\n"
                              + "-----END PGP MESSAGE-----",
                      encodedHexB64);
      logger.info(String.format(":::::::::: pgp file %s", mainEncryptionSting));
      System.out.println("::::::::: pgp file : " + mainEncryptionSting);

      String response1 = pgpUtils.decryptMessage(mainEncryptionSting,privatekey);
      System.out.println("::::::::: decrypted response : " + response1);
      logger.info(String.format(":::::::::: decrypted response  %s", response1));

      result = gs.fromJson(response1, InflowResponse.class);
      System.out.println("After casting: "+ result.toString());

      InflowResponse inflowResponse = InflowResponse.frominFlowResponse(result);
      saveInFlow(inflowRequest,inflowResponse);

    }catch (Exception ex) {
      logger.info(String.format(":::: ERROR %s",ex.getMessage()));
    }
   return result;
  }


  private void saveInFlow(InflowRequest inflowRequest, InflowResponse inflowResponse) {
    InFlow1 inFlow1 = new InFlow1();
    inFlow1.setResponseMessage(inflowResponse.getResponseMessage());
    inFlow1.setResponseCode(inflowResponse.getResponseCode());
    inFlow1.setTransactionId(inflowRequest.getTransactionId());
    inFlow1.setTransactionAmount(inflowRequest.getTransactionAmount());
    inFlow1.setProcessorId(inflowRequest.getProcessorId());
    inFlow1.setPaymentReference(inflowRequest.getPaymentReference());
    inFlow1.setMerchantAccount(inflowRequest.getMerchantAccount());
    inFlow1.setSourceBankCode(inflowRequest.getSourceBankCode());
    inFlow1.setDebitAccount(inflowRequest.getDebitAccount());
    inFlowRepository.save(inFlow1);
  }




  public ApiAccountValidationResponse nameRenqiry(ApiAccountValidationRequest apiAccountValidationRequest) throws Exception {
    AccountValidationRequest accountValidationRequest = apiAccountValidationRequest.toGetAccount();
    accountValidationRequest.setProcessorId(processId);

    String uri = nameEnquiry;
    System.out.println("::::::::: RESPONSE FROM Request URL : " + uri);
    logger.info(String.format(":::::::::: Request URL %s", uri));
    AccountValidationResponse result = new AccountValidationResponse();

    // =================  converting encryption to JSON
    // ------------------------------------------------------------
    Gson gs = new Gson();
    String request1 = gs.toJson(accountValidationRequest);
    System.out.println("::::::::: REQUEST  : " + accountValidationRequest);
    logger.info(String.format(":::::::::: REQUEST %s", accountValidationRequest));
    PGPUtils pgpUtils = new PGPUtils();

    request1 = pgpUtils.encryptMessage(request1,publickey);
    System.out.println("PGP: " + request1);
    request1 = Hex.encodeHexString(request1.getBytes());
    System.out.println("Base64 PGP (HEX): " + request1);
    AccountValidation encryptedRequest = new AccountValidation();
    encryptedRequest.setData(request1);
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    try {
      HttpEntity<String> entity = new HttpEntity(encryptedRequest, headers);
      System.out.println("::::::::: ENCRYPTEDREQUEST  : " + request1);
      logger.info(String.format(":::::::::: ENCRYPTEDREQUEST %s", request1));

      ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
      System.out.println("::::::::: name RESPONSE FROM ZENITH : " + response);
      logger.info(String.format(":::::::::: name RESPONSE FROM ZENITH %s", response));

      Validation mainResponse = new Gson().fromJson(response.getBody(), Validation.class);
      System.out.println("name response: "+ mainResponse.getResponse());

      String encodedHexB64 = this.getBase64FromHEX(mainResponse.getResponse());
      System.out.println("::::::::: encode to base64 : " + encodedHexB64);
      logger.info(String.format(":::::::::: decodeHEX response %s", encodedHexB64));

      String mainEncryptionSting =
              String.format(
                      "-----BEGIN PGP MESSAGE-----\n"
                              + "Version: BCPG v1.54\n\n\n"
                              + "%s"
                              + "\n"
                              + "-----END PGP MESSAGE-----",
                      encodedHexB64);
      logger.info(String.format(":::::::::: pgp file %s", mainEncryptionSting));
      System.out.println("::::::::: pgp file : " + mainEncryptionSting);

      String response1 = pgpUtils.decryptMessage(mainEncryptionSting,privatekey);
      System.out.println("::::::::: decrypted response : " + response1);
      logger.info(String.format(":::::::::: decrypted response  %s", response1));

      result = gs.fromJson(response1, AccountValidationResponse.class);
      System.out.println("After casting: "+ result.toString());


    }catch (Exception ex) {
      logger.info(String.format(":::: ERROR %s",ex.getMessage()));
    }
    return ApiAccountValidationResponse.fromGetValidation(result);
  }




  public TransactionStatusResponse Requery(TransactionStatusRequest transactionStatusRequest) throws Exception {
    //InflowRequest inflowRequest = apiInflowRequest.toinFlow();
    transactionStatusRequest.setProcessorId(processId);


    String uri = requery;
    System.out.println("::::::::: RESPONSE FROM Request URL : " + uri);
    logger.info(String.format(":::::::::: Request URL %s", uri));
    TransactionStatusResponse result = new TransactionStatusResponse();

    // =================  converting encryption to JSON
    // ------------------------------------------------------------
    Gson gs = new Gson();
    String request1 = gs.toJson(transactionStatusRequest);
    System.out.println("::::::::: REQUEST  : " + transactionStatusRequest);
    logger.info(String.format(":::::::::: REQUEST %s", transactionStatusRequest));
    PGPUtils pgpUtils = new PGPUtils();

    request1 = pgpUtils.encryptMessage(request1,publickey);
    System.out.println("PGP: " + request1);
    request1 = Hex.encodeHexString(request1.getBytes());
    System.out.println("Base64 PGP (HEX): " + request1);
    TransactionStatus encryptedRequest = new TransactionStatus();
    encryptedRequest.setData(request1);
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    try {
      HttpEntity<String> entity = new HttpEntity(encryptedRequest, headers);
      System.out.println("::::::::: ENCRYPTEDREQUEST  : " + request1);
      logger.info(String.format(":::::::::: ENCRYPTEDREQUEST %s", request1));

      ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
      System.out.println("::::::::: requery RESPONSE FROM ZENITH : " + response);
      logger.info(String.format(":::::::::: requery RESPONSE FROM ZENITH %s", response));

      Transaction mainResponse = new Gson().fromJson(response.getBody(), Transaction.class);
      System.out.println("Out flow response: "+ mainResponse.getResponse());

      String encodedHexB64 = this.getBase64FromHEX(mainResponse.getResponse());
      System.out.println("::::::::: encode to base64 : " + encodedHexB64);
      logger.info(String.format(":::::::::: decodeHEX response %s", encodedHexB64));

      String mainEncryptionSting =
              String.format(
                      "-----BEGIN PGP MESSAGE-----\n"
                              + "Version: BCPG v1.54\n\n\n"
                              + "%s"
                              + "\n"
                              + "-----END PGP MESSAGE-----",
                      encodedHexB64);
      logger.info(String.format(":::::::::: pgp file %s", mainEncryptionSting));
      System.out.println("::::::::: pgp file : " + mainEncryptionSting);

      String response1 = pgpUtils.decryptMessage(mainEncryptionSting,privatekey);
      System.out.println("::::::::: decrypted response : " + response1);
      logger.info(String.format(":::::::::: decrypted response  %s", response1));

      result = gs.fromJson(response1, TransactionStatusResponse.class);
      System.out.println("After casting: "+ result.toString());



    }catch (Exception ex) {
      logger.info(String.format(":::: ERROR %s",ex.getMessage()));
    }
    return result;
  }




  public TransferResponse Transfer(TransferRequest transferRequest) throws Exception {
    //InflowRequest inflowRequest = apiInflowRequest.toinFlow();
    transferRequest.setProcessorId(processId);
    transferRequest.setChannelId(channelId);

    String uri = transfer;
    System.out.println("::::::::: RESPONSE FROM Request URL : " + uri);
    logger.info(String.format(":::::::::: Request URL %s", uri));
    TransferResponse result = new TransferResponse();

    // =================  converting encryption to JSON
    // ------------------------------------------------------------
    Gson gs = new Gson();
    String request1 = gs.toJson(transferRequest);
    System.out.println("::::::::: REQUEST  : " + transferRequest);
    logger.info(String.format(":::::::::: REQUEST %s", transferRequest));
    PGPUtils pgpUtils = new PGPUtils();

    request1 = pgpUtils.encryptMessage(request1,publickey);
    System.out.println("PGP: " + request1);
    request1 = Hex.encodeHexString(request1.getBytes());
    System.out.println("Base64 PGP (HEX): " + request1);
    TransferData encryptedRequest = new TransferData();
    encryptedRequest.setData(request1);
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    try {
      HttpEntity<String> entity = new HttpEntity(encryptedRequest, headers);
      System.out.println("::::::::: ENCRYPTEDREQUEST  : " + request1);
      logger.info(String.format(":::::::::: ENCRYPTEDREQUEST %s", request1));

      ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
      System.out.println(":::::::::  RESPONSE FROM ZENITH : " + response);
      logger.info(String.format(":::::::::: RESPONSE FROM ZENITH %s", response));

      Transfer mainResponse = new Gson().fromJson(response.getBody(), Transfer.class);
      System.out.println(" response: "+ mainResponse.getResponse());

      String encodedHexB64 = this.getBase64FromHEX(mainResponse.getResponse());
      System.out.println("::::::::: encode to base64 : " + encodedHexB64);
      logger.info(String.format(":::::::::: decodeHEX response %s", encodedHexB64));

      String mainEncryptionSting =
              String.format(
                      "-----BEGIN PGP MESSAGE-----\n"
                              + "Version: BCPG v1.54\n\n\n"
                              + "%s"
                              + "\n"
                              + "-----END PGP MESSAGE-----",
                      encodedHexB64);
      logger.info(String.format(":::::::::: pgp file %s", mainEncryptionSting));
      System.out.println("::::::::: pgp file : " + mainEncryptionSting);

      String response1 = pgpUtils.decryptMessage(mainEncryptionSting,privatekey);
      System.out.println("::::::::: decrypted response : " + response1);
      logger.info(String.format(":::::::::: decrypted response  %s", response1));

      result = gs.fromJson(response1, TransferResponse.class);
      System.out.println("After casting: "+ result.toString());



    }catch (Exception ex) {
      logger.info(String.format(":::: ERROR %s",ex.getMessage()));
    }
    return result;
  }







  public BatchResponse CreateBatch (BatchRequest batchRequest) throws Exception {
    //InflowRequest inflowRequest = apiInflowRequest.toinFlow();
    batchRequest.setProcessorId(processId);
  //  transferRequest.setChannelId(channelId);

    String uri = batch;
    System.out.println("::::::::: RESPONSE FROM Request URL : " + uri);
    logger.info(String.format(":::::::::: Request URL %s", uri));
    BatchResponse result = new BatchResponse();

    // =================  converting encryption to JSON
    // ------------------------------------------------------------
    Gson gs = new Gson();
    String request1 = gs.toJson(batchRequest);
    System.out.println("::::::::: REQUEST  : " + batchRequest);
    logger.info(String.format(":::::::::: REQUEST %s", batchRequest));
    PGPUtils pgpUtils = new PGPUtils();

    request1 = pgpUtils.encryptMessage(request1,publickey);
    System.out.println("PGP: " + request1);
    request1 = Hex.encodeHexString(request1.getBytes());
    System.out.println("Base64 PGP (HEX): " + request1);
    Batch encryptedRequest = new Batch();
    encryptedRequest.setData(request1);
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    try {
      HttpEntity<String> entity = new HttpEntity(encryptedRequest, headers);
      System.out.println("::::::::: ENCRYPTEDREQUEST  : " + request1);
      logger.info(String.format(":::::::::: ENCRYPTEDREQUEST %s", request1));

      ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
      System.out.println(":::::::::  RESPONSE FROM ZENITH : " + response);
      logger.info(String.format(":::::::::: RESPONSE FROM ZENITH %s", response));

      BatchData mainResponse = new Gson().fromJson(response.getBody(), BatchData.class);
      System.out.println(" response: "+ mainResponse.getResponse());

      String encodedHexB64 = this.getBase64FromHEX(mainResponse.getResponse());
      System.out.println("::::::::: encode to base64 : " + encodedHexB64);
      logger.info(String.format(":::::::::: decodeHEX response %s", encodedHexB64));

      String mainEncryptionSting =
              String.format(
                      "-----BEGIN PGP MESSAGE-----\n"
                              + "Version: BCPG v1.54\n\n\n"
                              + "%s"
                              + "\n"
                              + "-----END PGP MESSAGE-----",
                      encodedHexB64);
      logger.info(String.format(":::::::::: pgp file %s", mainEncryptionSting));
      System.out.println("::::::::: pgp file : " + mainEncryptionSting);

      String response1 = pgpUtils.decryptMessage(mainEncryptionSting,privatekey);
      System.out.println("::::::::: decrypted response : " + response1);
      logger.info(String.format(":::::::::: decrypted response  %s", response1));

      result = gs.fromJson(response1, BatchResponse.class);
      System.out.println("After casting: "+ result.toString());



    }catch (Exception ex) {
      logger.info(String.format(":::: ERROR %s",ex.getMessage()));
    }
    return result;
  }






  public  UploadResponse uploadBatch (UploadRequest uploadRequest) throws Exception {

    uploadRequest.setProcessorId(processId);
    //  transferRequest.setChannelId(channelId);

    String uri = upload;
    System.out.println("::::::::: RESPONSE FROM Request URL : " + uri);
    logger.info(String.format(":::::::::: Request URL %s", uri));
    UploadResponse result = new UploadResponse();


    // =================  converting encryption to JSON
    // ------------------------------------------------------------

    Gson gs = new Gson();
    String request1 = gs.toJson(uploadRequest);
    System.out.println("::::::::: REQUEST  : " + uploadRequest);
    logger.info(String.format(":::::::::: REQUEST %s", uploadRequest));
    PGPUtils pgpUtils = new PGPUtils();

    request1 = pgpUtils.encryptMessage(request1,publickey);
    System.out.println("PGP: " + request1);
    request1 = Hex.encodeHexString(request1.getBytes());
    System.out.println("Base64 PGP (HEX): " + request1);
    UploadData encryptedRequest = new UploadData();
    encryptedRequest.setData(request1);
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    try {
      HttpEntity<String> entity = new HttpEntity(encryptedRequest, headers);
      System.out.println("::::::::: ENCRYPTEDREQUEST  : " + request1);
      logger.info(String.format(":::::::::: ENCRYPTEDREQUEST %s", request1));

      ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
      System.out.println(":::::::::  RESPONSE FROM ZENITH : " + response);
      logger.info(String.format(":::::::::: RESPONSE FROM ZENITH %s", response));

      Upload mainResponse = new Gson().fromJson(response.getBody(), Upload.class);
      System.out.println(" response: "+ mainResponse.getResponse());

      String encodedHexB64 = this.getBase64FromHEX(mainResponse.getResponse());
      System.out.println("::::::::: encode to base64 : " + encodedHexB64);
      logger.info(String.format(":::::::::: decodeHEX response %s", encodedHexB64));

      String mainEncryptionSting =
              String.format(
                      "-----BEGIN PGP MESSAGE-----\n"
                              + "Version: BCPG v1.54\n\n\n"
                              + "%s"
                              + "\n"
                              + "-----END PGP MESSAGE-----",
                      encodedHexB64);
      logger.info(String.format(":::::::::: pgp file %s", mainEncryptionSting));
      System.out.println("::::::::: pgp file : " + mainEncryptionSting);

      String response1 = pgpUtils.decryptMessage(mainEncryptionSting,privatekey);
      System.out.println("::::::::: decrypted response : " + response1);
      logger.info(String.format(":::::::::: decrypted response  %s", response1));

      result = gs.fromJson(response1, UploadResponse.class);
      System.out.println("After casting: "+ result.toString());

    }catch (Exception ex) {
      logger.info(String.format(":::: ERROR %s",ex.getMessage()));
    }
    return result;
  }






  public BatchStatusResponse batchStatus (BatchStatusRequest batchStatusRequest) throws Exception {

    batchStatusRequest.setProcessorId(processId);
    //  transferRequest.setChannelId(channelId);

    String uri = batchStatus;
    System.out.println("::::::::: RESPONSE FROM Request URL : " + uri);
    logger.info(String.format(":::::::::: Request URL %s", uri));
    BatchStatusResponse result = new BatchStatusResponse();

    // =================  converting encryption to JSON
    // ------------------------------------------------------------
    Gson gs = new Gson();
    String request1 = gs.toJson(batchStatusRequest);
    System.out.println("::::::::: REQUEST  : " + batchStatusRequest);
    logger.info(String.format(":::::::::: REQUEST %s", batchStatusRequest));
    PGPUtils pgpUtils = new PGPUtils();

    request1 = pgpUtils.encryptMessage(request1,publickey);
    System.out.println("PGP: " + request1);
    request1 = Hex.encodeHexString(request1.getBytes());
    System.out.println("Base64 PGP (HEX): " + request1);
    CheckBatch encryptedRequest = new CheckBatch();
    encryptedRequest.setData(request1);
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    try {
      HttpEntity<String> entity = new HttpEntity(encryptedRequest, headers);
      System.out.println("::::::::: ENCRYPTEDREQUEST  : " + request1);
      logger.info(String.format(":::::::::: ENCRYPTEDREQUEST %s", request1));

      ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
      System.out.println(":::::::::  RESPONSE FROM ZENITH : " + response);
      logger.info(String.format(":::::::::: RESPONSE FROM ZENITH %s", response));

      CheckBatchResponse mainResponse = new Gson().fromJson(response.getBody(), CheckBatchResponse.class);
      System.out.println(" response: "+ mainResponse.getResponse());

      String encodedHexB64 = this.getBase64FromHEX(mainResponse.getResponse());
      System.out.println("::::::::: encode to base64 : " + encodedHexB64);
      logger.info(String.format(":::::::::: decodeHEX response %s", encodedHexB64));

      String mainEncryptionSting =
              String.format(
                      "-----BEGIN PGP MESSAGE-----\n"
                              + "Version: BCPG v1.54\n\n\n"
                              + "%s"
                              + "\n"
                              + "-----END PGP MESSAGE-----",
                      encodedHexB64);
      logger.info(String.format(":::::::::: pgp file %s", mainEncryptionSting));
      System.out.println("::::::::: pgp file : " + mainEncryptionSting);

      String response1 = pgpUtils.decryptMessage(mainEncryptionSting,privatekey);
      System.out.println("::::::::: decrypted response : " + response1);
      logger.info(String.format(":::::::::: decrypted response  %s", response1));

      result = gs.fromJson(response1, BatchStatusResponse.class);
      System.out.println("After casting: "+ result.toString());

    }catch (Exception ex) {
      logger.info(String.format(":::: ERROR %s",ex.getMessage()));
    }
    return result;
  }





  private String getBase64FromHEX(String hexadecimal) {
    //String hexadecimal = "6a95b4dd5419f2ffb9f655309c931cb0";
    System.out.println("hexadecimal: " + hexadecimal);
    BigInteger bigint = new BigInteger(hexadecimal, 16);

    StringBuilder sb = new StringBuilder();
    byte[] ba = Base64.encodeInteger(bigint);
    for (byte b : ba) {
      sb.append((char)b);
    }
    String s = sb.toString();
    System.out.println("base64: " + s);
    System.out.println("encoded: " + Base64.isBase64(s));
    return s;
  }

}
