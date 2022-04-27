package com.exception;

public class TenPinBowlingException {

  public static class InvalidScoreException extends Exception {
    public InvalidScoreException(String errorMessage) {
      super(errorMessage);
    }
  }

  public static class InvalidNumberOfRollsException extends Exception {
    public InvalidNumberOfRollsException(String errorMessage) {
      super(errorMessage);
    }
  }

  public static class InvalidKnockedPinsCountException extends Exception {
    public InvalidKnockedPinsCountException(String errorMessage) {
      super(errorMessage);
    }
  }

  public static class IncorrectFormatException extends Exception {
    public IncorrectFormatException(String errorMessage) {
      super(errorMessage);
    }
  }
}
