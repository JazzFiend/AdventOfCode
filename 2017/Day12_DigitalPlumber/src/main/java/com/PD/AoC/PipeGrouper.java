package com.PD.AoC;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Collection;

public class PipeGrouper {
  public static int countDistinctGroups(Map<String, List<String>> pipes) {
    Set<String> connectionsToCheck = pipes.keySet();
    int groups = 0;
    while(!connectionsToCheck.isEmpty()) {
      String startingConnection = connectionsToCheck.toArray(String[]::new)[0];
      Collection<String> connectionsInGroup = PipeTracer.extractConnectionsInGroup(pipes, startingConnection);
      groups++;
      connectionsToCheck.removeAll(connectionsInGroup);
    }
    return groups;
  }
}
