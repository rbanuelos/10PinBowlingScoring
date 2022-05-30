package com.game;

import com.exception.TenPinBowlingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class that represent a Game.
 * Consist of a Player and a List of Frames.
 * Check if game is valid
 * - number of frames
 * - valid frame
 * - valid bonus frame (if 10th frame was a strike or spare)
 */
public class Game {

  public static int NUMBER_OF_FRAMES = 10;
  public static int MAX_SCORE = 300;

  private final String player;
  private final ArrayList<Frame> frames;

  public Game(final Roll roll) throws TenPinBowlingException.InvalidKnockedPinsCountException,
      TenPinBowlingException.InvalidNumberOfRollsException {
    this.player = roll.getPlayer();
    this.frames = new ArrayList<>();
    initGame(roll.getRolls());
  }

  /**
   * Initialize a Game from Player rolls. Creates Game Frames from list of player
   * rolls
   *
   * @param rolls Player rolls
   * @throws TenPinBowlingException.InvalidKnockedPinsCountException if rolls values are not
   *                                                                 correct
   * @throws TenPinBowlingException.InvalidNumberOfRollsException    if number of frames is not
   *                                                                 complete
   */
  private void initGame(final List<Integer> rolls)
      throws TenPinBowlingException.InvalidKnockedPinsCountException,
      TenPinBowlingException.InvalidNumberOfRollsException {
    if (rolls == null || rolls.isEmpty()) {
      throw new TenPinBowlingException.InvalidNumberOfRollsException("Roll list is empty!");
    }
    int rollIndex = 0;
    while (rollIndex < rolls.size()) {
      if (rollIndex == rolls.size() - 1) {
        addFrame(rolls.get(rollIndex), 0, false);
      } else {
        if (isStrike(rolls.get(rollIndex))) {
          if (isMaxFrameNumberReached()) {
            addFrame(rolls.get(rollIndex), rolls.get(rollIndex + 1), true);
            rollIndex += 1;
          } else {
            addFrame(rolls.get(rollIndex), 0, false);
          }
        } else {
          addFrame(rolls.get(rollIndex), rolls.get(rollIndex + 1), true);
          rollIndex += 1;
        }
      }
      rollIndex++;
    }

    if (this.frames.size() < NUMBER_OF_FRAMES) {
      throw new TenPinBowlingException.InvalidNumberOfRollsException(
          "Not enough Rolls to complete a Game");
    }

    if (this.frames.size() == NUMBER_OF_FRAMES) {
      if (getLastFrame().isStrike() || getLastFrame().isSpare()) {
        throw new TenPinBowlingException.InvalidNumberOfRollsException(
            "No Bonus Rolls for last Strike or Spare");
      }
    }
  }

  public final void addFrame(final Integer ball1, final Integer ball2, final boolean doubleValues)
      throws TenPinBowlingException.InvalidKnockedPinsCountException,
      TenPinBowlingException.InvalidNumberOfRollsException {

    Frame previousFrame = null;
    if (!this.frames.isEmpty()) {
      previousFrame = this.frames.get(this.frames.size() - 1);
    }

    Frame newFrame;

    if (this.frames.size() == NUMBER_OF_FRAMES + 1) {
      throw new TenPinBowlingException.InvalidNumberOfRollsException("Exceeded number of Rolls!");
    }

    if (isMaxFrameNumberReached() && !Objects.isNull(previousFrame)) {
      if (!(previousFrame.isStrike() && doubleValues) && !previousFrame.isSpare()) {
        throw new TenPinBowlingException.InvalidNumberOfRollsException(
            "Not enough Rolls. Missing 1 bonus roll");
      }
      if (!(previousFrame.isSpare() && !doubleValues) && !previousFrame.isStrike()) {
        throw new TenPinBowlingException.InvalidNumberOfRollsException(
            "Exceeded number of Rolls!. 1 extra Bonus roll");
      }
      newFrame = new Frame(ball1, ball2, true);
    } else {
      newFrame = new Frame(ball1, ball2, false);
      if (this.frames.size() + 1 == NUMBER_OF_FRAMES) {
        if (!newFrame.isStrike() && !doubleValues) {
          throw new TenPinBowlingException.InvalidNumberOfRollsException(
              "Not enough Rolls to complete a Game");
        }
        newFrame.setIsLastFrame(true);
      }
    }

    this.frames.add(newFrame);
  }

  /**
   * Get Player.
   *
   * @return String player name
   */
  public final String getPlayer() {
    return this.player;
  }

  /**
   * Get frames.
   *
   * @return List<Frame> list of game's frames
   */
  public final ArrayList<Frame> getFrames() {
    return this.frames;
  }

  private boolean isStrike(Integer value) {
    return value.equals(Frame.STRIKE_NUMBER);
  }

  private boolean isMaxFrameNumberReached() {
    return this.frames.size() == NUMBER_OF_FRAMES;
  }

  private Frame getLastFrame() {
    return this.frames.get(frames.size() - 1);
  }
}
