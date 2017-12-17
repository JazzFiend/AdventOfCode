import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class JumpInstructionParser {
  private static List<Integer> extractJumpInstructions(String instructionFilename) {
    ArrayList<Integer> jumpInstructions = new ArrayList<Integer>();
    try {
      String line;
      FileReader fileReader = new FileReader(instructionFilename);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      while((line = bufferedReader.readLine()) != null) {
        jumpInstructions.add(Integer.parseInt(line));
      }
    } catch(IOException ex) {
      System.out.println(ex);
    }
    return jumpInstructions;
  }

  public static void main(String[] args) {
    final String instructionFilename = "input.txt";
    List<Integer> jumpInstructions = extractJumpInstructions(instructionFilename);
    boolean escapedMaze = false;
    int stepCount = 0;
    int pointer = 0;

    while(!escapedMaze) {
      int moveValue = jumpInstructions.get(pointer);
      if(moveValue >= 3) {
        jumpInstructions.set(pointer, moveValue - 1);
      } else {
        jumpInstructions.set(pointer, moveValue + 1);
      }
      pointer += moveValue;
      stepCount++;
      if(pointer < 0 || pointer >= jumpInstructions.size()) {
        escapedMaze = true;
      }
    }

    System.out.println("Steps to Escape: " + stepCount);
  }
}
