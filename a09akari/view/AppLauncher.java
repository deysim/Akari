package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.controller.ClassicMvcControllerImpl;
import com.comp301.a09akari.model.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    // TODO: Create your Model, View, and Controller instances and launch your GUI
    int[][] PUZZLE_01 = {
      {6, 6, 6, 6, 1, 6, 6},
      {6, 6, 6, 5, 6, 6, 6},
      {0, 6, 6, 6, 6, 6, 6},
      {6, 5, 6, 6, 6, 4, 6},
      {6, 6, 6, 6, 6, 6, 5},
      {6, 6, 6, 2, 6, 6, 6},
      {6, 6, 5, 6, 6, 6, 6},
    };

    int[][] PUZZLE_02 = {
      {5, 6, 6, 5, 6, 6, 6, 6, 6, 5},
      {6, 6, 6, 6, 6, 6, 6, 5, 6, 6},
      {6, 3, 6, 6, 6, 6, 0, 6, 6, 6},
      {6, 6, 2, 6, 6, 5, 6, 6, 6, 1},
      {6, 6, 6, 1, 0, 5, 6, 6, 6, 6},
      {6, 6, 6, 6, 1, 5, 5, 6, 6, 6},
      {5, 6, 6, 6, 2, 6, 6, 2, 6, 6},
      {6, 6, 6, 5, 6, 6, 6, 6, 5, 6},
      {6, 6, 1, 6, 6, 6, 6, 6, 6, 6},
      {0, 6, 6, 6, 6, 6, 1, 6, 6, 0},
    };

    int[][] PUZZLE_03 = {
      {6, 6, 5, 6, 6, 6, 6},
      {6, 5, 6, 6, 6, 4, 6},
      {6, 6, 6, 6, 6, 6, 5},
      {6, 6, 6, 6, 6, 6, 6},
      {3, 6, 6, 6, 6, 6, 6},
      {6, 2, 6, 6, 6, 5, 6},
      {6, 6, 6, 6, 0, 6, 6},
    };

    int[][] PUZZLE_04 = {
      {6, 1, 6, 6, 6, 6, 5, 6, 6, 6},
      {6, 6, 6, 6, 6, 6, 6, 6, 6, 5},
      {6, 6, 5, 5, 6, 6, 6, 2, 6, 6},
      {2, 6, 6, 5, 6, 6, 1, 5, 6, 6},
      {6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
      {6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
      {6, 6, 5, 2, 6, 6, 0, 6, 6, 1},
      {6, 6, 2, 6, 6, 6, 5, 1, 6, 6},
      {2, 6, 6, 6, 6, 6, 6, 6, 6, 6},
      {6, 6, 6, 5, 6, 6, 6, 6, 5, 6},
    };

    int[][] PUZZLE_05 = {
      {6, 6, 5, 6, 6, 6},
      {6, 5, 6, 6, 6, 3},
      {6, 6, 6, 6, 6, 6},
      {6, 6, 6, 6, 6, 6},
      {3, 6, 6, 6, 6, 6},
      {6, 2, 6, 6, 6, 6},
      {6, 6, 6, 6, 0, 6},
    };

    Puzzle a = new PuzzleImpl(PUZZLE_01);
    Puzzle b = new PuzzleImpl(PUZZLE_02);
    Puzzle c = new PuzzleImpl(PUZZLE_03);
    Puzzle d = new PuzzleImpl(PUZZLE_04);
    Puzzle e = new PuzzleImpl(PUZZLE_05);

    PuzzleLibrary library = new PuzzleLibraryImpl();
    library.addPuzzle(a);
    library.addPuzzle(b);
    library.addPuzzle(c);
    library.addPuzzle(d);
    library.addPuzzle(e);

    Model model = new ModelImpl(library);

    ClassicMvcController controller = new ClassicMvcControllerImpl(model);

    View view = new View(controller, stage);

    Scene scene = new Scene(view.render());
    scene.getStylesheets().add("main.css");
    stage.setScene(scene);

    model.addObserver(view);

    stage.show();
  }
}
