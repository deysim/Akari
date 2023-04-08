package com.comp301.a09akari.model;

public class PuzzleImpl implements Puzzle {
  private int width;
  private int height;
  private int[][] board;

  public PuzzleImpl(int[][] board) {
    if (board == null) {
      throw new IllegalArgumentException();
    }
    this.height = board.length; // aka rows
    this.width = board[0].length; // aka columns
    this.board = board;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public CellType getCellType(int r, int c) {
    if (r < 0 || r >= height || c < 0 || c >= width) {
      throw new IndexOutOfBoundsException();
    } else {
      int a = board[r][c];
      if (a >= 0 && a <= 4) {
        return CellType.CLUE;
      } else if (a == 5) {
        return CellType.WALL;
      } else {
        return CellType.CORRIDOR;
      }
    }
  }

  @Override
  public int getClue(int r, int c) {
    if (r < 0 || r >= height || c < 0 || c >= width) {
      throw new IndexOutOfBoundsException();
    }
    CellType a = getCellType(r, c);
    if (a != CellType.CLUE) {
      throw new IllegalArgumentException();
    }

    return board[r][c];
  }
}
