import java.util.Hashtable;
import java.util.ArrayList;

public class SpiralMemoryReader {
  public enum Direction {
    UP, DOWN, LEFT, RIGHT
  }

  private static String coordinatesToString(int x, int y) {
    return Integer.toString(x) + "," + Integer.toString(y);
  }

  private static ArrayList<String> getValidSurroundingCoordinates(int x, int y, Hashtable<String, Integer> graphValuePairs) {
    ArrayList<String> validCoordinates = new ArrayList<String>();
    for(int i = x - 1; i <= x + 1; i++) {
      for(int j = y - 1; j <= y + 1; j++) {
        if(i == x && j == y) {
          continue;
        }
        if(graphValuePairs.containsKey(coordinatesToString(i, j))) {
          validCoordinates.add(coordinatesToString(i, j));
        }
      }
    }
    return validCoordinates;
  }

  public static void main(String[] args) {
    final int stopPoint = 325489;
    int xPointer = 0;
    int yPointer = 0;
    int leftMax = 0;
    // Need to start the inital case
    int rightMax = 1;
    int upMax = 0;
    int downMax = 0;
    boolean stopPointExceeded = false;
    Direction direction = Direction.RIGHT;
    Hashtable<String, Integer> graphValuePairs = new Hashtable<String, Integer>();
    graphValuePairs.put(coordinatesToString(xPointer, yPointer), 1);

    for(int i = 1; i < stopPoint; i++) {
      switch(direction) {
        case LEFT :
          if(--xPointer <= leftMax) {
            direction = Direction.DOWN;
            downMax--;
          }
          break;

        case RIGHT :
          if(++xPointer >= rightMax) {
            direction = Direction.UP;
            upMax++;
          }
          break;

        case UP :
          if(++yPointer >= upMax) {
            direction = Direction.LEFT;
            leftMax--;
          }
          break;

        case DOWN :
          if(--yPointer <= downMax) {
            direction = Direction.RIGHT;
            rightMax++;
          }
          break;
      }

      ArrayList<String> coordinatesToSum = getValidSurroundingCoordinates(xPointer, yPointer, graphValuePairs);
      int sum = 0;
      for(String coordinate : coordinatesToSum) {
        sum += graphValuePairs.get(coordinate);
      }
      if(!stopPointExceeded && sum > stopPoint) {
        System.out.format("First value greater than stop point: %d\n", sum);
        stopPointExceeded = true;
      }
      graphValuePairs.put(coordinatesToString(xPointer, yPointer), sum);
    }

    System.out.format("Value at Stop Point: %d\n", graphValuePairs.get(coordinatesToString(xPointer, yPointer)));
    if(xPointer < 0) {
      xPointer = xPointer * -1;
    }
    if(yPointer < 0) {
      yPointer = yPointer * -1;
    }

    System.out.format("Manhattan Distance: %d\n", (xPointer + yPointer));
  }
}
