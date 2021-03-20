package slogo.view.controller;

import java.util.function.Consumer;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import slogo.view.LanguageConsumer;

/**
 * Clear screen control.
 *
 * @author David Li
 */
public class ClearController extends ControllerElement implements LanguageConsumer {

  private final Button myClearButton;

  public ClearController() {
    super();
    this.setId("ClearController");
    myClearButton = new Button("Clear");
    this.getChildren().addAll(myClearButton);
  }

  public void setExecuteCommandActions(Consumer<String> response) {
    myClearButton.setOnAction(e -> executeRawCommand(response, "ClearScreen"));
  }
}
