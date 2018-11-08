package com.PD.KnotHash;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class KnotHasherTest {
  @Test
  public void hashEmptyString() {
    KnotHasher kh = new KnotHasher(16);
    String hash = kh.hash("");
    assertEquals("a2582a3a0e66e6e86e3812dcb672a272", hash);
  }

  @Test
  public void hashAlphaText() {
    KnotHasher kh = new KnotHasher(16);
    String hash = kh.hash("AoC 2017");
    assertEquals("33efeb34ea91902bb2f59c9920caa6cd", hash);
  }

  @Test
  public void hashNumberList() {
    KnotHasher kh = new KnotHasher(16);
    String hash = kh.hash("1,2,3");
    assertEquals("3efbe78a8d82f29979031a4aa0b16a9d", hash);
  }

  @Test
  public void hashNumberList2() {
    KnotHasher kh = new KnotHasher(16);
    String hash = kh.hash("1,2,4");
    assertEquals("63960835bcdc130f0b66d7ff4f6a5a8e", hash);
  }

  @Test
  public void PuzzlePart2() {
    KnotHasher kh = new KnotHasher(16);
    String hash = kh.hash("97,167,54,178,2,11,209,174,119,248,254,0,255,1,64,190");
    assertEquals("aff593797989d665349efe11bb4fd99b", hash);
  }
}