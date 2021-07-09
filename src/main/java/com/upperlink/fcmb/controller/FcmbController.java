package com.upperlink.fcmb.controller;


import com.upperlink.fcmb.domain.request.BalanceRequest;
import com.upperlink.fcmb.domain.response.BalanceEnquiryResponse;
import com.upperlink.fcmb.domain.response.NameEnquiryResponse;
import com.upperlink.fcmb.domain.response.PaymentResponse;
import com.upperlink.fcmb.producerModel.ProducerNameEnquiry;
import com.upperlink.fcmb.producerModel.ProducerTransactionProcessing;
import com.upperlink.fcmb.services.FcmbServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FcmbController {


    @Autowired
    private FcmbServices fcmbServices;



    @PostMapping("/nameenquiry")
    public NameEnquiryResponse nameEnquiry(@RequestBody ProducerNameEnquiry producerNameEnquiry) throws Exception {
        NameEnquiryResponse response= fcmbServices.nameEnquiry(producerNameEnquiry);
        return response;
    }



    @RequestMapping(path = "/payment", method = RequestMethod.POST,consumes = MediaType
            .APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PaymentResponse payment(@RequestBody ProducerTransactionProcessing paymentRequest) throws Exception {
        PaymentResponse response= fcmbServices.payments(paymentRequest);
        return response;
    }

    @RequestMapping(path = "/balance", method = RequestMethod.POST,consumes = MediaType
            .APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BalanceEnquiryResponse balanceEnquiry(@RequestBody BalanceRequest balanceRequest) throws Exception {
        BalanceEnquiryResponse response= fcmbServices.balanceEnquiry(balanceRequest);
        return response;
    }

}
