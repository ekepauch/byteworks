package com.xpresspayments.ZenithBank.controller;



import com.xpresspayments.ZenithBank.exceptions.BadRequestException;
import com.xpresspayments.ZenithBank.model.constant.ErrorCode;
import com.xpresspayments.ZenithBank.model.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author abdussamad
 */
@ControllerAdvice
public class ErrorHandler {

  Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ErrorResponse> handleBadRequest(
      HttpServletRequest request, BadRequestException e) {
    ErrorResponse response = ErrorResponse.builder()
        .error(ErrorCode.INPUT)
        .message(e.getMessage())
        .build();
    LOGGER.error("Error: " + e.getMessage(), e);
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleInternalError
      (HttpServletRequest request, Exception e) {
    ErrorResponse response = ErrorResponse.builder()
        .error(ErrorCode.PROCESSING)
        .message(e.getMessage())
        .build();
    LOGGER.error("Error: " + e.getMessage(), e);
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

