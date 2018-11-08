package com.PD.KnotHash;

import java.util.ArrayList;
import java.util.List;

public class KnotHasher {
  private int denseHashMultiple;

  public KnotHasher(int denseHashMultiple) {
    this.denseHashMultiple = denseHashMultiple;
  }

  public String hash(String seed) {
    List<Integer> inputLengths = calculateInputLengths(seed);
    CircularList<Integer> sparseHash = runRounds(inputLengths);
    CircularList<Integer> denseHash = convertToDenseHash(sparseHash);
    return listToHex(denseHash);
  }

  private CircularList<Integer> runRounds(List<Integer> inputLengths) {
    KnotHashRoundComputer hashComputer = new KnotHashRoundComputer(256);
    CircularList<Integer> sparseHash = new CircularList<Integer>();
    for (int i = 0; i < 64; i++) {
      sparseHash = hashComputer.computeKnotHash(256, inputLengths);
    }
    return sparseHash;
  }

  private List<Integer> calculateInputLengths(String seed) {
    List<Integer> asciiSeed = convertByteArrayToList(seed.getBytes());
    List<Integer> finalInputList = addSalt(asciiSeed);
    return finalInputList;
  }

  public List<Integer> convertByteArrayToList(byte[] byteArray) {
    List<Integer> intList = new ArrayList<Integer>();
    for (byte b : byteArray) {
      intList.add(Byte.toUnsignedInt(b));
    }
    return intList;
  }

  public List<Integer> addSalt(List<Integer> list) {
    List<Integer> l = list;
    l.add(17);
    l.add(31);
    l.add(73);
    l.add(47);
    l.add(23);
    return l;
  }

  public CircularList<Integer> convertToDenseHash(CircularList<Integer> sparseHash) {
    int count = 0;
    int xorTotal = 0;
    CircularList<Integer> denseHash = new CircularList<Integer>();

    for (Integer element : sparseHash) {
      xorTotal ^= element;
      if (count >= denseHashMultiple - 1) {
        denseHash.add(xorTotal);
        xorTotal = 0;
        count = -1;
      }
      count++;
    }
    return denseHash;
  }

  public String listToHex(CircularList<Integer> list) {
    String hexString = "";

    for (Integer element : list) {
      if (element < 16) {
        hexString += "0";
      }
      hexString += Integer.toHexString(element);
    }
    return hexString;
  }
}