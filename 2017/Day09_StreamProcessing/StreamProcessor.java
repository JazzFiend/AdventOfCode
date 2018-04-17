import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StreamProcessor {

  public static void main(String[] args) {
    String stream = getStreamFromFile("input.txt");
    // String stream = "{{<a!>},{<a!>},{<a!>},{<ab>}}";
    StreamStateMachine streamStateMachine = new StreamStateMachine();
    for(char character : stream.toCharArray()) {
      streamStateMachine.analyzeCharacter(character);
    }
    System.out.println(String.format("Score: %s", streamStateMachine.getScore()));
    System.out.println(String.format("Garbage Count: %s", streamStateMachine.getGarbageCount()));
  }

  private static String getStreamFromFile(String fileName) {
    try {
      String line;
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      line = bufferedReader.readLine();
      bufferedReader.close();
      return line;
    } catch(IOException ex) {
      System.out.println(ex);
      return null;
    }
  }
}
