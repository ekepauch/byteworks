package com.bytework.paystack;


import com.bytework.exceptions.BadRequestException;
import com.bytework.utils.CustomResponseCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@SuppressWarnings("ALL")
@Slf4j
@Service
public class PaymentService {


    @Value("${payment.baseUrl}")
    private String payment;

    @Value("${secrete.key}")
    private String key;




    public PaymentResponse payment (PaymentRequest paymentRequest) throws IOException {

        String uri = payment ;
        log.info("::::::::::  URL %s"+ uri);
        PaymentResponse result = new PaymentResponse();


        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization","Bearer"+" "+ key);
        ObjectMapper objectMapper = new ObjectMapper();

        try{

            HttpEntity<String> entity = new HttpEntity(paymentRequest,headers);
            log.info("::::::::::  REQUEST  %s"+ entity);
            ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
            log.info("::::::::::  RESPONSE   %s"+ response);
            result = objectMapper.readValue(response.getBody(), PaymentResponse.class);

        }catch (HttpClientErrorException ex) {
            throw new BadRequestException(CustomResponseCode.INVALID_REQUEST, "Unable to get response ");
        }
        return result;
    }
}
