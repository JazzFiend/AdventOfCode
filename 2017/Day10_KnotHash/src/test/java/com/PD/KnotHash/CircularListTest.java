package com.PD.KnotHash;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class CircularListTest {
  CircularList<Integer> list;
  @Before
  public void setup() {
    list = new CircularList<Integer>();
  }

  private void setupSimpleList() {
    list.add(1);
    list.add(2);
    list.add(3);
  }

  private void setupComplextList() {
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    list.add(5);
  }

  @Test
  public void createEmptyList() {
    assertTrue(list.isEmpty());
  }

  @Test
  public void canAddToList() {
    list.add(1);
    assertFalse(list.isEmpty());
    int listItem = list.at(0);
    assertEquals(1, listItem);
    assertEquals(1, list.size());
  }

  @Test
  public void canReferencePastEndOfList() {
    setupSimpleList();

    int listItem = list.at(3);
    assertEquals(1, listItem);
    listItem = list.at(4);
    assertEquals(2, listItem);
    listItem = list.at(100);
    assertEquals(2, listItem);
  }

  @Test
  public void useNegativeNumberAsIndex() {
    setupSimpleList();

    int listItem = list.at(-1);
    assertEquals(3, listItem);
    listItem = list.at(-2);
    assertEquals(2, listItem);
    listItem = list.at(-100);
    assertEquals(3, listItem);
  }

  @Test
  public void reverseSectionTest() {
    setupComplextList();
    list.reverseSection(1, 3);
    int listItem = list.at(0);
    assertEquals(1, listItem);
    listItem = list.at(1);
    assertEquals(4, listItem);
    listItem = list.at(2);
    assertEquals(3, listItem);
    listItem = list.at(3);
    assertEquals(2, listItem);
    listItem = list.at(4);
    assertEquals(5, listItem);
  }

  @Test
  public void reverseSection_evenNumberOfElementsTest() {
    setupComplextList();
    list.reverseSection(1, 4);
    int listItem = list.at(0);
    assertEquals(1, listItem);
    listItem = list.at(1);
    assertEquals(5, listItem);
    listItem = list.at(2);
    assertEquals(4, listItem);
    listItem = list.at(3);
    assertEquals(3, listItem);
    listItem = list.at(4);
    assertEquals(2, listItem);
  }

  @Test
  public void reverseSection_extendPastEnd() {
    setupComplextList();
    list.reverseSection(4, 3);
    int listItem = list.at(0);
    assertEquals(1, listItem);
    listItem = list.at(1);
    assertEquals(5, listItem);
    listItem = list.at(2);
    assertEquals(3, listItem);
    listItem = list.at(3);
    assertEquals(4, listItem);
    listItem = list.at(4);
    assertEquals(2, listItem);
  }

  @Test
  public void reverseEntireList() {
    setupComplextList();
    list.reverseSection(1, 5);
    int listItem = list.at(0);
    assertEquals(2, listItem);
    listItem = list.at(1);
    assertEquals(1, listItem);
    listItem = list.at(2);
    assertEquals(5, listItem);
    listItem = list.at(3);
    assertEquals(4, listItem);
    listItem = list.at(4);
    assertEquals(3, listItem);
  }

  @Test
  public void exampleTest() {
    list.add(4);
    list.add(3);
    list.add(0);
    list.add(1);
    list.add(2);
    list.reverseSection(1, 5);
    int listItem = list.at(0);
    assertEquals(3, listItem);
    listItem = list.at(1);
    assertEquals(4, listItem);
    listItem = list.at(2);
    assertEquals(2, listItem);
    listItem = list.at(3);
    assertEquals(1, listItem);
    listItem = list.at(4);
    assertEquals(0, listItem);
  }
}
