package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;

import java.awt.*;

public class ChangePuzzleView implements FXComponent {
  private final ClassicMvcController controller;

  public ChangePuzzleView(ClassicMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox layout = new HBox();

    // previous puzzle button
    Button previous = new Button("previous");
    previous.setOnAction(
        (ActionEvent event) -> {
          controller.clickPrevPuzzle();
        });
    previous.setAlignment(Pos.BOTTOM_LEFT);
    layout.getChildren().add(previous);

    // random puzzle button
    Button random = new Button("random");
    random.setOnAction(
        (ActionEvent e) -> {
          controller.clickRandPuzzle();
        });
    random.setAlignment(Pos.BOTTOM_CENTER);
    layout.getChildren().add(random);

    // next puzzle button
    Button next = new Button("next");
    next.setOnAction(
        (ActionEvent e) -> {
          controller.clickNextPuzzle();
        });
    next.setAlignment(Pos.BOTTOM_RIGHT);
    layout.getChildren().add(next);
    layout.setAlignment(Pos.CENTER);
    return layout;
  }
}
