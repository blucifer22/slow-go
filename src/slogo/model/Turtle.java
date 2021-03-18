package slogo.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The Turtle is the object that commands of forward and right are put upon, and it contains the
 * fundamental variables of where the turtle is placed and facing.
 *
 * @author Harrison Huang
 * @author Cole Spector
 * @author David Li
 */
public class Turtle {

  private Coordinates coordinates;
  private Pen pen;
  private Variables vars;
  private PropertyChangeListener turtleListener;
  private boolean isVisible = true;

  /**
   * Primary constructor for Turtle, which takes initial coordinates, a pen, and a pair of listeners
   * and constructs a Turtle.
   *
   * @param coordinates The coordinate system that this Turtle will operate in
   * @param pen The initial type of Pen that this Turtle will have
   * @param turtleListener A listener that will report changes in this Turtle's location and other
   *                       properties to the View TODO: CHANGE THIS!
   * @param variablesListener A listener that will report changes in Variables TODO: CHANGE THIS TOO!
   */
  public Turtle(Coordinates coordinates, Pen pen, PropertyChangeListener turtleListener,
      PropertyChangeListener variablesListener) {
    this.coordinates = coordinates;
    this.pen = pen;
    this.vars = new Variables(variablesListener);
    this.turtleListener = turtleListener;

    turtleListener
        .propertyChange(
            new PropertyChangeEvent(this, "LOCATION", this.coordinates, this.coordinates));
    turtleListener
        .propertyChange(
            new PropertyChangeEvent(this, "HEADING", this.coordinates.getHeading(),
                this.coordinates.getHeading()));
    turtleListener
        .propertyChange(
            new PropertyChangeEvent(this, "VISIBILITY", this.isVisible, this.isVisible));
  }

  /**
   * Constructor with no listeners attached
   */
  public Turtle(Coordinates coordinates, Pen pen) {
    this(coordinates, pen, evt -> {
    }, evt -> {
    });
  }

  /**
   * Creates a new Turtle object given parameters of a starting position and heading.
   *
   * @param x       X coordinate
   * @param y       Y coordinate
   * @param heading direction turtle is facing relative to positive x-axis
   */

  public Turtle(Coordinates coordinates, double x, double y, double heading) {
    this.coordinates = coordinates;
    coordinates.setX(x);
    coordinates.setY(y);
    coordinates.setHeading(heading);
  }

  public Variables getVars() {
    return vars;
  }

  public Pen getPen() {
    return pen;
  }

  public boolean isPenActive() {
    return pen.isPenActive();
  }

  /**
   * Moves the turtle forward a certain number of pixels (or backward, for a negative number).
   *
   * @param pixels number of pixels the turtle will move forward
   */
  public void forward(double pixels) {
    double xPos = coordinates.getX();
    double yPos = coordinates.getY();
    double heading = coordinates.getHeading();

    xPos += pixels * Math.cos(Math.toRadians(heading));
    yPos += pixels * Math.sin(Math.toRadians(heading));

    setPosition(xPos, yPos);
  }

  /**
   * Turns the turtle to the right for a certain number of degrees. Notifies turtle listener of
   * heading change
   *
   * @param degrees number of degrees the turtle will move clockwise
   */
  public void right(double degrees) {

    double heading = coordinates.getHeading();

    turtleListener
        .propertyChange(new PropertyChangeEvent(this, "HEADING", heading, heading - degrees));

    setHeading(heading - degrees);
  }

  /**
   * Getter method for obtaining the x-coordinate of the turtle.
   *
   * @return double of turtle's x-coordinate
   */

  public double getX() {
    return coordinates.getX();
  }

  /**
   * Getter method for obtaining the y-coordinate of the turtle.
   *
   * @return double of turtle's y-coordinate
   */

  public double getY() {
    return coordinates.getY();
  }

  /**
   * Sets x and y coordinates of the turtle Notifies turtle listener of position change
   *
   * @param x New x-coordinate
   * @param y New y-coordinate
   */
  public void setPosition(double x, double y) {
    Coordinates prevCoordinates = new GridCoordinates(coordinates);
    coordinates.setX(x);
    coordinates.setY(y);
    turtleListener
        .propertyChange(new PropertyChangeEvent(this, "LOCATION", prevCoordinates, coordinates));
  }

  /**
   * Getter method for obtaining the heading of the turtle in degrees counterclockwise from the
   * x-axis.
   *
   * @return heading of the turtle in degrees
   */

  public double getHeading() {
    return coordinates.getHeading();
  }

  /**
   * Setter method for the heading of the turtle. Automatically recalculates to a number between 0
   * and 360 degrees.
   *
   * @param heading The new heading of the object in degrees.
   */

  public void setHeading(double heading) {
    turtleListener
        .propertyChange(new PropertyChangeEvent(this, "HEADING", coordinates, heading));
    coordinates.setHeading(heading);
  }

  /**
   * Returns if the turtle is visible on screen.
   *
   * @return boolean whether the turtle is visible
   */
  public boolean isVisible() {
    return isVisible;
  }

  /**
   * Sets the visibility of the turtle.
   *
   * @param isVisible boolean whether the turtle is visible
   */
  public void setVisible(boolean isVisible) {
    turtleListener
        .propertyChange(new PropertyChangeEvent(this, "VISIBILITY", this.isVisible, isVisible));
    this.isVisible = isVisible;
  }

  /**
   * Makes the pen inactive Notifies turtle listener of pen change
   */
  public void liftPen() {
    turtleListener.propertyChange(new PropertyChangeEvent(this, "PEN", isPenActive(), false));
    pen.liftPen();
  }

  /**
   * Makes the pen active Notifies turtle listener of pen change
   */
  public void placePen() {
    turtleListener.propertyChange(new PropertyChangeEvent(this, "PEN", isPenActive(), true));
    pen.placePen();
  }

  /**
   * Clears the screen of the Turtle's lines.
   */
  public void clearScreen() {
    turtleListener.propertyChange(new PropertyChangeEvent(this, "CLEAR", null, null));
  }
}
