package main.java.com.archu.battleships.model;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private int length;
    private int hits = 0;
    private List<Point> coordinates = new ArrayList<>();// List of points (x,y) of grid in which ship is placed

    public Ship(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public List<Point> getCoordinates() {
        return coordinates;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void getHits() {
        this.hits += 1;
    }

    public void addCoordinates(Point point) {
        coordinates.add(point);
    }

    public boolean isSunk() {
        return hits == length;

    }

}
