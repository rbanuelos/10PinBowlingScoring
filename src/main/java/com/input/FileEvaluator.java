package com.input;

import com.exception.TenPinBowlingException;
import com.game.Roll;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class that evaluates File content line by line to extract Rolls for every player.
 * It also detects if there is a format issue
 */
public class FileEvaluator {
  private final List<String> lines;

  public FileEvaluator(List<String> lines) {
    this.lines = lines;
  }

  public List<Roll> evaluate() throws NumberFormatException,
      TenPinBowlingException.IncorrectFormatException {
    List<AbstractMap.SimpleEntry<String, Integer>> rollEntries = new ArrayList<>();
    for (String line : lines) {
      if (!isBlankString(line)) {
        rollEntries.add(getPlayerRollValuePair(line));
      }
    }

    return getRollValuesPerPlayer(rollEntries);
  }

  public AbstractMap.SimpleEntry<String, Integer> getPlayerRollValuePair(String line)
      throws NumberFormatException, TenPinBowlingException.IncorrectFormatException {
    String[] splitLine = line.split("\t");

    if (splitLine.length != 2) {
      throw new TenPinBowlingException.IncorrectFormatException(
          "File must only contain 2 columns separated by tab!");
    }

    String rollPlayer = splitLine[0];
    String rollValue = splitLine[1];

    if (rollValue.equals("F")) {
      return new AbstractMap.SimpleEntry<>(rollPlayer, -1);
    }

    int value = Integer.parseInt(rollValue);
    if (value < 0 || value > 10) {
      throw new TenPinBowlingException.IncorrectFormatException(
          "Invalid value for roll. Should be between 0 and 10 or F");
    }

    return new AbstractMap.SimpleEntry<>(rollPlayer, value);
  }

  private List<Roll> getRollValuesPerPlayer(
      List<AbstractMap.SimpleEntry<String, Integer>> rollEntries) {
    List<Roll> rolls = new ArrayList<>();
    for (AbstractMap.SimpleEntry<String, Integer> entry : rollEntries) {
      Roll roll = findRollForPlayer(entry.getKey(), rolls);
      if (roll == null) {
        rolls.add(
            new Roll(entry.getKey(), new ArrayList<>(Collections.singletonList(entry.getValue()))));
      } else {
        roll.getRolls().add(entry.getValue());
      }
    }

    return rolls;
  }

  private Roll findRollForPlayer(String player, List<Roll> rolls) {
    return rolls.stream()
        .filter(roll -> player.equals(roll.getPlayer()))
        .findAny()
        .orElse(null);
  }

  private boolean isBlankString(String string) {
    return string == null || string.trim().isEmpty();
  }
}