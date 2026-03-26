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
            for (int c = 0; c < numCols(); c++) {
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
        try {
            int nr = cell.getNeighborRowIndex(dir, numRows());
            int nc = cell.getNeighborColIndex(dir, numCols());
            return cells[nr][nc];
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

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

    public void connectNeighbors(Cell origin, Cell neighbor) {
        int betweenR = (origin.rowIndex() + neighbor.rowIndex()) / 2;
        int betweenC = (origin.colIndex() + neighbor.colIndex()) / 2;
        cells[betweenR][betweenC].setPath(true);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        // Top boundary
        for (int c = 0; c < numCols() + 2; c++) {
            sb.append(Cell.WALL_TEXT);
        }
        sb.append("\n");
        // Each row
        for (int r = 0; r < numRows(); r++) {
            sb.append(Cell.WALL_TEXT); // left boundary
            for (int c = 0; c < numCols(); c++) {
                sb.append(cells[r][c].getText());
            }
            sb.append(Cell.WALL_TEXT); // right boundary
            sb.append("\n");
        }
        // Bottom boundary
        for (int c = 0; c < numCols() + 2; c++) {
            sb.append(Cell.WALL_TEXT);
        }
        sb.append("\n");
        return sb.toString();
    }
}