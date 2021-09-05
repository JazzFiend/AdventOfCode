package com.PD.AoC;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PipeGrouperTest {
  @Test
  public void oneGroup() {
    String[] connectionList = new String[2];
    connectionList[0] = "0 <-> 1";
    connectionList[1] = "1 <-> 2";
    Map<String, List<String>> pipes = ConnectionMapGenerator.generateConnectionMap(connectionList);
    assertEquals(1, PipeGrouper.countDistinctGroups(pipes));
  }

  @Test
  public void acceptanceTest() {
    try {
      String[] connectionList = getTextFromFile("src/test/java/com/PD/AoC/acceptanceTest.txt");
      Map<String, List<String>> pipes = ConnectionMapGenerator.generateConnectionMap(connectionList);
      assertEquals(2, PipeGrouper.countDistinctGroups(pipes));
    } catch(IOException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void PuzzleTwo() {
    try {
      String[] connectionList = getTextFromFile("src/test/java/com/PD/AoC/puzzleInput.txt");
      Map<String, List<String>> pipes = ConnectionMapGenerator.generateConnectionMap(connectionList);
      assertEquals(179, PipeGrouper.countDistinctGroups(pipes));
    } catch(IOException e) {
      fail(e.getMessage());
    }
  }

  private String[] getTextFromFile(String fileName) throws IOException {
    FileReader fileReader = new FileReader(fileName);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    String[] linesOfText = (String[])bufferedReader.lines().toArray(String[]::new);
    bufferedReader.close();
    return linesOfText;
  }
}
