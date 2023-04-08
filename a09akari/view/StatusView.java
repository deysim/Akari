package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;

import java.awt.*;

public class StatusView implements FXComponent {
  private final ClassicMvcController controller;

  public StatusView(ClassicMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    StackPane layout = new StackPane();
    layout.getStyleClass().add("status-view");

    if (controller.isSolved()) {
      Label win = new Label("YOU SOLVED THE PUZZLE");
      win.getStyleClass().add("status");
      layout.getChildren().add(win);
      return layout;
    } else {
      Label lose = new Label("THERE ARE STILL SOME MISTAKES");
      lose.getStyleClass().add("status");
      layout.getChildren().add(lose);
      return layout;
    }
  }
}
