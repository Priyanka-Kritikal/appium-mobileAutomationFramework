package com.automate.customexceptions;

@SuppressWarnings("serial")
public class DriverInitializationException extends FrameworkException {
  public DriverInitializationException(String message) {
    super(message);
  }

  public DriverInitializationException(String message, Throwable t) {
    super(message, t);
  }
}
