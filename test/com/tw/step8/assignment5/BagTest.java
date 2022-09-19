package com.tw.step8.assignment5;

import com.tw.step8.assignment5.exceptions.NegativeCapacityException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BagTest {
  @Test
  void shouldThrowNegativeCapacityException() {
    assertThrows(NegativeCapacityException.class, () -> Bag.createBag(-1, 0));
  }

  @Test
  void shouldAddBallOfSpecifiedColorInTheBag() throws NegativeCapacityException {
    Bag bag = Bag.createBag(1, 1);
    assertTrue(bag.add(new Ball(Color.GREEN)));
  }

  @Test
  void shouldNotAddBallIfMaxCapacityIsReached() throws NegativeCapacityException {
    Bag bag = Bag.createBag(1, 1);
    bag.add(new Ball(Color.GREEN));
    assertFalse(bag.add(new Ball(Color.GREEN)));
  }

  @Test
  void shouldNotAddBallOfSpecifiedColorIfMaxBallsOfThatColorAlreadyPresent() throws NegativeCapacityException {
    Bag bag = Bag.createBag(2, 1);
    bag.add(new Ball(Color.GREEN));

    assertFalse(bag.add(new Ball(Color.GREEN)));
  }

  @Test
  void shouldNotAddRedBallIfItIsMoreThanDoubleOfGreen() throws NegativeCapacityException {
    Bag bag = Bag.createBag(5, 1);
    bag.add(new Ball(Color.GREEN));
    bag.add(new Ball(Color.RED));
    bag.add(new Ball(Color.RED));

    assertFalse(bag.add(new Ball(Color.RED)));
  }

  @Test
  void shouldNotAddYellowBallIfItIsMoreThan40PercentOfCurrentSize() throws NegativeCapacityException {
    Bag bag = Bag.createBag(4, 1);
    bag.add(new Ball(Color.GREEN));

    assertFalse(bag.add(new Ball(Color.YELLOW)));
  }

  @Test
  void shouldAddYellowBallIfItIsLessThan40PercentOfCurrentSize() throws NegativeCapacityException {
    Bag bag = Bag.createBag(4, 1);
    bag.add(new Ball(Color.GREEN));
    bag.add(new Ball(Color.RED));

    assertTrue(bag.add(new Ball(Color.YELLOW)));
  }
}