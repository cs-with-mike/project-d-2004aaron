/**
 * Westmont College Spring 2026
 * CS 030 Project D
 *
 * @author Assistant Professor Mike Ryu mryu@westmont.edu
 * @author Aaron Wu aawu@westmont.edu
 */

package edu.westmont.cs030.maze;

import java.util.ArrayList;

/**
 * Represents a maze as a 2D array of Cells.
 */
public class Maze {

  /**
   * 2D array of Cells to represent this maze.
   */
  public final Cell[][] cells;

  /**
   * Initializes the maze given the dimensions of the maze in number of rows and columns.
   *
   * @param numRows number of rows this maze should have
   * @param numCols number of columns this maze should have
   * @throws IllegalArgumentException if numRows or numCols is not positive
   */
  public Maze(int numRows, int numCols) {
    if (numRows <= 0 || numCols <= 0) {
      throw new IllegalArgumentException("Maze dimensions must be positive.");
    }
    this.cells = new Cell[numRows][numCols];
    initialize();
  }

  /**
   * Resets every cell in this maze to a new Cell configured as a wall.
   */
  public void initialize() {
    for (int r = 0; r < numRows(); r++) {
      for (int c = 0; c < numCols(); c++) {
        cells[r][c] = new Cell(r, c);
      }
    }
  }

  /**
   * Helper function that returns the number of rows in this maze.
   *
   * @return number of rows in this maze
   */
  public int numRows() {
    return cells.length;
  }

  /**
   * Helper function that returns the number of columns in this maze.
   *
   * @return number of columns in this maze
   */
  public int numCols() {
    return cells[0].length;
  }

  /**
   * Given a Cell, returns a neighboring Cell in the given Direction.
   *
   * @param cell Cell whose neighbor in the given Direction is to be retrieved
   * @param dir  Direction of the neighbor from the perspective of the Cell given
   * @return the neighboring Cell if one exists, null if no such neighbor exists in this maze
   */
  public Cell getNeighbor(Cell cell, Direction dir) {
    try {
      int nr = cell.getNeighborRowIndex(dir, numRows());
      int nc = cell.getNeighborColIndex(dir, numCols());
      return cells[nr][nc];
    } catch (IndexOutOfBoundsException e) {
      return null;
    }
  }

  /**
   * Given a Cell, returns an ArrayList of Cell consisting of its neighbors
   * in all Directions, as long as the neighbor(s) exist within the bounds of this maze.
   *
   * @param cell the Cell whose neighbors are to be retrieved
   * @return ArrayList of neighboring cells to the Cell given in the parameter
   */
  public ArrayList<Cell> getNeighbors(Cell cell) {
    ArrayList<Cell> neighbors = new ArrayList<>();
    for (Direction dir : Direction.values()) {
      Cell neighbor = getNeighbor(cell, dir);
      if (neighbor != null) {
        neighbors.add(neighbor);
      }
    }
    return neighbors;
  }

  /**
   * Given two neighboring Cells, sets the Cell that is located between the neighbors as a path, thus connecting the two neighboring Cells together.
   *
   * @param origin   one of the two neighboring Cells
   * @param neighbor another one of the two neighboring Cells
   */
  public void connectNeighbors(Cell origin, Cell neighbor) {
    int betweenR = (origin.rowIndex() + neighbor.rowIndex()) / 2;
    int betweenC = (origin.colIndex() + neighbor.colIndex()) / 2;
    cells[betweenR][betweenC].setPath(true);
  }

  /**
   * String representation of this maze to be used in rendering the maze.
   *
   * @return string representation of this maze
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();

    for (int c = 0; c < numCols() + 2; c++) {
      sb.append(Cell.WALL_TEXT);
    }
    sb.append("\n");

    for (int r = 0; r < numRows(); r++) {
      sb.append(Cell.WALL_TEXT);
      for (int c = 0; c < numCols(); c++) {
        sb.append(cells[r][c].getText());
      }
      sb.append(Cell.WALL_TEXT);
      sb.append("\n");
    }

    for (int c = 0; c < numCols() + 2; c++) {
      sb.append(Cell.WALL_TEXT);
    }
    sb.append("\n");

    return sb.toString();
  }
}