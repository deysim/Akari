package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.Puzzle;

public interface ClassicMvcController {
  /** Handles the click action to go to the next puzzle */
  void clickNextPuzzle();

  /** Handles the click action to go to the previous puzzle */
  void clickPrevPuzzle();

  /** Handles the click action to go to a random puzzle */
  void clickRandPuzzle();

  /** Handles the click action to reset the currently active puzzle */
  void reset();

  /** Handles the click event on the cell at row r, column c */
  int getActivePuzzleIndex();

  int getPuzzleLibrarySize();

  Puzzle getActivePuzzle();

  boolean isSolved();

  boolean isLit(int r, int c);

  boolean isLamp(int r, int c);

  boolean isLampIllegal(int r, int c);

  boolean isClueSatisfied(int r, int c);

  void addLamp(int r, int c);

  void removeLamp(int r, int c);

  int getClue(int r, int c);
}
