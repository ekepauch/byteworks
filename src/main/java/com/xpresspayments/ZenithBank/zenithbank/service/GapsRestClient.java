//package com.xpresspayments.GTBank.zenithbank.service;//package com.xpresspayments.ZenithBank.zenithbank.service;
//
//import com.xpresspayments.ZenithBank.exception.ProcessingException;
//import com.xpresspayments.ZenithBank.util.SecurityUtil;
//import com.xpresspayments.ZenithBank.zenithbank.configuration.GapsConfiguration;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.*;
//import org.springframework.stereotype.Component;
//import org.springframework.util.Assert;
//import org.springframework.web.client.HttpServerErrorException;
//import org.springframework.web.client.RestTemplate;
//import sun.rmi.runtime.Log;
//
//
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//
//@Component
//public class GapsRestClient {
//
//  private RestTemplate restTemplate = new RestTemplate();
//  private static final Logger LOGGER = LoggerFactory.getLogger(GapsRestClient.class);
//  private static final String SYSTEM_NAME = "GTB-Gaps";
//
//  private GapsConfiguration configuration;
//  private LogRepository logRepository;
//
//  @Autowired
//  public GapsRestClient(
//          GapsConfiguration gapsConfiguration) {
//    Assert.notNull(gapsConfiguration);
//    this.configuration = gapsConfiguration;
//    this.logRepository = logRepository;
//  }
//
//  public String post(String url, String requestXml) {
//    Log log = new Log();
//    log.setSystem(SYSTEM_NAME);
//    log.setRequestBody(requestXml);
//    Log savedLog = logRepository.save(log);
//    LOGGER.info("A" + requestXml);
//    try {
//      HttpHeaders requestHeaders = new HttpHeaders();
//      requestHeaders.setContentType(MediaType.TEXT_XML);
//      HttpEntity<?> requestEntity = new HttpEntity<>(requestXml, requestHeaders);
//      SecurityUtil.byPassSSLCertificate();
//      ResponseEntity<String> responseEntity =
//          restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
//      if (responseEntity.getStatusCode().is2xxSuccessful()) {
//        savedLog.setResponseBody(responseEntity.getBody());
//        savedLog.setResponseCode(responseEntity.getStatusCode().toString());
//        logRepository.save(savedLog);
//        return responseEntity.getBody();
//      }
//      LOGGER.error("Status Code: " + responseEntity.getStatusCode());
//      LOGGER.error(responseEntity.getBody());
//      throw new ProcessingException("Could not complete request");
//    } catch (HttpServerErrorException ex) {
//      log.setStatus(ex.getStatusCode().toString());
//      log.setResponseBody(ex.getResponseBodyAsString());
//      logRepository.save(log);
//      LOGGER.error(ex.getResponseBodyAsString());
//    } catch (Exception e) {
//      LOGGER.error("Request failed", e);
//      throw new ProcessingException(e);
//    }
//    throw new ProcessingException("Request failed");
//  }
//
//}