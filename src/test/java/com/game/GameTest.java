package com.game;

import com.exception.TenPinBowlingException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class GameTest {

  @Test(expected = TenPinBowlingException.InvalidNumberOfRollsException.class)
  public void invalidGameForNullRoll() throws TenPinBowlingException.InvalidNumberOfRollsException,
      TenPinBowlingException.InvalidKnockedPinsCountException {
    new Game(new Roll("Test Player", null));
  }

  @Test(expected = TenPinBowlingException.InvalidNumberOfRollsException.class)
  public void invalidGameForEmptyRoll() throws TenPinBowlingException.InvalidNumberOfRollsException,
      TenPinBowlingException.InvalidKnockedPinsCountException {
    new Game(new Roll("Test Player", new ArrayList<>()));
  }

  @Test(expected = TenPinBowlingException.InvalidNumberOfRollsException.class)
  public void invalidGameForNoEnoughRolls() throws TenPinBowlingException.InvalidNumberOfRollsException,
      TenPinBowlingException.InvalidKnockedPinsCountException {
    List<Integer> rolls = new ArrayList<>();
    rolls.add(3);
    rolls.add(7);
    rolls.add(6);
    rolls.add(3);
    rolls.add(10);
    rolls.add(8);
    rolls.add(1);
    rolls.add(10);
    rolls.add(10);
    rolls.add(9);
    rolls.add(0);
    rolls.add(7);
    rolls.add(3);
    rolls.add(4);
    rolls.add(4);
    rolls.add(9);

    Roll roll = new Roll("Test Player", rolls);

    new Game(roll);
  }

  @Test(expected = TenPinBowlingException.InvalidNumberOfRollsException.class)
  public void invalidGameForNoEnoughRollsForStrikeBonus()
      throws TenPinBowlingException.InvalidNumberOfRollsException,
      TenPinBowlingException.InvalidKnockedPinsCountException {
    List<Integer> rolls = new ArrayList<>();
    rolls.add(3);
    rolls.add(7);
    rolls.add(6);
    rolls.add(3);
    rolls.add(10);
    rolls.add(8);
    rolls.add(1);
    rolls.add(10);
    rolls.add(10);
    rolls.add(9);
    rolls.add(0);
    rolls.add(7);
    rolls.add(3);
    rolls.add(4);
    rolls.add(4);
    rolls.add(10);
    rolls.add(9);
    // rolls.add(0);

    Roll roll = new Roll("Test Player", rolls);

    new Game(roll);
  }

  @Test(expected = TenPinBowlingException.InvalidNumberOfRollsException.class)
  public void invalidGameForNoBonusRollsForStrikeInLastFrame()
      throws TenPinBowlingException.InvalidNumberOfRollsException,
      TenPinBowlingException.InvalidKnockedPinsCountException {
    List<Integer> rolls = new ArrayList<>();
    rolls.add(3);
    rolls.add(7);
    rolls.add(6);
    rolls.add(3);
    rolls.add(10);
    rolls.add(8);
    rolls.add(1);
    rolls.add(10);
    rolls.add(10);
    rolls.add(9);
    rolls.add(0);
    rolls.add(7);
    rolls.add(3);
    rolls.add(4);
    rolls.add(4);
    rolls.add(10);

    Roll roll = new Roll("Test Player", rolls);

    new Game(roll);
  }

  @Test(expected = TenPinBowlingException.InvalidNumberOfRollsException.class)
  public void invalidGameForNoEnoughRollsForSpareBonus()
      throws TenPinBowlingException.InvalidNumberOfRollsException,
      TenPinBowlingException.InvalidKnockedPinsCountException {
    List<Integer> rolls = new ArrayList<>();
    rolls.add(3);
    rolls.add(7);
    rolls.add(6);
    rolls.add(3);
    rolls.add(10);
    rolls.add(8);
    rolls.add(1);
    rolls.add(10);
    rolls.add(10);
    rolls.add(9);
    rolls.add(0);
    rolls.add(7);
    rolls.add(3);
    rolls.add(4);
    rolls.add(4);
    rolls.add(1);
    rolls.add(9);
    // rolls.add(0);

    Roll roll = new Roll("Test Player", rolls);

    new Game(roll);
  }

  @Test(expected = TenPinBowlingException.InvalidNumberOfRollsException.class)
  public void invalidGameForTooManyRollsForSpareBonus()
      throws TenPinBowlingException.InvalidNumberOfRollsException,
      TenPinBowlingException.InvalidKnockedPinsCountException {
    List<Integer> rolls = new ArrayList<>();
    rolls.add(3);
    rolls.add(7);
    rolls.add(6);
    rolls.add(3);
    rolls.add(10);
    rolls.add(8);
    rolls.add(1);
    rolls.add(10);
    rolls.add(10);
    rolls.add(9);
    rolls.add(0);
    rolls.add(7);
    rolls.add(3);
    rolls.add(4);
    rolls.add(4);
    rolls.add(1);
    rolls.add(9);
    rolls.add(0);
    rolls.add(0);

    Roll roll = new Roll("Test Player", rolls);

    new Game(roll);
  }

}