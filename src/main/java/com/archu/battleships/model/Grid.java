package main.java.com.archu.battleships.model;

import java.util.Random;

public class Grid {

    private int[][] grid;

    public Grid() {
        grid = new int[10][10];
    }

    /**
     * @param ship - Ship which will be added
     */
    public void add(Ship ship) {
        Random random = new Random();
        boolean isPlaced = true;
        while (isPlaced) {// Loop until ship will be placed.
            int row = random.nextInt(10);//random points of row and column.
            int col = random.nextInt(10);
            boolean direction = random.nextBoolean(); // random direction of ship
            if (direction) { // Checking if it is possible to place ship; true - vertically, false - horizontally
                if (isPossibleToPlaceVertically(row, col, ship)) { // true - add ship
                    for (int i = 0; i < ship.getLength(); i++) {
                        grid[row + i][col] = 1;
                        Point point = new Point(row + i, col);
                        ship.addCoordinates(point);
                    }
                    addSpaceBetweenShipsVertically(row, col, ship.getLength()); // add free space around ship
                    isPlaced = !isPlaced;
                }
            } else {
                if (isPossibleToPlaceHorizontally(row, col, ship)) { // true - add ship
                    for (int i = 0; i < ship.getLength(); i++) {
                        grid[row][col + i] = 1;
                        Point point = new Point(row, col + i);
                        ship.addCoordinates(point);
                    }
                    addSpaceBetweenShipsHorizontally(row, col, ship.getLength()); // add free space around ship
                    isPlaced = !isPlaced;
                }
            }
        }
    }

    /**
     * @param row  - row of grid
     * @param col  - column of grid
     * @param ship - ship to place
     * @return False in otherwise.
     */
    public boolean isPossibleToPlaceVertically(int row, int col, Ship ship) {
        boolean isPossibleToPlace = true;
        for (int i = 0; i < ship.getLength(); i++) {
            if (row + ship.getLength() > 10 || grid[row + i][col] != 0) {
                isPossibleToPlace = false;
            }
        }
        return isPossibleToPlace;
    }

    /**
     * @param row  - row of grid
     * @param col  - column of grid
     * @param ship - ship to place
     * @return False in otherwise.
     */
    public boolean isPossibleToPlaceHorizontally(int row, int col, Ship ship) {
        boolean isPossibleToPlace = true;
        for (int i = 0; i < ship.getLength(); i++) {
            if (col + ship.getLength() > 10 || grid[row][col + i] != 0) {
                isPossibleToPlace = false;
            }
        }
        return isPossibleToPlace;
    }

    /**
     * @param row - row of grid
     * @param col - column of grid
     * @param len - length of ship
     *            Method adds vertically space between ships. Ships can not be placed side by side. The number "2" is intended to express free space between ships.
     */
    public void addSpaceBetweenShipsVertically(int row, int col, int len) {
        for (int i = 0; i < len; i++) {
            if (col + 1 < 10) {
                grid[row + i][col + 1] = 2;
                if (row - 1 >= 0) {
                    grid[row - 1][col + 1] = 2;
                }
                if (row + len < 10) {
                    grid[row + len][col + 1] = 2;
                }
            }
            if (col - 1 >= 0) {
                grid[row + i][col - 1] = 2;
                if (row - 1 >= 0) {
                    grid[row - 1][col - 1] = 2;
                }
                if (row + len < 10) {
                    grid[row + len][col - 1] = 2;
                }
            }
            if (row - 1 >= 0) grid[row - 1][col] = 2;
            if (row + len < 10) grid[row + len][col] = 2;
        }
    }

    /**
     * @param row - row of grid
     * @param col - column of grid
     * @param len - length of ship
     *            Method adds horizontally space between ships. Ships can not be placed side by side. The number "2" is intended to express free space between ships.
     */
    public void addSpaceBetweenShipsHorizontally(int row, int col, int len) {
        for (int i = 0; i < len; i++) {
            if (row + 1 < 10) {
                grid[row + 1][col + i] = 2;
                if (col - 1 >= 0) {
                    grid[row + 1][col - 1] = 2;
                }
                if (col + len < 10) {
                    grid[row + 1][col + len] = 2;
                }
            }
            if (row - 1 >= 0) {
                grid[row - 1][col + i] = 2;
                if (col - 1 >= 0) {
                    grid[row - 1][col - 1] = 2;
                }
                if (col + len < 10) {
                    grid[row - 1][col + len] = 2;
                }
            }
            if (col - 1 >= 0) grid[row][col - 1] = 2;
            if (col + len < 10) grid[row][col + len] = 2;
        }
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }
}
