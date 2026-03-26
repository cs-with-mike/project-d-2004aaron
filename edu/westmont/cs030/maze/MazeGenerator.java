/**
 * Westmont College Spring 2026
 * CS 030 Project D
 *
 * @author Assistant Professor Mike Ryu mryu@westmont.edu
 * @author Aaron Wu aawu@westmont.edu
 */


package edu.westmont.cs030.maze;

import javax.swing.text.JTextComponent;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Separate container for generating maze pathing and animating the generation process.
 */
public class MazeGenerator {

  private final Maze maze;
  private final int r0;
  private final int c0;
  private final boolean isShuffle;

  private final JTextComponent renderTarget;
  private final int renderDelay;

  /**
   * Initializes the maze generators with parameters necessary for creating the maze and generating the paths within.
   *
   * @param mazeHeight        height of the maze in number of rows
   *                          (including rows dedicated for displaying internal walls)
   * @param mazeWidth         width of the maze in number of column
   *                          (including columns dedicated for displaying internal walls)
   * @param initRow           index of the row to begin the maze path generation from
   * @param initCol           index of the column to begin the maze path generation from
   * @param isShuffle         <code>true</code> if the path generation is randomized,
   *                          <code>false</code> if the generation should be linear/sequential
   * @param renderTarget      {@link JTextComponent} to output the rendered maze text to
   * @param renderDelayMillis milliseconds of delay to inject between each step of maze generation process
   */
  public MazeGenerator(int mazeHeight, int mazeWidth, int initRow, int initCol,
                       boolean isShuffle, JTextComponent renderTarget, int renderDelayMillis) {
    this.r0 = initRow;
    this.c0 = initCol;

    this.renderDelay = renderDelayMillis;
    this.isShuffle = isShuffle;

    int numRows = mazeHeight % 2 == 0 ? mazeHeight + 1 : mazeHeight;
    int numCols = mazeWidth % 2 == 0 ? mazeWidth + 1 : mazeWidth;

    this.maze = new Maze(numRows, numCols);
    this.renderTarget = renderTarget;
  }

  /**
   * Returns the GUI render target that was set at instantiation.
   * @return A {@link JTextComponent} if the render target was set, <code>null</code> if unset.
   */
  public JTextComponent getRenderTarget() {
    return this.renderTarget;
  }

  public void generateMaze() {
    maze.initialize();
    generateMaze(maze.cells[r0][c0]);
  }

  public void generateMaze(Cell currCell) {
    currCell.setPath(true);
    displayMaze(maze);

    ArrayList<Cell> neighbors = maze.getNeighbors(currCell);
    if (isShuffle) {
      Collections.shuffle(neighbors);
    }

    for (Cell neighbor : neighbors) {
      if (!neighbor.isPath()) {
        maze.connectNeighbors(currCell, neighbor);
        generateMaze(neighbor);
      }
    }
  }

  /**
   * Displays the given {@link Maze} at its current state to both to the GUI window and console.
   *
   * @param maze {@link Maze} to display
   */
  private void displayMaze(Maze maze) {
    try {
      Thread.sleep(this.renderDelay);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    if (this.renderTarget != null) this.renderTarget.setText(maze.toString());
    System.out.println(maze);
  }

}
