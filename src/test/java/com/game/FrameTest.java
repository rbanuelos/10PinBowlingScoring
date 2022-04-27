package com.game;

import static org.junit.Assert.assertTrue;

import com.exception.TenPinBowlingException;
import org.junit.Test;

public class FrameTest {

  @Test(expected = TenPinBowlingException.InvalidKnockedPinsCountException.class)
  public void invalidMoreThanTenFrame() throws TenPinBowlingException.InvalidKnockedPinsCountException {
    new Frame(10, 1, false);
    new Frame(1, 10, false);
  }

  @Test(expected = TenPinBowlingException.InvalidKnockedPinsCountException.class)
  public void invalidLessThanZeroFrame() throws TenPinBowlingException.InvalidKnockedPinsCountException {
    new Frame(-2, -1, false);
    new Frame(0, -2, false);
  }

  @Test(expected = TenPinBowlingException.InvalidKnockedPinsCountException.class)
  public void invalidNegativeBallFrame() throws TenPinBowlingException.InvalidKnockedPinsCountException {
    new Frame(-2, -1, false);
    new Frame(0, -2, false);
    new Frame(7, -2, false);
    new Frame(-2, 12, false);
  }

  @Test
  public void isSpare() throws TenPinBowlingException.InvalidKnockedPinsCountException {

    Frame f1, f2;
    f1 = new Frame(1, 9, false);
    f2 = new Frame(3, 7, false);

    assertTrue(f1.isSpare() && f2.isSpare());
  }

  @Test
  public void isNotSpare() throws TenPinBowlingException.InvalidKnockedPinsCountException {

    Frame f1, f2;
    f1 = new Frame(10, 0, false);
    f2 = new Frame(4, 5, false);

    assertTrue(!f1.isSpare() && !f2.isSpare());
  }

  @Test
  public void isStrike() throws TenPinBowlingException.InvalidKnockedPinsCountException {
    Frame f1, f2;
    f1 = new Frame(10, 0, false);
    f2 = new Frame(0, 10, false);

    assertTrue(f1.isStrike() && f2.isStrike());
  }

  @Test
  public void isNotStrike() throws TenPinBowlingException.InvalidKnockedPinsCountException {
    Frame f1, f2;
    f1 = new Frame(1, 0, false);
    f2 = new Frame(3, 7, false);

    assertTrue(!f1.isStrike() && !f2.isStrike());
  }
}