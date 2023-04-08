package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.List;
import java.awt.Point;
import java.util.Random;

public class ModelImpl implements Model {
  private PuzzleLibrary library;
  private int activeLibraryIndex;
  private ArrayList<Point> lampPositions;
  private List<ModelObserver> observers;

  public ModelImpl(PuzzleLibrary library) {
    if (library == null) {
      throw new IllegalArgumentException();
    }
    this.library = library;
    activeLibraryIndex = 0;
    observers = new ArrayList<>();
    lampPositions = new ArrayList<>();
  }

  public void addLamp(int r, int c) { // notify observers
    CellType a = library.getPuzzle(activeLibraryIndex).getCellType(r, c);
    if (a != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }

    Point b = new Point(r, c);
    if (!lampPositions.contains(b)) {
      lampPositions.add(b);
      notifyObservers();
    }
  }

  public void removeLamp(int r, int c) { // notify observers
    CellType a = library.getPuzzle(activeLibraryIndex).getCellType(r, c);
    if (a != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }

    Point b = new Point(r, c);

    if (lampPositions.contains(b)) {
      lampPositions.remove(b);
      notifyObservers();
    }
  }

  public boolean isLit(int r, int c) {
    Point b = new Point(r, c);

    if (library.getPuzzle(activeLibraryIndex).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    } else if (lampPositions.contains(b)) {
      return true;
    }

    for (Point pos : lampPositions) {
      if (pos.getX() == r && pos.getY() < c) { // checking horizontally on right
        int wrong = 0;
        for (int i = c; i > pos.getY(); i--) {
          if (library.getPuzzle(activeLibraryIndex).getCellType(r, i) == CellType.CLUE
              || library.getPuzzle(activeLibraryIndex).getCellType(r, i) == CellType.WALL) {
            wrong++;
          }
        }
        if (wrong == 0) {
          return true;
        }

      } else if (pos.getX() == r && pos.getY() > c) { // checking horizontally left
        int wrong = 0;
        for (int i = c; i < pos.getY(); i++) {
          if (library.getPuzzle(activeLibraryIndex).getCellType(r, i) == CellType.CLUE
              || library.getPuzzle(activeLibraryIndex).getCellType(r, i) == CellType.WALL) {
            wrong++;
          }
        }
        if (wrong == 0) {
          return true;
        }
      } else if (pos.getY() == c && pos.getX() < r) { // verticlly bottom
        int wrong = 0;
        for (int i = r; i > pos.getX(); i--) {
          if (library.getPuzzle(activeLibraryIndex).getCellType(i, c) == CellType.CLUE
              || library.getPuzzle(activeLibraryIndex).getCellType(i, c) == CellType.WALL) {
            wrong++;
          }
        }
        if (wrong == 0) {
          return true;
        }
      } else if (pos.getY() == c && pos.getX() > r) { // vertically top point(r,c)
        int wrong = 0;
        for (int i = r; i < pos.getX(); i++) {
          if (library.getPuzzle(activeLibraryIndex).getCellType(i, c) == CellType.CLUE
              || library.getPuzzle(activeLibraryIndex).getCellType(i, c) == CellType.WALL) {
            wrong++;
          }
        }
        if (wrong == 0) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean isLamp(int r, int c) {
    if (library.getPuzzle(activeLibraryIndex).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }

    Point b = new Point(r, c);

    if (lampPositions.contains(b)) {
      return true;
    } else {
      return false;
    }
  }

  public boolean isLampIllegal(int r, int c) {
    if (r < 0
        || r >= library.getPuzzle(activeLibraryIndex).getHeight()
        || c < 0
        || c >= library.getPuzzle(activeLibraryIndex).getWidth()) {
      throw new IndexOutOfBoundsException();
    }

    Point a = new Point(r, c);
    if (!isLamp(r, c)) {
      throw new IllegalArgumentException();
    }

    for (Point pos : lampPositions) {
      if (pos.getX() == r && pos.getY() < c) { // checking horizontally on right
        int y = 0;
        for (int i = c; i > pos.getY(); i--) {
          if (library.getPuzzle(activeLibraryIndex).getCellType(r, i) == CellType.CLUE
              || library.getPuzzle(activeLibraryIndex).getCellType(r, i) == CellType.WALL) {
            y++;
          }
        }
        if (!(y > 0)) {
          return true;
        }
      } else if (pos.getX() == r && pos.getY() > c) { // checking horizontally left
        int y = 0;
        for (int i = c; i < pos.getY(); i++) {
          if (library.getPuzzle(activeLibraryIndex).getCellType(r, i) == CellType.CLUE
              || library.getPuzzle(activeLibraryIndex).getCellType(r, i) == CellType.WALL) {
            y++;
          }
        }
        if (!(y > 0)) {
          return true;
        }
      } else if (pos.getY() == c && pos.getX() < r) { // verticlly bottom
        int y = 0;
        for (int i = r; i > pos.getX(); i--) {
          if (library.getPuzzle(activeLibraryIndex).getCellType(i, c) == CellType.CLUE
              || library.getPuzzle(activeLibraryIndex).getCellType(i, c) == CellType.WALL) {
            y++;
          }
        }
        if (!(y > 0)) {
          return true;
        }
      } else if (pos.getY() == c && pos.getX() > r) { // vertically top
        int y = 0;
        for (int i = r; i < pos.getX(); i++) {
          if (library.getPuzzle(activeLibraryIndex).getCellType(i, c) == CellType.CLUE
              || library.getPuzzle(activeLibraryIndex).getCellType(i, c) == CellType.WALL) {
            y++;
          }
        }
        if (!(y > 0)) {
          return true;
        }
      }
    }
    return false;
  }

  public Puzzle getActivePuzzle() {
    return library.getPuzzle(activeLibraryIndex);
  }

  public int getActivePuzzleIndex() {
    return activeLibraryIndex;
  }

  public void setActivePuzzleIndex(int index) { // notify observers
    if (index < 0 || index >= library.size()) {
      throw new IndexOutOfBoundsException();
    }

    activeLibraryIndex = index;
    resetPuzzle();
  }

  public int getPuzzleLibrarySize() {
    return library.size();
  }

  public void resetPuzzle() { // notify observers
    lampPositions = new ArrayList<>();
    notifyObservers();
  }

  public boolean isSolved() {
    Puzzle b = getActivePuzzle();
    for (int r = 0; r < getActivePuzzle().getHeight(); r++) {
      for (int c = 0; c < getActivePuzzle().getWidth(); c++) {
        CellType a = b.getCellType(r, c);
        if (a == CellType.CORRIDOR && !isLit(r, c)) {
          return false;
        } else if (a == CellType.CORRIDOR && isLamp(r, c) && isLampIllegal(r, c)) {
          return false;
        } else if (a == CellType.CLUE && !isClueSatisfied(r, c)) {
          return false;
        }
      }
    }
    return true;
  }

  public boolean isClueSatisfied(int r, int c) {
    if (library.getPuzzle(activeLibraryIndex).getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }

    int numLights = 0;
    if (((r + 1) < (library.getPuzzle(activeLibraryIndex).getHeight()))) {
      Point one = new Point(r + 1, c);
      if (lampPositions.contains(one)) {
        numLights++;
      }
    }
    if ((r - 1) >= 0) {
      Point one = new Point(r - 1, c);
      if (lampPositions.contains(one)) {
        numLights++;
      }
    }

    if (((c + 1) < (library.getPuzzle(activeLibraryIndex).getWidth()))) {
      Point one = new Point(r, c + 1);
      if (lampPositions.contains(one)) {
        numLights++;
      }
    }

    if ((c - 1) >= 0) {
      Point one = new Point(r, c - 1);
      if (lampPositions.contains(one)) {
        numLights++;
      }
    }

    if (numLights == library.getPuzzle(activeLibraryIndex).getClue(r, c)) {
      return true;
    } else {
      return false;
    }
  }

  public void addObserver(ModelObserver observer) {
    observers.add(observer);
  }

  public void removeObserver(ModelObserver observer) {
    observers.remove(observer);
  }

  private void notifyObservers() {
    for (ModelObserver o : observers) {
      o.update(this);
    }
  }
  /*
  public static void main(String[] args) {
    int[][] PUZZLE_05 = {{6}};

    PuzzleLibrary library = new PuzzleLibraryImpl();
    Puzzle one = new PuzzleImpl(PUZZLE_05);
    library.addPuzzle(one);

    Model b = new ModelImpl(library);

    b.addLamp(0, 0);

    System.out.println(b.isSolved());
    }

     */
}
