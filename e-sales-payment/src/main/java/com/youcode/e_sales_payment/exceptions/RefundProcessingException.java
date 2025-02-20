package com.youcode.e_sales_payment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class RefundProcessingException extends RuntimeException {
  public RefundProcessingException(String message) {
    super(message);
  }

  public RefundProcessingException(String message, Throwable cause) {
    super(message, cause);
  }
}
