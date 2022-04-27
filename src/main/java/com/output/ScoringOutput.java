package com.output;

public abstract class ScoringOutput {

  public abstract void display();

  public void displayFramesHeader(Integer numberOfFrames) {
    System.out.print("Frames  \t\t");
    for (int frameNumber = 1; frameNumber <= numberOfFrames; frameNumber++) {
      System.out.print(frameNumber + "\t\t");
    }
    System.out.print("\n");
  }
}