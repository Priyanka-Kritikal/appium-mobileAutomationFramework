package com.automate.customexceptions;

@SuppressWarnings("serial")
public class FrameworkException extends RuntimeException {

  public FrameworkException(String message) {
    super(message);
  }

  public FrameworkException(String message, Throwable t) {
    super(message, t);
  }
}
