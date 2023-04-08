package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

import java.awt.*;

public class ResetView implements FXComponent {
  private final ClassicMvcController controller;

  public ResetView(ClassicMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    StackPane layout = new StackPane();
    layout.getStyleClass().add("status-view");

    Button reset = new Button("Reset");

    reset.setOnAction(
        (ActionEvent event) -> {
          controller.reset();
        });
    layout.getChildren().add(reset);

    return layout;
  }
}
