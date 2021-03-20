package slogo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import slogo.model.GridCoordinates;
import slogo.model.ModelPen;
import slogo.model.Palette;
import slogo.model.Turtle;

/**
 * TurtleGeneral is a delightfully named marshalling class for controlling n-Turtles by way of their
 * TurtleControllers. Also contains global Turtle data like variables, user-defined functions, and
 * color palettes.
 *
 * @author Marc Chmielewski
 */
public class TurtleGeneral {
  private final List<TurtleController> turtleArmy;
  // TODO: Move control of Variables and UserCommands here
  public static Palette palette;
  private final List<Integer> activeTurtleIds;

  public TurtleGeneral(List<TurtleController> turtleArmy) {
    this.turtleArmy = turtleArmy;
    palette = new Palette();
    activeTurtleIds = new ArrayList<>();
    for(TurtleController curController : turtleArmy) {
      activeTurtleIds.add(curController.getTurtle().getId());
    }
  }

  public TurtleGeneral(TurtleController turtleConscript) {
    this.turtleArmy = List.of(turtleConscript);
    palette = new Palette();
    activeTurtleIds = new ArrayList<>();
    for(TurtleController curController : turtleArmy) {
      activeTurtleIds.add(curController.getTurtle().getId());
    }
  }

  public void conscriptTurtle(Turtle recruitTurtle) {
    if(turtleArmy.size() < recruitTurtle.getId()) {
      for(int i = turtleArmy.size(); i <= recruitTurtle.getId(); i++) {
        // TODO: Implement turtle conscription behavior (REQUIRES FIXED LISTENERS)
      }
    }
  }

  public Palette getPalette() {
    return palette;
  }

  public List<Integer> getActiveTurtleIds() {
    return activeTurtleIds;
  }

  public void setActiveTurtles(List<Integer> activeTurtleIds) {
    this.activeTurtleIds.clear();
    this.activeTurtleIds.addAll(activeTurtleIds);
  }

  public List<TurtleController> getTurtleArmy() {
    return turtleArmy;
  }
}