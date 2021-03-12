package slogo.model;

public class Variable {

  private final String name;
  private double value;

  public Variable(String name, double value) {
    this.name = name;
    this.value = value;
  }

  public Variable(Variable other) {
    this.name = other.getName();
    this.value = other.getValue();
  }

  public String getName() {
    return name;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return name.substring(1) + " = " + value; // Remove colon
  }
}
