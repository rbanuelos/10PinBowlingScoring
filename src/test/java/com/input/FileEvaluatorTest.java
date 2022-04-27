package com.input;

import static org.junit.Assert.assertEquals;

import com.exception.TenPinBowlingException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class FileEvaluatorTest {

  @Test(expected = TenPinBowlingException.IncorrectFormatException.class)
  public void invalidStringLineNoTab() throws TenPinBowlingException.IncorrectFormatException {
    List<String> lines = new ArrayList<>();
    lines.add("Invalid file line...");
    FileEvaluator fileEvaluator = new FileEvaluator(lines);
    fileEvaluator.getPlayerRollValuePair(lines.get(0));
  }

  @Test(expected = TenPinBowlingException.IncorrectFormatException.class)
  public void invalidStringLineBeginWithTab() throws TenPinBowlingException.IncorrectFormatException {
    List<String> lines = new ArrayList<>();
    lines.add("\tplayer1\t2");
    FileEvaluator fileEvaluator = new FileEvaluator(lines);
    fileEvaluator.getPlayerRollValuePair(lines.get(0));
  }

  @Test(expected = TenPinBowlingException.IncorrectFormatException.class)
  public void invalidStringLineThreeValues() throws TenPinBowlingException.IncorrectFormatException {
    List<String> lines = new ArrayList<>();
    lines.add("Invalid\t1\t2");
    FileEvaluator fileEvaluator = new FileEvaluator(lines);
    fileEvaluator.getPlayerRollValuePair(lines.get(0));
  }

  @Test(expected = NumberFormatException.class)
  public void invalidStringLineNoInteger() throws TenPinBowlingException.IncorrectFormatException {
    List<String> lines = new ArrayList<>();
    lines.add("Invalidfile\t2.");
    FileEvaluator fileEvaluator = new FileEvaluator(lines);
    fileEvaluator.getPlayerRollValuePair(lines.get(0));
  }

  @Test
  public void validStringLineForFaultRoll() throws TenPinBowlingException.IncorrectFormatException {
    Integer faultRoll = -1;
    List<String> lines = new ArrayList<>();
    lines.add("validfile\tF");
    FileEvaluator fileEvaluator = new FileEvaluator(lines);

    fileEvaluator.getPlayerRollValuePair(lines.get(0));

    assertEquals(fileEvaluator.getPlayerRollValuePair(lines.get(0)).getKey(), "validfile");
    assertEquals(fileEvaluator.getPlayerRollValuePair(lines.get(0)).getValue(), faultRoll);
  }

  @Test
  public void validStringLineForRoll() throws TenPinBowlingException.IncorrectFormatException {
    Integer roll = 10;
    List<String> lines = new ArrayList<>();
    lines.add("John M. Deacon\t10");
    FileEvaluator fileEvaluator = new FileEvaluator(lines);

    fileEvaluator.getPlayerRollValuePair(lines.get(0));

    assertEquals(fileEvaluator.getPlayerRollValuePair(lines.get(0)).getKey(), "John M. Deacon");
    assertEquals(fileEvaluator.getPlayerRollValuePair(lines.get(0)).getValue(), roll);
  }
}