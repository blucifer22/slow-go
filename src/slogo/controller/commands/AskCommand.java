package slogo.controller.commands;

import java.util.List;
import slogo.controller.Command;
import slogo.controller.GlobalProperties;
import slogo.controller.ListCommandHead;
import slogo.controller.TurtleController;
import slogo.model.Turtle;

/**
 * Ask is a type of Command that executes commands on a given set of turtles.
 *
 * @author Harrison Huang
 */

public class AskCommand extends Command {

  private static final int NUM_PARAMS = 2;
  private static final int TURTLE_IDS_INDEX = 0;
  private static final int COMMANDS_INDEX = 1;


  /**
   * Constructor for AskCommand.
   */
  public AskCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Runs commands on a specific set of turtles.
   *
   * @param turtle           The current active turtle
   * @param globalProperties The global properties
   * @return The result of the last command run
   */
  @Override
  protected double executeCommand(Turtle turtle, GlobalProperties globalProperties) {

    ListCommandHead askListCommand = (ListCommandHead) getChildren().get(TURTLE_IDS_INDEX);

    List<TurtleController> turtleArmy = globalProperties.getCopyOfTurtleArmy();
    double lastCommandValue = 0;

    for (int i = 0; i < askListCommand.getInnerChildren().size(); i++) {
      int turtleID = (int) askListCommand.getInnerChildren().get(i)
          .execute(turtle, globalProperties);
      if (turtleID <= globalProperties.getNumTurtlesCreated()) {
        for (TurtleController tc : turtleArmy) {
          if (tc.getTurtle().getId() == turtleID) {
            lastCommandValue = getChildren().get(COMMANDS_INDEX)
                .execute(tc.getTurtle(), globalProperties);
            continue;
          }
        }
      }
    }

    return lastCommandValue;
  }

}
