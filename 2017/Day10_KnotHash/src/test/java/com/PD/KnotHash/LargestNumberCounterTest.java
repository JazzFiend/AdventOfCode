package com.PD.KnotHash;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LargestNumberCounterTest {
  @Test
  public void findMissingNumber() {
    int count = LargestNumberCounter.getLargestCount(new int[]{-20, 34, 21, -87, 92});
    assertEquals(1, count);
  }
}