import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.io.*;

public class MemoryAllocator {
  private static List<Integer> extractMemoryStartState(String filename) {
    ArrayList<Integer> memory = new ArrayList<Integer>();
    try {
      String line;
      FileReader fileReader = new FileReader(filename);
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      line = bufferedReader.readLine();
      for(String memoryValue : line.split(" ")) {
        memory.add(Integer.parseInt(memoryValue));
      }
    } catch(IOException ex) {
      System.out.println(ex);
    }
    return memory;
  }

  private static String generateString(List<Integer> integerList) {
    String toReturn = "";
    for(Integer i : integerList) {
      toReturn += i.toString() + " ";
    }
    return toReturn;
  }

  private static int getLargestMemoryBucket(List<Integer> memory) {
    int indexOfLargestValue = -1;
    int largestValue = -1;
    for(int i = 0; i < memory.size(); i++) {
      if(memory.get(i) > largestValue) {
        largestValue = memory.get(i);
        indexOfLargestValue = i;
      }
    }
    return indexOfLargestValue;
  }

  private static List<Integer> allocateMemory(List<Integer> memory) {
    int location = getLargestMemoryBucket(memory);
    int blocks = memory.get(location);
    memory.set(location, 0);
    while(blocks != 0) {
      if(location == memory.size() - 1) {
        location = 0;
      } else {
        location++;
      }
      memory.set(location, memory.get(location) + 1);
      blocks--;
    }
    return memory;
  }

  public static void main(String[] args) {
    final String startStateFilename = "input.txt";
    List<Integer> memory = extractMemoryStartState(startStateFilename);
    HashSet<String> memoriesSeenBefore = new HashSet<String>();
    memoriesSeenBefore.add(generateString(memory));
    boolean continueLoop = true;
    int cycleCount = 0;
    int cyclesTillFirstRepeat = 0;
    String firstRepeatedMemory;

    while(continueLoop) {
      cycleCount++;
      memory = allocateMemory(memory);
      String memoryString = generateString(memory);
      if(memoriesSeenBefore.contains(memoryString)) {
        continueLoop = false;
      } else {
        memoriesSeenBefore.add(memoryString);
      }
    }

    System.out.println("Cycles after a repeat: " + cycleCount);

    firstRepeatedMemory = generateString(memory);
    continueLoop = true;
    cycleCount = 0;

    while(continueLoop) {
      cycleCount++;
      memory = allocateMemory(memory);
      if(firstRepeatedMemory.equals(generateString(memory))) {
        continueLoop = false;
      }
    }
    System.out.println("Cycles to force another repeat: " + cycleCount);
  }
}
