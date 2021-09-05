package com.PD.AoC;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectionMapGenerator {
  static Map<String, List<String>> generateConnectionMap(String[] connections) {
    Map<String, List<String>> pipes = new HashMap<>();
  
    Arrays.asList(connections).forEach((connection) -> {
      String[] pipeSplit = connection.split(" <-> ");
      String[] destinations = pipeSplit[1].split(", ");
      pipes.put(pipeSplit[0], Arrays.asList(destinations));
    });
    return pipes;
  }
}
