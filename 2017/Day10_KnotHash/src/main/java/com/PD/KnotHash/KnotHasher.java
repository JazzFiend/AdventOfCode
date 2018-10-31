package com.PD.KnotHash;

import java.util.List;

public class KnotHasher {
  public static int computeKnotHash(int markCount, List<Integer> lengthList) {
    CircularList<Integer> list = new CircularList<Integer>();
    int pointer = 0;
    int skipSize = 0;
    for(int i = 0; i < markCount; i++) {
      list.add(i);
    }

    for(int reverseOffset : lengthList) {
      list.reverseSection(pointer, reverseOffset);
      pointer = (pointer + reverseOffset + skipSize) % list.size();
      skipSize++;
    }

    return list.at(0) * list.at(1);
  }
}