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

  public static int NUMBER_OF_FRAMES = 9;
  public static int BONUS_FRAME = NUMBER_OF_FRAMES + 1;
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
    for (int rollNumber = 0; rollNumber < rolls.size(); rollNumber++) {
      if (rollNumber == rolls.size() - 1) {
        setFrame(rolls.get(rollNumber), 0, false);
      } else {
        if (rolls.get(rollNumber).equals(Frame.STRIKE_NUMBER)) {
          if (this.frames.size() == BONUS_FRAME) {
            setFrame(rolls.get(rollNumber), rolls.get(rollNumber + 1), true);
            rollNumber += 1;
          } else {
            setFrame(rolls.get(rollNumber), 0, false);
          }
        } else {
          setFrame(rolls.get(rollNumber), rolls.get(rollNumber + 1), true);
          rollNumber += 1;
        }
      }
    }

    if (this.frames.size() <= NUMBER_OF_FRAMES) {
      throw new TenPinBowlingException.InvalidNumberOfRollsException(
          "Not enough Rolls to complete a Game");
    }

    if (this.frames.size() == NUMBER_OF_FRAMES + 1) {
      Frame lastFrame = this.frames.get(this.frames.size() - 1);
      if (lastFrame.isStrike() || lastFrame.isSpare()) {
        throw new TenPinBowlingException.InvalidNumberOfRollsException(
            "No Bonus Rolls for last Strike or Spare");
      }
    }
  }

  public final void setFrame(final Integer ball1, final Integer ball2, final boolean doubleValues)
      throws TenPinBowlingException.InvalidKnockedPinsCountException,
      TenPinBowlingException.InvalidNumberOfRollsException {

    Frame previousFrame = null;
    if (!this.frames.isEmpty()) {
      previousFrame = this.frames.get(this.frames.size() - 1);
    }

    Frame newFrame;

    if (this.frames.size() == BONUS_FRAME + 1) {
      throw new TenPinBowlingException.InvalidNumberOfRollsException("Exceeded number of Rolls!");
    }

    if (this.frames.size() == NUMBER_OF_FRAMES + 1 && !Objects.isNull(previousFrame)) {
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
      if (this.frames.size() == NUMBER_OF_FRAMES) {
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
}
