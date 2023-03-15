package com.warehouse.service.exceptions;

public class ServiceException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final String message;

  public ServiceException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }

}
