package slogo.view.canvas;

import java.util.function.Consumer;
import javafx.scene.layout.StackPane;
import slogo.model.Coordinates;
import slogo.view.SelectorTarget;

public class Grid extends StackPane implements SelectorTarget<String> {

  public static final double DEFAULT_GRID_WIDTH = 600;
  public static final double DEFAULT_GRID_HEIGHT = DEFAULT_GRID_WIDTH / SLogoCanvas.GRID_ASPECT_RATIO;

  private final GridLines myGridLines;
  private final TurtleView myTurtleView;

  public Grid() {
    this.setId("Grid");

    myGridLines = new GridLines();
    myGridLines.changeGridType("None");
    myTurtleView = new TurtleView();
    myTurtleView.setXCoordinate(convertXCoordinate(0));
    myTurtleView.setYCoordinate(convertYCoordinate(0));
    myTurtleView.setHeading(convertHeading(0));

    this.getChildren().addAll(myGridLines, myTurtleView);
  }

  public TurtleView getTurtleView() {
    return myTurtleView;
  }

  @Override
  public Consumer<String> updateAction() {
    return myGridLines::changeGridType;
  }

  public void resizeElements() {
    myGridLines.resize();
    myTurtleView.setTranslateX(convertXCoordinate(myTurtleView.getXCoordinate()));
    myTurtleView.setTranslateY(convertYCoordinate(myTurtleView.getYCoordinate()));
  }

  public void setTurtleHeading(double heading) {
    myTurtleView.setHeading(convertHeading(heading));
    myTurtleView.setRotate(convertHeading(heading));
  }

  private double convertHeading(double heading) {
    return (90 - heading);
  }

  private double convertXCoordinate(double x) {
    return x * this.getWidth() / DEFAULT_GRID_WIDTH;
  }

  private double convertYCoordinate(double y) {
    return -1 * y * this.getHeight() / DEFAULT_GRID_HEIGHT;
  }

  public void setTurtleLocation(Coordinates newCoordinates) {
    myTurtleView.setXCoordinate(newCoordinates.getX());
    myTurtleView.setTranslateX(convertXCoordinate(newCoordinates.getX()));
    myTurtleView.setYCoordinate(newCoordinates.getY());
    myTurtleView.setTranslateY(convertYCoordinate(newCoordinates.getY()));
  }

  public void setTurtleVisibility(boolean visible) {
    if (visible) {
      myTurtleView.setOpacity(1);
    }
    else {
      myTurtleView.setOpacity(0);
    }
  }
}
