package com;

import com.exception.TenPinBowlingException;
import com.game.Game;
import com.game.Roll;
import com.game.TraditionalScoreImpl;
import com.input.FileEvaluator;
import com.input.FileReader;
import com.output.ScoringOutput;
import com.output.StandardScoringOutputImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {

  public static void main(String[] args) {
    try {
      List<String> fileLines = new FileReader().readFile(args[0]);
      List<Roll> rolls = new FileEvaluator(fileLines).evaluate();
      if (rolls.isEmpty()) {
        System.out.println("Empty file!");
        System.exit(0);
      }
      displayResults(createGames(rolls));
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("No file name provided");
      System.exit(0);
    } catch (IOException | TenPinBowlingException.IncorrectFormatException e) {
      System.out.println(e.getMessage());
      System.exit(0);
    } catch (NumberFormatException e) {
      System.out.println("Could not convert Roll value to Integer!");
      System.exit(0);
    }
  }

  private static List<Game> createGames(List<Roll> rolls) {
    List<Game> games = new ArrayList<>();
    for (Roll roll : rolls) {
      try {
        games.add(new Game(roll));
      } catch (TenPinBowlingException.InvalidKnockedPinsCountException
               | TenPinBowlingException.InvalidNumberOfRollsException e) {
        System.out.println("Could not create Game for player : " + roll.getPlayer());
        System.out.println(e.getMessage());
      }
    }

    if (games.isEmpty()) {
      System.out.println("No valid Game to show!");
      System.exit(0);
    }

    return games;
  }

  private static void displayResults(List<Game> games) {
    for (Game game : games) {
      try {
        ScoringOutput scoringOutput =
            new StandardScoringOutputImpl(game,
                new TraditionalScoreImpl(game).score());
        scoringOutput.displayFramesHeader(10);
        scoringOutput.display();
      } catch (TenPinBowlingException.InvalidScoreException e) {
        System.out.println("Invalid score for player : " + game.getPlayer());
      }
    }
  }
}
