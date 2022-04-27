package com;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AppIT {

  @Test
  public void integrationTestForNormalGame() {
    App.main(new String[] {"src/main/resources/scores.txt"});
    assertTrue(true);
  }

  @Test
  public void integrationTestForPerfectGame() {
    App.main(new String[] {"src/main/resources/perfect.txt"});
    assertTrue(true);
  }

  @Test
  public void integrationTestForZeroGame() {
    App.main(new String[] {"src/main/resources/zero.txt"});
    assertTrue(true);
  }
}