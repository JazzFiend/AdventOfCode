package com.PD.KnotHash;

import java.util.ArrayList;

public class CircularList<T> {
  private ArrayList<T> list;

  public CircularList() {
    list = new ArrayList<T>();
  }

  public boolean isEmpty() {
    return list.isEmpty();
  }

  public void add(T element) {
    list.add(element);
  }

  public T at(int location) {
    if(location < 0) {
      return list.get(list.size() - ((location * -1) % list.size()));
    } else {
      return list.get(location % list.size());
    }
  }

  public int size() {
    return list.size();
  }

  public void reverseSection(int start, int offset) {
    int pointer1 = start;
    int pointer2 = start + offset - 1;

    while(pointer1 < pointer2) {
      swapElements(pointer1++, pointer2--);
    }
  }

  private void swapElements(int pointer1, int pointer2) {
    T temp = this.at(pointer1);
    this.set(pointer1, this.at(pointer2));
    this.set(pointer2, temp);
  }

  private void set(int location, T value) {
    list.set(location % list.size(), value);
  }
}