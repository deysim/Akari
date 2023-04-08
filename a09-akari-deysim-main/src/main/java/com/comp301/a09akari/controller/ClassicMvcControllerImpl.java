package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.Puzzle;

import java.util.Random;

public class ClassicMvcControllerImpl implements ClassicMvcController {
  private Model model;

  public ClassicMvcControllerImpl(Model model) {
    this.model = model;
  }

  public void clickNextPuzzle() {
    int index = model.getActivePuzzleIndex();
    int size = model.getPuzzleLibrarySize();
    if (index != size - 1) {
      model.setActivePuzzleIndex(index + 1);
    }
  }

  public void clickPrevPuzzle() {
    int index = model.getActivePuzzleIndex();

    if (index > 0) {
      model.setActivePuzzleIndex(index - 1);
    }
  }

  public void clickRandPuzzle() {
    Random random = new Random();
    int size = model.getPuzzleLibrarySize();
    int min = 0;
    int x = random.nextInt(size);
    model.setActivePuzzleIndex(x);
  }

  public void reset() {
    model.resetPuzzle();
  }

  public int getActivePuzzleIndex() {
    return model.getActivePuzzleIndex();
  }

  public int getPuzzleLibrarySize() {
    return model.getPuzzleLibrarySize();
  }

  public Puzzle getActivePuzzle() {
    return model.getActivePuzzle();
  }

  public boolean isSolved() {
    return model.isSolved();
  }

  public boolean isLit(int r, int c) {
    return model.isLit(r, c);
  }

  public boolean isLamp(int r, int c) {
    return model.isLamp(r, c);
  }

  public boolean isLampIllegal(int r, int c) {
    return model.isLampIllegal(r, c);
  }

  public boolean isClueSatisfied(int r, int c) {
    return model.isClueSatisfied(r, c);
  }

  @Override
  public void addLamp(int r, int c) {
    model.addLamp(r, c);
  }

  @Override
  public void removeLamp(int r, int c) {
    model.removeLamp(r, c);
  }

  public int getClue(int r, int c) {
    return model.getActivePuzzle().getClue(r, c);
  }
}
