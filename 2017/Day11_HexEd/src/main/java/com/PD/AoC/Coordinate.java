package com.PD.AoC;

public class Coordinate {
  public int x;
  public int y;

  public Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public boolean equals(Coordinate c) {
    return (x == c.x && y == c.y);
  }
}