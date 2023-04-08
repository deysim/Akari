package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.ModelObserver;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View implements FXComponent, ModelObserver {
  private final ClassicMvcController controller;
  private Stage stage;

  public View(ClassicMvcController controller, Stage stage) {
    this.controller = controller;
    this.stage = stage;
  }

  @Override
  public Parent render() {
    VBox layout = new VBox();

    // logo
    Logo logo = new Logo(controller);
    layout.getChildren().add(logo.render());

    // status view
    StatusView statview = new StatusView(controller);
    layout.getChildren().add(statview.render());

    // puzzle view
    PuzzleView puzzle = new PuzzleView(controller);
    layout.getChildren().add(puzzle.render());

    // reset view
    ResetView reset = new ResetView(controller);
    layout.getChildren().add(reset.render());

    // change puzzle view
    ChangePuzzleView changePuzzle = new ChangePuzzleView(controller);
    layout.getChildren().add(changePuzzle.render());

    return layout;
  }

  public void update(Model model) {
    Scene scene = new Scene(render());
    scene.getStylesheets().add("main.css");
    stage.setScene(scene);
    stage.sizeToScene();
  }
}
