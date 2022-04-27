package com.game;

import com.exception.TenPinBowlingException;
import java.util.List;

public interface Score {
  List<Integer> score() throws TenPinBowlingException.InvalidScoreException;
}