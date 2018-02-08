import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jdk.nashorn.internal.runtime.regexp.joni.Config;

public class SimpleCPU {
  private static List<Instruction> gatherInstructions(String filename) {
    try {
      String line;
      FileReader fileReader = new FileReader(filename);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      ArrayList<Instruction> instructions = new ArrayList<Instruction>();

      while((line = bufferedReader.readLine()) != null) {
        String[] arguments = line.split(" ");
        instructions.add(new Instruction(arguments[0], Instruction.decodeAction(arguments[1]), Integer.parseInt(arguments[2]), arguments[4], Instruction.decodeConditional(arguments[5]), Integer.parseInt(arguments[6])));
      }
      return instructions;
    } catch(IOException ex) {
      System.out.println(ex);
      return null;
    }
  }

  private static int executeInstructions(List<Instruction> instructions, Map<String, Integer> registers) {
    boolean conditionalEval;
    int largestValueSeen = 0;
    for(Instruction i : instructions) {
      if(!registers.containsKey(i.getModifiedRegister())) {
        registers.put(i.getModifiedRegister(), 0);
      }
      if(!registers.containsKey(i.getConditionalRegister())) {
        registers.put(i.getConditionalRegister(), 0);
      }
      conditionalEval = i.evaluateConditional(registers.get(i.getConditionalRegister()));
      if(conditionalEval) {
        int newValue = 0;
        if(i.getAction() == Action.INC) {
          newValue = registers.get(i.getModifiedRegister()) + i.getValue();
        } else if(i.getAction() == Action.DEC) {
          newValue = registers.get(i.getModifiedRegister()) - i.getValue(); 
        }
        if(newValue > largestValueSeen) {
          largestValueSeen = newValue;
        }
        registers.put(i.getModifiedRegister(), newValue);
      }
    }
    return largestValueSeen;
  }

  private static int getLargestValue(Map<String, Integer> map) {
    int largestValueSeen = Integer.MIN_VALUE;
    for(int value : map.values()) {
      if(value > largestValueSeen) {
        largestValueSeen = value;
      }
    }
    return largestValueSeen;
  }

  public static void main(String[] args) {
    final String filename = "input.txt";
    List<Instruction> instructions = gatherInstructions(filename);
    HashMap<String, Integer> registers = new HashMap<String, Integer>();
    System.out.print(String.format("Largest Value Ever Seen: %d\n", executeInstructions(instructions, registers)));
    System.out.print(String.format("Largest Register Value: %s\n", getLargestValue(registers)));
  }
}