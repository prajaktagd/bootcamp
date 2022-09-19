package com.tw.step8.assignment5;

import com.tw.step8.assignment5.exceptions.NegativeCapacityException;

import java.util.Arrays;
import java.util.HashSet;

public class Bag {
  private final int capacity;
  private final HashSet<Ball> balls;
  private int numberOfGreenBalls;
  private int index;

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

  private int ballCountOf(Color color) {
    return (int) balls.stream()
        .filter((ball) -> ball.getColor() == color)
        .count();
  }

  private boolean canGreenBeAdded(Ball ball) {
    return ball.getColor() == Color.GREEN && this.ballCountOf(Color.GREEN) == numberOfGreenBalls;
  }

  private boolean canRedBeAdded(Ball ball) {
    return ball.getColor() == Color.RED && (2 * this.ballCountOf(Color.GREEN) == this.ballCountOf(Color.RED));
  }

  private boolean canYellowBeAdded(Ball ball) {
    double percentOfYellowBeingAdded = (this.ballCountOf(Color.YELLOW) + 1.0) / (balls.size() + 1);
    return ball.getColor() == Color.YELLOW && percentOfYellowBeingAdded >= 0.4;
  }

  public boolean add(Ball ball) {
    if (balls.size() == capacity) {
      return false;
    }

    if (canGreenBeAdded(ball)) {
      return false;
    }

    if (canRedBeAdded(ball)) {
      return false;
    }

    if (canYellowBeAdded(ball)) {
      return false;
    }

    balls.add(ball);
    return true;
  }


}
