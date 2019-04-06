package main.java.com.archu.battleships.model;

import java.util.ArrayList;

public class Player {

    private static final int sizeOfShips[] = new int[]{4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
    private static final int numberOfShips = sizeOfShips.length;
    private ArrayList<Ship> ships;
    private Grid gameGrid;
    private Grid gameOpponentGrid;
    private int score;

    public Player() {
        ships = new ArrayList<>();
        gameGrid = new Grid();
        gameOpponentGrid = new Grid();
        score = 0;

        for (int i = 0; i < numberOfShips; i++) { //init ships
            ships.add(new Ship(sizeOfShips[i]));
        }
        placeShips();
    }

    public boolean hasShips() {
        boolean hasAlive = false;
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                hasAlive = true;
            }
        }
        return hasAlive;
    }

    /**
     * Place in grid initialized ships .
     */
    public void placeShips() {
        for (Ship ship : ships) {
            gameGrid.add(ship);
        }
    }

    public Grid getGameGrid() {
        return gameGrid;
    }

    public void setGameGrid(Grid gameGrid) {
        this.gameGrid = gameGrid;
    }

    public Grid getGameOpponentGrid() {
        return gameOpponentGrid;
    }

    public void setGameOpponentGrid(Grid gameOpponentGrid) {
        this.gameOpponentGrid = gameOpponentGrid;
    }


    public int getScore() {
        return score;
    }

    public void setScore() {
        this.score = this.score++;
    }


    /**
     * @param p - Point which was selected by opponent to shot.
     * @return - Null or ship which was hit.
     */
    public Ship getHit(Point p) {
        Ship hitShip = null;
        data:
        for (Ship ship : ships) {
            for (Point point : ship.getCoordinates()) {
                if (p.equals(point)) {
                    hitShip = ship;
                    ship.getHits();
                    if (ship.isSunk()) {
                    }
                }

            }
        }
        return hitShip;

    }

    /**
     * @param hit - True/9 ship was hit, else False/8 missed
     * @param row - row of grid
     * @param col - column of grid
     */
    public void processHit(boolean hit, int row, int col) {
        if (hit) {
            gameOpponentGrid.getGrid()[row][col] = 9;
        } else {
            gameOpponentGrid.getGrid()[row][col] = 8;
        }

    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

}
