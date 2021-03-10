package slogo.controller.commands;

import slogo.controller.Command;
import slogo.model.Turtle;

public class PenDownCommand extends Command {

  private static final int NUM_PARAMS = 0;

  public PenDownCommand(){
    numParams = NUM_PARAMS;
  }

  @Override
  public double execute(Turtle turtle) {
    return turtle.isPenActive() ? 0 : 1;
  }
}
