public class Instruction {
  private String modifiedRegister;
  private Action action;
  private int value;
  private String conditionalRegister;
  private Conditional conditional;
  private int conditionalArguement;

  public Instruction(String modifiedRegister, Action action, int value, String conditionalRegister, Conditional conditional, int conditionalArguement) {
    this.modifiedRegister = modifiedRegister;
    this.action = action;
    this.value = value;
    this.conditionalRegister = conditionalRegister;
    this.conditional = conditional;
    this.conditionalArguement = conditionalArguement;
  }

  public String getModifiedRegister() {
    return modifiedRegister;
  }

  public Action getAction() {
    return action;
  }

  public String getConditionalRegister() {
    return conditionalRegister;
  }

  public int getValue() {
    return value;
  }

  public static Conditional decodeConditional(String conditionalString) {
    if(conditionalString.equals(">")) {
      return Conditional.GREATER_THAN;
    } else if(conditionalString.equals("<")) {
      return Conditional.LESS_THAN;
    } else if(conditionalString.equals(">=")) {
      return Conditional.GREATER_EQUAL;
    } else if(conditionalString.equals("<=")) {
      return Conditional.LESS_EQUAL;
    } else if(conditionalString.equals("==")) {
      return Conditional.EQUAL;
    } else if(conditionalString.equals("!=")) {
      return Conditional.NOT_EQUAL;
    } else {
      System.out.print("Invalid Conditional Seen");
    }
    return null;
  }

  public static Action decodeAction(String actionString) {
    if(actionString.equals("inc")) {
      return Action.INC;
    } else if(actionString.equals("dec")) {
      return Action.DEC;
    } else {
      System.out.print(String.format("Invalid Action Seen: %s", actionString));
    }
    return null;
  }

  public String toString() {
    return String.format("%s %s %d %s %s %d", modifiedRegister, action.name(), value, conditionalRegister, conditional.name(), conditionalArguement);
  }

  public boolean evaluateConditional(int registerValue) {
    if(conditional == Conditional.GREATER_THAN) {
      return registerValue > conditionalArguement;
    } else if(conditional == Conditional.LESS_THAN) {
      return registerValue < conditionalArguement;
    } else if(conditional == Conditional.GREATER_EQUAL) {
      return registerValue >= conditionalArguement;
    } else if(conditional == Conditional.LESS_EQUAL) {
      return registerValue <= conditionalArguement;
    } else if(conditional == Conditional.EQUAL) {
      return registerValue == conditionalArguement;
    } else if(conditional == Conditional.NOT_EQUAL) {
      return registerValue != conditionalArguement;
    } else {
      return false;
    }
  }
}