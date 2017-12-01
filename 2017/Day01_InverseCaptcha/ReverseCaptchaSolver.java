import java.io.*;

public class ReverseCaptchaSolver {
  public static void main(String[] args) throws Exception {
    try {
      String fileName = "captcha.txt";
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      final String digitSequence = bufferedReader.readLine();
      //final String digitSequence = "1111";

      System.out.format("One Step Total: %d\n", calculateRepeatValues(digitSequence, 1));
      System.out.format("Halfway Total: %d\n", calculateRepeatValues(digitSequence, digitSequence.length() / 2));
    } catch(Exception e) {
      System.out.println(e);
    }
  }

  private static int calculateRepeatValues(String digitSequence, int stepsAhead) {
    int sum = 0;
    for(int i = 0; i < digitSequence.length(); i++) {
      // Supports wrapping around string
      int comparePoint = (i + stepsAhead) % digitSequence.length();
      if(digitSequence.charAt(i) == digitSequence.charAt(comparePoint)) {
        sum += Character.getNumericValue(digitSequence.charAt(i));
      }
    }
    return sum;
  }
}
