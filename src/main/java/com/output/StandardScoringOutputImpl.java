package com.output;

import com.game.Frame;
import com.game.Game;
import java.util.List;

/**
 * Class that outputs Score and Pinfalls data from Game.
 * - for each player, prints their name on a separate line before printing that player's pinfalls
 * and score.
 * - all values are tab-separated.
 * - the output should calculate if a player scores a strike ('X'), a spare ('/') and allow for
 * extra chances in the
 * tenth frame
 */
public class StandardScoringOutputImpl extends ScoringOutput {

  private static final String NEW_LINE_CHARACTER = "\n";
  private static final String SEPARATOR_CHARACTER = "\t";
  private static final String DOUBLE_SEPARATOR_CHARACTER =
      SEPARATOR_CHARACTER + SEPARATOR_CHARACTER;

  private final Game game;
  private final List<Integer> scores;

  public StandardScoringOutputImpl(Game game, List<Integer> scores) {
    this.game = game;
    this.scores = scores;
  }

  public void display() {
    displayPlayerName();
    displayPinFalls();
    displayScore();
  }

  private void displayPlayerName() {
    System.out.println(this.game.getPlayer());
  }

  private void displayPinFalls() {
    System.out.print("Pinfalls" + DOUBLE_SEPARATOR_CHARACTER);
    String firstBall, secondBall;
    for (Frame frame : this.game.getFrames()) {
      if (frame.isStrike()) {
        if (!frame.isLastFrame()) {
          System.out.print(SEPARATOR_CHARACTER);
        }
        System.out.print("X" + SEPARATOR_CHARACTER);
      } else if (frame.isSpare()) {
        System.out.print(frame.getFirstBall() + SEPARATOR_CHARACTER + "/" + SEPARATOR_CHARACTER);
      } else {
        if (!frame.getBall1Fault()) {
          firstBall = frame.getFirstBall().toString();
        } else {
          firstBall = "F";
        }
        if (!frame.getBall2Fault()) {
          secondBall = frame.getSecondBall().toString();
        } else {
          secondBall = "F";
        }
        if (frame.isBonusFrame()) {
          if (frame.getFirstBall().equals(Frame.STRIKE_NUMBER)) {
            firstBall = "X";
          }
          if (frame.getSecondBall().equals(Frame.STRIKE_NUMBER)) {
            secondBall = "X";
          }
        }
        System.out.print(firstBall + SEPARATOR_CHARACTER + secondBall + SEPARATOR_CHARACTER);
      }
    }
    System.out.print(NEW_LINE_CHARACTER);
  }

  private void displayScore() {
    System.out.print("Score    " + DOUBLE_SEPARATOR_CHARACTER);
    for (Integer score : this.scores) {
      System.out.print(score + DOUBLE_SEPARATOR_CHARACTER);
    }
    System.out.print(NEW_LINE_CHARACTER);
  }
}