package com.PD.AoC;

import java.util.Map;
import java.util.Queue;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Collection;

public class PipeTracer {
  private static void addIfNotSeen(Queue<String> connectionsToCheck, Collection<String> linkedConnections, String connection) {
    if(!linkedConnections.contains(connection)) {
      connectionsToCheck.add(connection);
    }
  }

  public static Collection<String> extractConnectionsInGroup(Map<String, List<String>> pipes, String start) {
    if(pipes.size() == 0) return new HashSet<String>();

    Queue<String> connectionsToCheck = new LinkedList<String>();
    Collection<String> linkedConnections = new HashSet<>();

    connectionsToCheck.add(start);
    linkedConnections.add(start);
    while(!connectionsToCheck.isEmpty()) {
      String currentConnection = connectionsToCheck.remove();
      if(pipes.containsKey(currentConnection)) {
        List<String> nextConnections = pipes.get(currentConnection);
        nextConnections.forEach((connection) -> {
          addIfNotSeen(connectionsToCheck, linkedConnections, connection);
        });
        linkedConnections.addAll(nextConnections);
      }
    }
    return linkedConnections;
  }
}
