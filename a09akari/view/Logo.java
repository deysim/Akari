package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;

import java.awt.*;

public class Logo implements FXComponent {
  private final ClassicMvcController controller;

  public Logo(ClassicMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    Pane logo = new HBox();
    logo.getStyleClass().add("logo");

    // add akari name
    Pane logoContainer = new HBox();
    Label akariName = new Label("AKARI");
    akariName.getStyleClass().add("akari");
    logoContainer.getChildren().add(akariName);
    HBox.setHgrow(logoContainer, Priority.ALWAYS);
    logo.getChildren().add(logoContainer);

    // add view for which puzzle you're on
    int currentPuzzle = controller.getActivePuzzleIndex() + 1;
    int librarySize = controller.getPuzzleLibrarySize();
    Pane container = new HBox();
    Label puzzleNumber = new Label("puzzle " + currentPuzzle + " of " + librarySize);
    puzzleNumber.getStyleClass().add("puzzle-index");
    container.getChildren().add(puzzleNumber);
    logo.getChildren().add(container);

    return logo;
  }
}
