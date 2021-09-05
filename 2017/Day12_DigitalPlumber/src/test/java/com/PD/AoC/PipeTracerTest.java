package com.PD.AoC;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.junit.Test;

public class PipeTracerTest {
  @Test
  public void emptyPipeMap() {
    assertEquals(0, PipeTracer.extractConnectionsInGroup(new HashMap<>(), "0").size());
  }

  @Test
  public void onePipe() {
    String[] connectionList = new String[1];
    connectionList[0] = "0 <-> 1";
    Map<String, List<String>> pipes = ConnectionMapGenerator.generateConnectionMap(connectionList);
    assertEquals(2, PipeTracer.extractConnectionsInGroup(pipes, "0").size());
  }

  @Test
  public void onePipeMultipleRecipients() {
    String[] connectionList = new String[1];
    connectionList[0] = "0 <-> 1, 2";
    Map<String, List<String>> pipes = ConnectionMapGenerator.generateConnectionMap(connectionList);

    assertEquals(3, PipeTracer.extractConnectionsInGroup(pipes, "0").size());
  }

  @Test
  public void twoPipes() {
    String[] connectionList = new String[2];
    connectionList[0] = "0 <-> 1";
    connectionList[1] = "1 <-> 2";
    Map<String, List<String>> pipes = ConnectionMapGenerator.generateConnectionMap(connectionList);

    assertEquals(3, PipeTracer.extractConnectionsInGroup(pipes, "0").size());
  }

  @Test
  public void someConnectionsRepeat() {
    String[] connectionList = new String[2];
    connectionList[0] = "0 <-> 1";
    connectionList[1] = "1 <-> 0, 2";
    Map<String, List<String>> pipes = ConnectionMapGenerator.generateConnectionMap(connectionList);

    assertEquals(3, PipeTracer.extractConnectionsInGroup(pipes, "0").size());
  }

  @Test
  public void acceptanceTest() {
    try {
      String[] connectionList = getTextFromFile("src/test/java/com/PD/AoC/acceptanceTest.txt");
      Map<String, List<String>> pipes = ConnectionMapGenerator.generateConnectionMap(connectionList);
      assertEquals(6, PipeTracer.extractConnectionsInGroup(pipes, "0").size());
    } catch(IOException e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void PuzzleOne() {
    try {
      String[] connectionList = getTextFromFile("src/test/java/com/PD/AoC/puzzleInput.txt");
      Map<String, List<String>> pipes = ConnectionMapGenerator.generateConnectionMap(connectionList);
      assertEquals(169, PipeTracer.extractConnectionsInGroup(pipes, "0").size());
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
