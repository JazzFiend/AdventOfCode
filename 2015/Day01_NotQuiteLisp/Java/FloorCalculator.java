public class FloorCalculator {


  public static int calculateFloor(String floorPlan) {
    int total = 0;
    for(char c : floorPlan) {
      if (c == '(') {
        total++;
      } else if(c == ')') {
        total--;
      } else {
        System.out.print("Bad character seen");
      }
    }
    return total;
  }


}


public static void main(String[] args) {
  String floorPlan = "(())"

  System.out.print("Final Floor: " + FloorCalculator.calculateFloor(floorPlan));

}
