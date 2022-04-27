package com.game;

import static org.junit.Assert.assertEquals;

import com.exception.TenPinBowlingException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class TraditionalScoreImplTest {

  @Test
  public void scoreForNormalGameNoBonus()
      throws TenPinBowlingException.InvalidNumberOfRollsException,
      TenPinBowlingException.InvalidKnockedPinsCountException, TenPinBowlingException.InvalidScoreException {
    Integer expectedScore = 141;
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
    rolls.add(0);

    Roll roll = new Roll("Test Player", rolls);
    Game game = new Game(roll);
    List<Integer> scores = new TraditionalScoreImpl(game).score();

    assertEquals(scores.get(scores.size() - 1), expectedScore);
  }

  @Test
  public void scoreForNormalGameStrikeBonus()
      throws TenPinBowlingException.InvalidNumberOfRollsException,
      TenPinBowlingException.InvalidKnockedPinsCountException, TenPinBowlingException.InvalidScoreException {
    Integer expectedScore = 152;
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
    rolls.add(10);  //strike
    rolls.add(7);
    rolls.add(3);

    Roll roll = new Roll("Test Player", rolls);
    Game game = new Game(roll);
    List<Integer> scores = new TraditionalScoreImpl(game).score();

    assertEquals(scores.get(scores.size() - 1), expectedScore);
  }

  @Test
  public void scoreForNormalGameSpareBonus()
      throws TenPinBowlingException.InvalidNumberOfRollsException,
      TenPinBowlingException.InvalidKnockedPinsCountException, TenPinBowlingException.InvalidScoreException {
    Integer expectedScore = 145;
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
    rolls.add(1);   //spare
    rolls.add(3);

    Roll roll = new Roll("Test Player", rolls);
    Game game = new Game(roll);
    List<Integer> scores = new TraditionalScoreImpl(game).score();

    assertEquals(scores.get(scores.size() - 1), expectedScore);
  }

  @Test
  public void scoreForPerfectGame()
      throws TenPinBowlingException.InvalidNumberOfRollsException,
      TenPinBowlingException.InvalidKnockedPinsCountException, TenPinBowlingException.InvalidScoreException {
    Integer expectedScore = 300;
    List<Integer> rolls = new ArrayList<>();
    rolls.add(10);
    rolls.add(10);
    rolls.add(10);
    rolls.add(10);
    rolls.add(10);
    rolls.add(10);
    rolls.add(10);
    rolls.add(10);
    rolls.add(10);
    rolls.add(10);
    rolls.add(10);
    rolls.add(10);

    Roll roll = new Roll("Test Player", rolls);
    Game game = new Game(roll);
    List<Integer> scores = new TraditionalScoreImpl(game).score();

    assertEquals(scores.get(scores.size() - 1), expectedScore);
  }

  @Test
  public void scoreZeroGame()
      throws TenPinBowlingException.InvalidNumberOfRollsException,
      TenPinBowlingException.InvalidKnockedPinsCountException, TenPinBowlingException.InvalidScoreException {
    Integer expectedScore = 0;
    List<Integer> rolls = new ArrayList<>();
    rolls.add(0);
    rolls.add(0);
    rolls.add(0);
    rolls.add(0);
    rolls.add(0);
    rolls.add(0);
    rolls.add(0);
    rolls.add(0);
    rolls.add(0);
    rolls.add(0);
    rolls.add(0);
    rolls.add(0);
    rolls.add(0);
    rolls.add(0);
    rolls.add(0);
    rolls.add(0);
    rolls.add(0);
    rolls.add(0);
    rolls.add(0);
    rolls.add(0);

    Roll roll = new Roll("Test Player", rolls);
    Game game = new Game(roll);
    List<Integer> scores = new TraditionalScoreImpl(game).score();

    assertEquals(scores.get(scores.size() - 1), expectedScore);
  }
}