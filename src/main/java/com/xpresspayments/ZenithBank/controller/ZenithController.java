package com.xpresspayments.ZenithBank.controller;


import com.xpresspayments.ZenithBank.exceptions.BadRequestException;
import com.xpresspayments.ZenithBank.model.XPDirect.Model.request.*;
import com.xpresspayments.ZenithBank.model.XPDirect.Model.response.*;
import com.xpresspayments.ZenithBank.model.request.*;
import com.xpresspayments.ZenithBank.model.response.*;
import com.xpresspayments.ZenithBank.util.CustomResponseCode;
import com.xpresspayments.ZenithBank.zenithbank.constant.GapsStatus;
import com.xpresspayments.ZenithBank.zenithbank.service.ZenithServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class ZenithController {


    @Autowired
    ZenithServices zenithServices;

    @RequestMapping(path = "/otp", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiTokenResponse Token (@RequestBody ApiTokenRequest request) throws Exception {

        if(request.getAccountNumber()== null)
            throw new BadRequestException(CustomResponseCode.INVALID_REQUEST, "account number cannot be empty");
        if (request.getAccountNumber().length() < 10)
            throw new BadRequestException(CustomResponseCode.INVALID_REQUEST, "Invalid Account Number Length");
        ApiTokenResponse response= zenithServices.TokenRequest(request);
        return response;
    }



    @RequestMapping(path = "/accountbalances", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiBalanceRetrivalResponse Balance (@RequestBody ApiBalanceRetrivalRequest request) throws Exception {

        if(request.getAccountNumber()== null)
            throw new BadRequestException(CustomResponseCode.INVALID_REQUEST, "account number cannot be empty");
        if (request.getAccountNumber().length() < 10)
            throw new BadRequestException(CustomResponseCode.INVALID_REQUEST, "Invalid Account Number Length");
        ApiBalanceRetrivalResponse response= zenithServices.Balance(request);
        return response;
    }


    @RequestMapping(path = "/payment", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiOutFlowResponse OutFlow (@RequestBody ApiOutFlowRequest apiOutFlowRequest) throws Exception {
        if(apiOutFlowRequest.getCreditAccountNumber()== null)
            throw new BadRequestException(CustomResponseCode.INVALID_REQUEST, "credit account cannot be empty");
        if (apiOutFlowRequest.getCreditAccountNumber().length() < 10)
            throw new BadRequestException(CustomResponseCode.INVALID_REQUEST, "Invalid Account Number Length");
        ApiOutFlowResponse response= zenithServices.outflow(apiOutFlowRequest);
        return response;
    }


    @RequestMapping(path = "/inflow", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public InflowResponse InFlow (@RequestBody InflowRequest inflowRequest) throws Exception {
        InflowResponse response= zenithServices.InFlow(inflowRequest);
        return response;
    }



    @RequestMapping(path = "/nameenquiry", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ZenithBankApiResponse> validateAccount (@RequestBody ApiAccountValidationRequest request) throws Exception {
        ApiAccountValidationResponse response= zenithServices.nameRenqiry(request);
        Map<String, Object> data = new HashMap<>(1);
        data.put("accounts", response);
        SuccessResponse successResponse = SuccessResponse
                .builder()
                .data(data)
                .build();
        return new ResponseEntity<>(successResponse, HttpStatus.OK);

    }

    @RequestMapping(path = "/requery", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public TransactionStatusResponse Requery (@RequestBody TransactionStatusRequest request) throws Exception {
        TransactionStatusResponse response= zenithServices.Requery(request);
        return response;
    }


    @RequestMapping(path = "/retailpayments", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public TransferResponse retailPayment (@RequestBody TransferRequest request) throws Exception {
        TransferResponse response= zenithServices.Transfer(request);
        return response;
    }



    @RequestMapping(path = "/batch", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BatchResponse createBatch (@RequestBody BatchRequest request) throws Exception {
        BatchResponse response= zenithServices.CreateBatch(request);
        return response;
    }


    @RequestMapping(path = "/upload", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UploadResponse uploads (@RequestBody UploadRequest request) throws Exception {
        UploadResponse response= zenithServices.uploadBatch(request);
        return response;
    }


    @RequestMapping(path = "/batchStatus", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BatchStatusResponse uploads (@RequestBody BatchStatusRequest request) throws Exception {
        BatchStatusResponse response= zenithServices.batchStatus(request);
        return response;
    }
}
