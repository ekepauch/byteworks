package com.xpresspayments.ZenithBank.exception;

/**
 * @author Abdussamad
 */
public class ProcessingException extends GapsApiException {

  public ProcessingException(String message) {
    super(message);
  }

  public ProcessingException(Throwable cause) {
    super(cause);
  }
}
