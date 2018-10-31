package com.PD.KnotHash;

public class LargestNumberCounter {
  public static int getLargestCount(int[] numbers) {
    int largest = Integer.MIN_VALUE;
    int count = 0;
    for (int number : numbers) {
        if (number >= largest) {
            largest = number;
            count = 0;
        }
        if(number == largest) {
            count++;
        }
    }
    return count;
  }
}
