package com.game;


import com.exception.TenPinBowlingException;

/**
 * Class that represent a Frame.
 * It has information about Bowling balls 1 and 2. In addition, has information
 * about if it's the last frame and/or if it's a Bonus Frame and/or if it's a
 * fault
 */
public class Frame {

  public static Integer STRIKE_NUMBER = 10;

  private final Integer ball1;
  private final Integer ball2;
  private final boolean ball1Fault;
  private final boolean ball2Fault;
  private final boolean isBonusFrame;
  private boolean isLastFrame;

  public Frame(Integer ball1, Integer ball2, boolean isBonusFrame) throws
      TenPinBowlingException.InvalidKnockedPinsCountException {

    this.isBonusFrame = isBonusFrame;

    if (ball1 == -1) {
      this.ball1 = 0;
      this.ball1Fault = true;
    } else {
      this.ball1 = ball1;
      this.ball1Fault = false;
    }

    if (ball2 == -1) {
      this.ball2 = 0;
      this.ball2Fault = true;
    } else {
      this.ball2 = ball2;
      this.ball2Fault = false;
    }

    if (this.isBonusFrame) {
      if (this.ball1 > STRIKE_NUMBER || this.ball2 > STRIKE_NUMBER) {
        throw new TenPinBowlingException.InvalidKnockedPinsCountException(
            "Invalid values for bonus balls 1 and 2. Sum should be less than " + STRIKE_NUMBER * 2);
      }
    } else {
      if (this.ball1 + this.ball2 > STRIKE_NUMBER) {
        throw new TenPinBowlingException.InvalidKnockedPinsCountException(
            "Invalid values for balls 1 and 2. Sum should be less than " + STRIKE_NUMBER);
      }
    }

    if (this.ball1 + this.ball2 < 0) {
      throw new TenPinBowlingException.InvalidKnockedPinsCountException(
          "Invalid values for balls 1 and 2. Sum should be greater than zero");
    }

    this.isLastFrame = false;
  }

  public Integer getKnockedPins() {
    return ball1 + ball2;
  }

  public boolean isStrike() {
    return (ball1.equals(STRIKE_NUMBER) || ball2.equals(STRIKE_NUMBER)) && !isBonusFrame;
  }

  public boolean isSpare() {
    return ball1 < STRIKE_NUMBER && ball2 < STRIKE_NUMBER && (ball1 + ball2 == STRIKE_NUMBER)
        && !isBonusFrame;
  }

  public void setIsLastFrame(boolean isLastFrame) {
    this.isLastFrame = isLastFrame;
  }

  public boolean isLastFrame() {
    return this.isLastFrame;
  }

  public boolean isBonusFrame() {
    return this.isBonusFrame;
  }

  public Integer getFirstBall() {
    return ball1;
  }

  public Integer getSecondBall() {
    return ball2;
  }

  public boolean getBall1Fault() {
    return this.ball1Fault;
  }

  public boolean getBall2Fault() {
    return this.ball2Fault;
  }

}