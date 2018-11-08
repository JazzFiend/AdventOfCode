package com.PD.KnotHash;

import java.util.List;

public class KnotHashRoundComputer {
  private int pointer;
  private int skipSize;
  private CircularList<Integer> list;
  private int markCount;

  public KnotHashRoundComputer(int markCount) {
    this.markCount = markCount;
    resetHash();
  }

  public void resetHash() {
    pointer = 0;
    skipSize = 0;
    list = new CircularList<Integer>();
    initializeNumberSequence(markCount);
  }

  public CircularList<Integer> computeKnotHash(int markCount, List<Integer> lengthList) {
    for (int reverseOffset : lengthList) {
      list.reverseSection(pointer, reverseOffset);
      pointer = (pointer + reverseOffset + skipSize) % list.size();
      skipSize++;
    }
    return list;
  }

  private void initializeNumberSequence(int markCount) {
    for (int i = 0; i < markCount; i++) {
      list.add(i);
    }
  }

  public int testableComputeKnotHash(int markCount, List<Integer> lengthList) {
    CircularList<Integer> list = computeKnotHash(markCount, lengthList);
    return list.at(0) * list.at(1);
  }
}