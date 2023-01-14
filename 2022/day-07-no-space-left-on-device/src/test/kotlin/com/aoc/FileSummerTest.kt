package com.aoc

import com.aoc.models.FileTree
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FileSummerTest {
  @Test
  fun noFiles() {
    val tree = FileTree()
    val directoryMap = FileSummer.calculateFileSizePerDirectory(tree)
    assertEquals(0, directoryMap["/"])
  }

  @Test
  fun oneFile() {
    val tree = FileTree()
    tree.addFileToCurrent("file", 43)
    val directoryMap = FileSummer.calculateFileSizePerDirectory(tree)
    assertEquals(43, directoryMap["/"])
  }

  @Test
  fun manyFilesOnlyOnOneNode() {
    val tree = FileTree()
    tree.addFileToCurrent("file1", 7483)
    tree.addFileToCurrent("file2", 859)
    tree.addFileToCurrent("file3", 945)
    val directoryMap = FileSummer.calculateFileSizePerDirectory(tree)
    assertEquals((7483 + 859 + 945), directoryMap["/"])
  }

  @Test
  fun folderWithNoFiles() {
    val tree = FileTree()
    tree.addFileToCurrent("f1", 34)
    tree.addFolderToCurrent("A")
    tree.moveTo("A")
    tree.addFileToCurrent("f2", 654)
    tree.addFolderToCurrent("B")
    tree.moveTo("B")
    tree.moveToRoot()

    val directoryMap = FileSummer.calculateFileSizePerDirectory(tree)
    assertEquals((34 + 654), directoryMap["/"])
    assertEquals((654), directoryMap["A"])
    assertEquals((0), directoryMap["B"])
  }

  @Test
  fun differentFoldersWithSameName() {
    val tree = FileTree()
    tree.addFileToCurrent("f1", 424)
    tree.addFolderToCurrent("A")
    tree.moveTo("A")
    tree.addFileToCurrent("f1", 1232)
    tree.addFolderToCurrent("A")
    tree.moveTo("A")
    tree.addFileToCurrent("f1", 546)
    tree.moveToRoot()

    val directoryMap = FileSummer.calculateFileSizePerDirectory(tree)
    assertEquals(424 + 1232 + 546, directoryMap["/"])
    assertEquals(546, directoryMap["A-copy"])
    assertEquals(1232 + 546, directoryMap["A"])
  }

  @Test
  fun differentFoldersWithSameNameOnDifferentBranches() {
    val tree = FileTree()
    tree.addFileToCurrent("f", 342)
    tree.addFolderToCurrent("A")
    tree.addFolderToCurrent("B")
    tree.moveTo("A")
    tree.addFileToCurrent("f", 97)
    tree.addFolderToCurrent("C")
    tree.moveTo("C")
    tree.addFileToCurrent("f", 1265)
    tree.addFolderToCurrent("Repeat")
    tree.moveTo("Repeat")
    tree.addFileToCurrent("f", 965)
    tree.moveToRoot()
    tree.moveTo("B")
    tree.addFileToCurrent("f", 3232)
    tree.addFolderToCurrent("D")
    tree.moveTo("D")
    tree.addFileToCurrent("f", 142)
    tree.addFolderToCurrent("Repeat")
    tree.moveTo("Repeat")
    tree.addFileToCurrent("f", 799)
    tree.moveToRoot()

    val directoryMap = FileSummer.calculateFileSizePerDirectory(tree)
    assertEquals(342 + 965 + 1265 + 97 + 3232 + 142 + 799, directoryMap["/"])
    assertEquals(965 + 1265 + 97, directoryMap["A"])
    assertEquals(3232 + 142 + 799, directoryMap["B"])
    assertEquals(965 + 1265, directoryMap["C"])
    assertEquals(142 + 799, directoryMap["D"])
    assertEquals(799, directoryMap["Repeat-copy"])
    assertEquals(965, directoryMap["Repeat"])
  }

  @Test
  fun manyFoldersAcrossManyNodes() {
    val tree = FileTree()
    tree.addFileToCurrent("file1", 63)
    tree.addFolderToCurrent("G")
    tree.moveTo("G")
    tree.addFileToCurrent("file5", 36)
    tree.moveToParent()
    tree.addFolderToCurrent("L")
    tree.moveTo("L")
    tree.addFileToCurrent("file2", 98)
    tree.addFolderToCurrent("A")
    tree.moveTo("A")
    tree.addFileToCurrent("file3", 4)
    tree.addFileToCurrent("file4", 50)
    tree.moveToRoot();

    val directoryMap = FileSummer.calculateFileSizePerDirectory(tree)
    assertEquals((63 + 98 + 4 + 50 + 36), directoryMap["/"])
    assertEquals((4 + 50), directoryMap["A"])
    assertEquals((98 + 4 + 50), directoryMap["L"])
    assertEquals((36), directoryMap["G"])
  }

  @Test
  fun identicalNamesEverywhere() {
    val tree = FileTree()
    tree.addFolderToCurrent("1")
    tree.addFolderToCurrent("2")
    tree.addFileToCurrent("a", 1)
    tree.moveTo("1")

    tree.addFolderToCurrent("1")
    tree.addFileToCurrent("b", 2)
    tree.moveTo("1")

    tree.addFileToCurrent("c", 3)

    tree.moveToRoot()
    tree.moveTo("2")

    tree.addFolderToCurrent("1")
    tree.addFileToCurrent("d", 4)
    tree.moveTo("1")

    tree.addFileToCurrent("e", 5)
    tree.moveToRoot()

    val directoryMap = FileSummer.calculateFileSizePerDirectory(tree)
    assertEquals((1 + 2 + 3 + 4 + 5), directoryMap["/"])
    assertEquals((5), directoryMap["1"])
    assertEquals((4 + 5), directoryMap["2"])
    assertEquals((3), directoryMap["1-copy"])
    assertEquals((2 + 3), directoryMap["1-copy-copy"])
  }
}