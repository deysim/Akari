package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Puzzle;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.awt.*;

public class PuzzleView implements FXComponent {
  private final ClassicMvcController controller;

  public PuzzleView(ClassicMvcController controller) {
    this.controller = controller;
  }

  public Parent render() {
    GridPane puzzleBoard = new GridPane();
    puzzleBoard.setHgap(10);
    puzzleBoard.setVgap(10);
    puzzleBoard.getStyleClass().add("board");

    // loop through the puzzle in model using controller
    Puzzle activePuzzle = controller.getActivePuzzle();

    for (int r = 0; r < activePuzzle.getHeight(); r++) {
      for (int c = 0; c < activePuzzle.getWidth(); c++) {
        CellType a = activePuzzle.getCellType(r, c);

        if (a == CellType.CORRIDOR && controller.isLit(r, c) && !controller.isLamp(r, c)) {
          Button addLamp = new Button();

          // add button design here, just make yellow
          addLamp.getStyleClass().add("tile-61");

          int finalC = c;
          int finalR = r;
          addLamp.setOnAction(
              (ActionEvent e) -> {
                controller.addLamp(finalR, finalC);
              });
          puzzleBoard.add(addLamp, c, r);
        } else if (a == CellType.CORRIDOR
            && controller.isLit(r, c)
            && controller.isLamp(r, c)
            && !controller.isLampIllegal(r, c)) {
          Button removeLamp = new Button();

          // add button design here, just make yellow with lamp pic
          removeLamp.getStyleClass().add("tile-62");

          int finalC = c;
          int finalR = r;
          removeLamp.setOnAction(
              (ActionEvent e) -> {
                controller.removeLamp(finalR, finalC);
              });
          puzzleBoard.add(removeLamp, c, r);
        } else if (a == CellType.CORRIDOR
            && controller.isLit(r, c)
            && controller.isLamp(r, c)
            && controller.isLampIllegal(r, c)) {
          Button removeLamp = new Button();

          // add button design here, just make yellow with red lamp pic
          removeLamp.getStyleClass().add("tile-63");

          int finalC = c;
          int finalR = r;
          removeLamp.setOnAction(
              (ActionEvent e) -> {
                controller.removeLamp(finalR, finalC);
              });
          puzzleBoard.add(removeLamp, c, r);
        } else if (a == CellType.CORRIDOR && !controller.isLit(r, c)) {
          Button addLamp = new Button();

          // add button design here, just make white
          addLamp.getStyleClass().add("tile-64");

          int finalC = c;
          int finalR = r;
          addLamp.setOnAction(
              (ActionEvent e) -> {
                controller.addLamp(finalR, finalC);
              });
          puzzleBoard.add(addLamp, c, r);
        } else if (a == CellType.CLUE && controller.isClueSatisfied(r, c)) {
          Label label = new Label("" + controller.getClue(r, c));

          // add satisfied clue design here
          label.getStyleClass().add("tile-0");

          puzzleBoard.add(label, c, r);
        } else if (a == CellType.CLUE && !controller.isClueSatisfied(r, c)) {
          Label label = new Label("" + controller.getClue(r, c));

          // add unsatisfied clue design here
          label.getStyleClass().add("tile-1");

          puzzleBoard.add(label, c, r);
        } else if (a == CellType.WALL) {
          Label label = new Label();

          // add black wall design here
          label.getStyleClass().add("tile-5");

          puzzleBoard.add(label, c, r);
        }
      }
    }

    return puzzleBoard;
  }
}
