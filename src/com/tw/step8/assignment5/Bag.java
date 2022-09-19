package com.tw.step8.assignment5;

import com.tw.step8.assignment5.exceptions.NegativeCapacityException;

import java.util.HashSet;

public class Bag {
  private final int capacity;
  private final HashSet<Ball> balls;
  private final int numberOfGreenBalls;

  private Bag(int capacity, int numberOfGreenBalls) {
    this.capacity = capacity;
    this.numberOfGreenBalls = numberOfGreenBalls;
    this.balls = new HashSet<>(capacity);
  }

  public static Bag createBag(int capacity, int numberOfGreenBalls) throws NegativeCapacityException {
    if (capacity < 0) {
      throw new NegativeCapacityException(capacity);
    }
    return new Bag(capacity, numberOfGreenBalls);
  }

  private int occupancyOf(Color color) {
    return (int) balls.stream()
        .filter((ball) -> ball.getColor() == color)
        .count();
  }

  private boolean isNotAllowed(Color color) {
    if (color == Color.GREEN) {
      return this.occupancyOf(Color.GREEN) == numberOfGreenBalls;
    }

    if (color == Color.RED) {
      return  this.occupancyOf(Color.RED) == (2 * this.occupancyOf(Color.GREEN));
    }

    if (color == Color.YELLOW) {
      double percentOfYellowBeingAdded = (this.occupancyOf(Color.YELLOW) + 1.0) / (balls.size() + 1);
      return percentOfYellowBeingAdded >= 0.4;
    }

    if (color == Color.BLACK) {
      return this.occupancyOf(Color.BLUE) >= 1;
    }

    if (color == Color.BLUE) {
      return this.occupancyOf(Color.BLACK) >= 1;
    }

    return false;
  }

  public boolean add(Ball ball) {
    if (balls.size() == capacity) {
      return false;
    }

    if(isNotAllowed(ball.getColor())) {
      return false;
    }

    balls.add(ball);
    return true;
  }
}
