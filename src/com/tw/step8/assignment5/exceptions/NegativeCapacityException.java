package com.tw.step8.assignment5.exceptions;

public class NegativeCapacityException extends Throwable {
  private final int capacity;

  public NegativeCapacityException(int capacity) {
    super();
    this.capacity = capacity;
  }

  @Override
  public String getMessage() {
    return String.format("Provided negative capacity %d", capacity);
  }
}
