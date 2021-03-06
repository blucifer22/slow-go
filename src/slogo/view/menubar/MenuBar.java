package slogo.view.menubar;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Holds all selectors and help button at the top of the window
 *
 * @author David Li
 */
public class MenuBar extends HBox {

  private final BackgroundSelector myBackgroundSelector;
  private final GridSelector myGridSelector;
  private final TurtleSelector myTurtleSelector;
  private final PenSelector myPenSelector;
  private final LanguageSelector myLanguageSelector;
  private final FileButtons myFileButtons;
  private final HelpWindow myHelpWindow;

  /**
   * Main constructor
   */
  public MenuBar() {
    this.setId("MenuBar");
    this.getStyleClass().add("box");
    this.setAlignment(Pos.CENTER_LEFT);

    myBackgroundSelector = new BackgroundSelector();
    myGridSelector = new GridSelector();
    myTurtleSelector = new TurtleSelector();
    myPenSelector = new PenSelector();
    myLanguageSelector = new LanguageSelector();
    myHelpWindow = new HelpWindow();
    myFileButtons = new FileButtons();

    Button infoButton = new Button("?");
    infoButton.setId("InfoButton");
    infoButton.setShape(new Circle(2));
    infoButton.setOnAction(e -> openHelpWindow());

    // Used to align info button to the right
    Pane spacer = new Pane();
    HBox.setHgrow(spacer, Priority.ALWAYS);

    this.getChildren()
        .addAll(myBackgroundSelector, myGridSelector, myTurtleSelector, myPenSelector,
            myLanguageSelector, spacer, myFileButtons, infoButton);
  }

  public MenuBarSelector<Color> getBackgroundSelector() {
    return myBackgroundSelector;
  }

  public MenuBarSelector<String> getGridSelector() {
    return myGridSelector;
  }

  public MenuBarSelector<String> getTurtleSelector() {
    return myTurtleSelector;
  }

  public MenuBarSelector<Color> getPenSelector() {
    return myPenSelector;
  }

  public LanguageSelector getLanguageSelector() {
    return myLanguageSelector;
  }

  public FileButtons getFileButtons() {
    return myFileButtons;
  }

  public boolean helpWindowIsOpen() {
    return myHelpWindow.isShowing();
  }

  private void openHelpWindow() {
    myHelpWindow.show();
  }
}
