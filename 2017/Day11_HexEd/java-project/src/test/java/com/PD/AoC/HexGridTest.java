package com.PD.AoC;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class HexGridTest {
  @Test
  public void traceOneStep() {
    try {
      String steps = "ne";
      Coordinate trace = HexGrid.trace(steps);
      assertTrue(trace.equals(new Coordinate(1, 1)));
      assertEquals(HexGrid.calculateStepsAway(trace), 1);
      assertEquals(HexGrid.maxDistanceDuringTrace(steps), 1);
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  @Test(expected = BadDirectionException.class)
  public void testBadDirection() throws BadDirectionException {
    try {
      HexGrid.trace("b");
    } catch (BadDirectionException e) {
      throw e;
    }
  }

  @Test
  public void traceEachDirection() {
    try {
      String steps = "n,s,ne,sw,nw,se";
      Coordinate trace = HexGrid.trace(steps);
      assertTrue(trace.equals(new Coordinate(0, 0)));
      assertEquals(HexGrid.calculateStepsAway(trace), 0);
      assertEquals(HexGrid.maxDistanceDuringTrace(steps), 1);
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void example1() {
    try {
      String steps = "ne,ne,ne";
      Coordinate trace = HexGrid.trace(steps);
      assertTrue(trace.equals(new Coordinate(3, 3)));
      assertEquals(HexGrid.calculateStepsAway(trace), 3);
      assertEquals(HexGrid.maxDistanceDuringTrace(steps), 3);
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void example2() {
    try {
      String steps = "ne,ne,sw,sw";
      Coordinate trace = HexGrid.trace(steps);
      assertTrue(trace.equals(new Coordinate(0, 0)));
      assertEquals(HexGrid.calculateStepsAway(trace), 0);
      assertEquals(HexGrid.maxDistanceDuringTrace(steps), 2);
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void example3() {
    try {
      String steps = "ne,ne,s,s";
      Coordinate trace = HexGrid.trace(steps);
      assertTrue(trace.equals(new Coordinate(2, -2)));
      assertEquals(HexGrid.calculateStepsAway(trace), 2);
      assertEquals(HexGrid.maxDistanceDuringTrace(steps), 2);
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void example4() {
    try {
      String steps = "se,sw,se,sw,sw";
      Coordinate trace = HexGrid.trace(steps);
      assertTrue(trace.equals(new Coordinate(-1, -5)));
      assertEquals(HexGrid.calculateStepsAway(trace), 3);
      assertEquals(HexGrid.maxDistanceDuringTrace(steps), 3);
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void puzzlePart1() {
    try {
      String steps = getLineFromFile("src/test/java/com/PD/AoC/puzzle.txt");
      Coordinate trace = HexGrid.trace(steps);
      int stepsAway = HexGrid.calculateStepsAway(trace);
      assertEquals(stepsAway, 810);
      assertEquals(HexGrid.maxDistanceDuringTrace(steps), 3);
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }

  private String getLineFromFile(String fileName) {
    try {
      String line;
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      line = bufferedReader.readLine();
      bufferedReader.close();
      return line;
    } catch(IOException ex) {
      System.out.println(ex);
      return null;
    }
  }
}
