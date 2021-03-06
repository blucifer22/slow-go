private class MarcUsecases {

  /**
   * The user types 'fd 50' in the command window, and sees the turtle move in the display window
   * leaving a trail, and the command is added to the environment's history.
   */
  public void fd50(String commandString) {
    CommandParser parser = new CommandParser();

    // This will contain a single command, that uses the forward function with parameter 50
    List<Command> commandList = parser.parseCommnands(commandString);

    Turtle turtle = new Turtle(); // Default constructor will have penDown, and isVisible by default
    for (Command command : commandList) {
      try {
        int successful = 0;
        if (turtle.hasCommand(command)) {
          successful = turtle
              .executeCommand(command); // Execution of command is handled by the Turtle
        }
        if (successful == 0) {
          throw new Exception;
        }
      }
    }
    turtle.alertListener(); // Wake up the listener (is this necessary?) and have it update the view
  }

  /**
   * The user defines a new function and sees the function appear in the function view
   *
   * Ex.) commandString = "TO HELLO[A B][ fd A bk B ]"
   * This will take the second branch in execute and actually define a new command.
   */
  public void declareFunction(String commandString) {
    CommandParser parser = new CommandParser();

    // This will contain a single command, that defines a new function.
    List<Command> commandList = parser.parseCommnands(commandString);
    Turtle turtle = new Turtle(); // Default constructor will have penDown, and isVisible by default
    for (Command command : commandList) {
      try {
        int successful = 0;
        if (turtle.hasCommand(command)) {
          successful = turtle
              .executeCommand(command); // Execution of command is handled by the Turtle
        }
        if (successful == 0) {
          throw new Exception;
        }
      }
    }
    turtle.alertListener(); // Wake up the listener (is this necessary?) and have it update the view
  }

  /**
   * The user defines a new function but uses a reserved keyword
   *
   * Ex.) commandString = "TO fd[A B][ fd A bk B ]"
   *
   * This will throw a ReservedKeywordException and print to the error stream.
   */
  public void declareFunction(String commandString) {
    CommandParser parser = new CommandParser();

    // This will contain a single command, that defines a new function.
    List<Command> commandList = parser.parseCommnands(commandString);
    Turtle turtle = new Turtle(); // Default constructor will have penDown, and isVisible by default
    for (Command command : commandList) {
      try {
        int successful = 0;
        if (turtle.hasCommand(command)) {
          successful = turtle
              .executeCommand(command); // Execution of command is handled by the Turtle
        }
        if (successful == 0) {
          throw new Exception;
        }
      }
    }
    turtle.alertListener(); // Wake up the listener (is this necessary?) and have it update the view
  }
}

/**
 * Dummy (and VERY drafty) implementation of the Turtle class with the requisite methods for these
 * user stories.
 */
public class Turtle {

  private List<Command> knownCommands;
  private List<Variable> knownVariables;

  public int executeCommand(Command command) {
    if (command.getType() == FORWARDS) {
      turtle.moveForwards(command.getMagnitude());
    } else if (command.getType() == TO) {
      implementNewCommand(command);
    }
  }

  private void implementNewCommand(Command command) {
    try {
      List<Token> tokenList = command.getTokens();
      Command newCommand = new Command();
      CommandType newCommandType = new CommandType(tokenList.get(0));
      List<Variable> newCommandParameters = new ArrayList<>();
      List<Command> newCommandCommands = new ArrayList<>();
      for (Token token : tokenList) {
        if (token.getType() == PARAM) {
          newCommandParameters.add(new Variable(token));
        } else if (token.getType() == COMMAND) {
          newCommandParameters.add(command);
        }
      }
      knownCommands
          .addCommand(new Command(newCommandType, newCommandParameters, newCommandCommands));
    }
    catch(ReservedKeywordException e) {
      System.err.println("ERROR: Reserved keyword exception. Try another keyword!");
      throw e; // Throw the exception upstream to be handled by view
    }
  }

  public int hasCommand(Command command) {
    if(knownCommands.contains(command)) {
      return 1;
    }
    return 0;
  }
}