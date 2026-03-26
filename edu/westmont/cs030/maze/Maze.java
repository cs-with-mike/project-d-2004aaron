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
 * Represents a maze as a 2D array of {@link Cell}s.
 */
public class Maze {
    public final Cell[][] cells;

    public Maze(int numRows, int numCols) {
        this.cells = new Cell[numRows][numCols];
        initialize();
    }

    public void initialize() {
        for (int r = 0; r < numRows(); r++) {
            for (int c = 0; c < numCols(); c+) {
                cells[r][c] = new Cell(r, c);
            }
        }
    }

    public int numRows() {
        return cells.length;
    }

    public int numCols() {
        return cells[0].length;
    }

    public Cell getNeighbor(Cell cell, Direction dir) {

    }

}
