package com.game;

import java.util.List;

public class Roll {
  private final String player;
  private final List<Integer> rolls;

  public Roll(String player, List<Integer> rolls) {
    this.player = player;
    this.rolls = rolls;
  }

  public String getPlayer() {
    return this.player;
  }

  public List<Integer> getRolls() {
    return this.rolls;
  }
}