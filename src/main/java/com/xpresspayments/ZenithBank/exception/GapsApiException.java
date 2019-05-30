package com.xpresspayments.ZenithBank.exception;

/**
 * @author Abdussamad
 */
public class GapsApiException extends RuntimeException {

  public GapsApiException(String message) {
    super(message);
  }

  public GapsApiException(Throwable cause) {
    super(cause);
  }
}
