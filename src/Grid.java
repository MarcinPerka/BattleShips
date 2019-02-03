import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid {


    private int[][] grid;

    public Grid() {
        grid = new int[10][10];
    }

    public void add(Ship ship) {
        Random random = new Random();
        boolean isPlaced = true;
        while (isPlaced) {
            int row = random.nextInt(10);
            int col = random.nextInt(10);
            boolean direction = random.nextBoolean();
            if (direction) {
                if (isPossibleToPlaceVertically(row, col, ship)) {
                    for (int i = 0; i < ship.getLength(); i++) {
                        grid[row + i][col] = 1;
                        Point point = new Point(row + i, col);
                        ship.addCoordinates(point);
                    }
                    addSpaceBetweenShipsVertically(row, col, ship.getLength());
                    isPlaced = !isPlaced;
                }
            } else {
                if (isPossibleToPlaceHorizontally(row, col, ship)) {
                    for (int i = 0; i < ship.getLength(); i++) {
                        grid[row][col + i] = 1;
                        Point point = new Point(row, col + i);
                        ship.addCoordinates(point);
                    }
                    addSpaceBetweenShipsHorizontally(row, col, ship.getLength());
                    isPlaced = !isPlaced;
                }
            }
        }
    }

    public boolean isPossibleToPlaceVertically(int row, int col, Ship ship) {
        boolean isPossibleToPlace = true;
        for (int i = 0; i < ship.getLength(); i++) {
            if (row + ship.getLength() > 10 || grid[row + i][col] != 0) {
                isPossibleToPlace = false;
            }
        }
        return isPossibleToPlace;
    }

    public boolean isPossibleToPlaceHorizontally(int row, int col, Ship ship) {
        boolean isPossibleToPlace = true;
        for (int i = 0; i < ship.getLength(); i++) {
            if (col + ship.getLength() > 10 || grid[row][col + i] != 0) {
                isPossibleToPlace = false;
            }
        }
        return isPossibleToPlace;
    }

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
