package com.PD.KnotHash;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class KnotHashRoundComputerTest {
  @Test
  public void fiveElementListTest() {
    KnotHashRoundComputer hashComputer = new KnotHashRoundComputer(5);
    ArrayList<Integer> inputList = new ArrayList<Integer>();
    inputList.add(3);
    inputList.add(4);
    inputList.add(1);
    inputList.add(5);
    int hash = hashComputer.testableComputeKnotHash(5, inputList);

    assertEquals(12, hash);
  }

  @Test
  public void PuzzlePart1() {
    KnotHashRoundComputer hashComputer = new KnotHashRoundComputer(256);
    ArrayList<Integer> inputList = new ArrayList<Integer>();
    inputList.add(97);
    inputList.add(167);
    inputList.add(54);
    inputList.add(178);
    inputList.add(2);
    inputList.add(11);
    inputList.add(209);
    inputList.add(174);
    inputList.add(119);
    inputList.add(248);
    inputList.add(254);
    inputList.add(0);
    inputList.add(255);
    inputList.add(1);
    inputList.add(64);
    inputList.add(190);
    int hash = hashComputer.testableComputeKnotHash(256, inputList);

    assertEquals(8536, hash);
  }
}
