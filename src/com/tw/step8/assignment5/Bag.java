package com.tw.step8.assignment5;

public class Bag {
  private final int capacity;
  private final Ball[] balls;

  public Bag(int capacity) {
    this.capacity = capacity;
    this.balls = new Ball[capacity];
  }
}
