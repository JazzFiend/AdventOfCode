public class StreamStateMachine {
  private StreamState streamState;
  private int totalScore;
  private int currentGroupScore;
  private int garbageCount;
  
  public StreamStateMachine() {
    streamState = StreamState.GROUP;
    totalScore = 0;
    currentGroupScore = 0;
    garbageCount = 0;
  }

  public void analyzeCharacter(char character) {
    if(streamState == StreamState.GROUP) {
      performGroupAction(character);
    } else if(streamState == StreamState.GARBAGE) {
      performGarbageAction(character);
    } else if(streamState == StreamState.IGNORE) {
      performIgnoreAction(character);
    }
  }

  private void performGroupAction(char character) {
    if(isGarbageStart(character)) {
      streamState = StreamState.GARBAGE;
    }
    else if(isNewGroup(character)) {
      currentGroupScore++;
      totalScore += currentGroupScore;
    } else if(isEndGroup(character)) {
      currentGroupScore--;
    }
  }

  private void performGarbageAction(char character) {
    if(isGarbageEnd(character)) {
      streamState = StreamState.GROUP;
    } else if(isIgnoreCharacter(character)) {
      streamState = StreamState.IGNORE;
    } else {
      garbageCount++;
    }
  }

  private void performIgnoreAction(char character) {
    streamState = StreamState.GARBAGE;
  }

  private boolean isNewGroup(char c) {
    return c == '{';
  }

  private boolean isEndGroup(char c) {
    return c == '}';
  }

  private boolean isGarbageStart(char c) {
    return c == '<';
  }

  private boolean isGarbageEnd(char c) {
    return c == '>';
  }

  private boolean isIgnoreCharacter(char c) {
    return c == '!';
  }

  public int getScore() {
    return totalScore;
  }

  public int getGarbageCount() {
    return garbageCount;
  }
}