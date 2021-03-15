package slogo.controller.commands;

import slogo.controller.Command;
import slogo.model.Turtle;

/**
 * And is a type of Command that checks whether both values are nonzero.
 *
 * @author Harrison Huang
 */

public class AndCommand extends Command {

  private static final int NUM_PARAMS = 2;

  /**
   * Constructor for AndCommand.
   */
  public AndCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Checks whether both values are nonzero.
   *
   * @param turtle The current turtle
   * @return 1 if both values are nonzero, else 0
   */
  @Override
  protected double executeCommand(Turtle turtle) {
    double firstVal = getChildren().get(0).execute(turtle);
    double secondVal = getChildren().get(1).execute(turtle);

    return ((firstVal != 0) && (secondVal != 0)) ? 1 : 0;
  }
}