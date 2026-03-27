/**
 * Westmont College Spring 2026
 * CS 030 Project D
 *
 * @author Assistant Professor Mike Ryu mryu@westmont.edu
 * @author Aaron Wu aawu@westmont.edu
 */

package edu.westmont.cs030.maze;

/**
 * Represents a cell of a maze, which may be a wall or path.
 * <br>
 * This class has three (3) private instance variables:
 * <ul>
 *   <li><code>private boolean isPath</code> indicates whether this cell is a path or a wall (mutable)</li>
 *   <li><code>private final int r</code>this cell's row index in the maze</li>
 *   <li><code>private final int c</code>this cell's column index in the maze</li>
 * </ul>
 */
public class Cell {

  /**
   * Text to render for the cell if it's a "wall."
   */
  public static final String WALL_TEXT = "██";  //

  /**
   * Text to render for the cell if it's a "path."
   */
  public static final String PATH_TEXT = "  ";

  /**
   * Indicator for whether the current cell is a path of a wall(is mutable)
   */
  private boolean isPath;

  /**
   * The cell's row index in the maze
   */
  private final int r;

  /**
   * The cell's column index in the maze
   */
  private final int c;

  /**
   * Construct a cell with the given and row and column indices regarding its location in a maze.
   *
   * @param rowIndex row index relative to the maze
   * @param colIndex column index relative to the maze
   */
  public Cell(int rowIndex, int colIndex) {
    this.r = rowIndex;
    this.c = colIndex;
    this.isPath = false;
  }

  /**
   * Getter for the value of C
   *
   * @return the column index
   */
  public int colIndex() {
    return this.c;
  }

  /**
   * Getter for value of R
   *
   * @return the row index
   */
  public int rowIndex() {
    return this.r;
  }

  /**
   * Tell if something is a path or a wall
   *
   * @return value of isPath
   */
  public boolean isPath() {
    return this.isPath;
  }

  /**
   * Change the value of isPath from wall to path and vice versa
   *
   * @param isPath
   */
  public void setPath(boolean isPath) {
    this.isPath = isPath;
  }

  /**
   * Render this cell as a portion of a maze.
   *
   * @return PATH_TEXT if this cell is a path, else WALL_TEXT
   */
  public String getText() {
    if (isPath) {
      return PATH_TEXT;
    } else {
      return WALL_TEXT;
    }
  }

  /**
   * Return the text representation of this cell for debugging (not rendering) purposes.
   *
   * @return text representation of this cell
   */
  public String toString() {
    if (isPath) {
      return String.format("Cell [%2d][%2d]: PATH", r, c);
    } else {
      return String.format("Cell [%2d][%2d]: WALL", r, c);
    }
  }

  /**
   * Get the column index for the neighboring column if it exists, defaults if the direction does not affect the column
   * return value
   *
   * @param dir      direction from the cell the column is in
   * @param colLimit limit for map size so that it is not searching for neighbors beyond the borders
   * @return index of the neighboring column in the direction provided
   * @throws IndexOutOfBoundsException
   */
  public int getNeighborColIndex(Direction dir, int colLimit)
          throws IndexOutOfBoundsException {
    switch (dir) {
      case EAST -> {
        int neighborCol = this.c + 2;
        if (neighborCol >= colLimit) {
          throw new IndexOutOfBoundsException("No eastern neighbor");
        }
        return neighborCol;
      }
      case WEST -> {
        int neighborCol = this.c - 2;
        if (neighborCol < 0) {
          throw new IndexOutOfBoundsException("No western neighbor");
        }
        return neighborCol;
      }
      default -> {
        return this.c;
      }
    }
  }

  /**
   * Get the row index for the neighboring row if it exists, defaults if the direction does not affect the row
   * return value
   *
   * @param dir      direction from the cell the row is in
   * @param rowLimit limit for map size so that it is not searching for neighbors beyond the borders
   * @return index of the neighboring row in the direction provided
   * @throws IndexOutOfBoundsException
   */
  public int getNeighborRowIndex(Direction dir, int rowLimit)
          throws IndexOutOfBoundsException {
    switch (dir) {
      case SOUTH -> {
        int neighborRow = this.r + 2;
        if (neighborRow >= rowLimit) {  // exclusive upper bound
          throw new IndexOutOfBoundsException(
                  "No southern neighbor: row " + neighborRow + " out of bounds for limit " + rowLimit);
        }
        return neighborRow;
      }
      case NORTH -> {
        int neighborRow = this.r - 2;
        if (neighborRow < 0) {
          throw new IndexOutOfBoundsException(
                  "No northern neighbor: row " + neighborRow + " is out of bounds");
        }
        return neighborRow;
      }
      default -> {
        return this.r;
      }
    }
  }

}
