package com.game;

import com.exception.TenPinBowlingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Class that implements a Traditional scoring type for 10 pin bowling game.
 */
public class TraditionalScoreImpl implements Score {
  private final Game game;

  public TraditionalScoreImpl(Game game) {
    this.game = game;
  }

  public List<Integer> score() throws TenPinBowlingException.InvalidScoreException {
    Integer frameScore;
    Integer frameScoreAcc = 0;
    int currentFrame = 0;

    List<Integer> scores = new ArrayList<>();
    int limit = this.game.getFrames().size();

    while (currentFrame < limit) {
      if (this.game.getFrames().get(currentFrame).isStrike()) {
        frameScore = getScoreForStrikeFrame(currentFrame);
      } else if (this.game.getFrames().get(currentFrame).isSpare()) {
        frameScore = getScoreForSpareFrame(currentFrame);
      } else {
        frameScore = this.game.getFrames().get(currentFrame).getKnockedPins();
      }
      frameScoreAcc += frameScore;
      if (currentFrame < Game.BONUS_FRAME) {
        scores.add(frameScoreAcc);
      }
      currentFrame++;
    }

    Integer gameScore = scores.get(scores.size() - 1);
    if (gameScore > Game.MAX_SCORE || gameScore < 0) {
      throw new TenPinBowlingException.InvalidScoreException("Invalid score!");
    }

    return scores;
  }

  private Integer getScoreForStrikeFrame(Integer currentFrame) {
    if (this.game.getFrames().get(currentFrame + 1).isStrike()
        && !this.game.getFrames().get(currentFrame + 1).isBonusFrame()) {
      return this.game.getFrames().get(currentFrame).getKnockedPins()
          + this.game.getFrames().get(currentFrame + 1).getKnockedPins()
          + this.game.getFrames().get(currentFrame + 2).getFirstBall();
    } else {
      return this.game.getFrames().get(currentFrame).getKnockedPins()
          + this.game.getFrames().get(currentFrame + 1).getKnockedPins();
    }
  }

  private Integer getScoreForSpareFrame(Integer currentFrame) {
    return this.game.getFrames().get(currentFrame).getKnockedPins()
        + this.game.getFrames().get(currentFrame + 1).getFirstBall();
  }
}