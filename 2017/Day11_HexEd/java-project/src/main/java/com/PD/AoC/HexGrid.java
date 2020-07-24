package com.PD.AoC;

public class HexGrid {
  public static Coordinate trace(String steps) throws BadDirectionException {
    Coordinate location = new Coordinate(0, 0);
    String[] tokenizedSteps = steps.split(",");

    for(String step : tokenizedSteps) {
      location = advanceCoordinate(location, step);
    }
    return location;
  }

  private static Coordinate advanceCoordinate(Coordinate c, String step) throws BadDirectionException {
    if(step.equals("n"))  {
      c.y = c.y + 2;
    } else if(step.equals("nw")) {
      c.x = c.x - 1;
      c.y = c.y + 1;
    } else if(step.equals("sw")) {
      c.x = c.x - 1;
      c.y = c.y - 1;
    } else if(step.equals("s")) {
      c.y = c.y - 2;
    } else if(step.equals("se")) {
      c.x = c.x + 1;
      c.y = c.y - 1;
    } else if(step.equals("ne")) {
      c.x = c.x + 1;
      c.y = c.y + 1;
    } else {
      throw new BadDirectionException(String.format("Wrong direction given: %s", step));
    }
    return c;
  }

  public static int calculateStepsAway(Coordinate c) {
    int x = Math.abs(c.x);
    int y = Math.abs(c.y);
    int total = 0;
    int min = Math.min(x, y);

    total += min;
    x -= min;
    y -= min;

    if(x == 0) {
      total += y/2;
    } else if(y == 0) {
      total += x;
    }

    return total;
  }

  public static int maxDistanceDuringTrace(String steps)  throws BadDirectionException {
    Coordinate location = new Coordinate(0, 0);
    String[] tokenizedSteps = steps.split(",");
    int maxDistance = 0;

    for(String step : tokenizedSteps) {
      location = advanceCoordinate(location, step);
      int currentDistance = calculateStepsAway(location);
      if(maxDistance < currentDistance) {
        maxDistance = currentDistance;
      }
    }
    return maxDistance;
  }
}