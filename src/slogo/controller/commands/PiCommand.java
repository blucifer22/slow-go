package slogo.controller.commands;

import slogo.controller.Command;
import slogo.model.Turtle;

/**
 * Pi is a type of Command that returns the value of pi.
 *
 * @author Harrison Huang
 */

public class PiCommand extends Command {

  private static final int NUM_PARAMS = 0;

  /**
   * Constructor for PiCommand.
   */
  public PiCommand() {
    setNumParams(NUM_PARAMS);
  }

  /**
   * Returns the value of pi.
   *
   * @param turtle The current turtle
   * @return value of pi
   */
  @Override
  protected double executeCommand(Turtle turtle) {
    return Math.PI;
  }

}
