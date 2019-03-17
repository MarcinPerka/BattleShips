package main.java.com.archu.battleships;

import java.util.Random;

public class Computer extends Player implements  IComputer {

    @Override
    public Point randomShot() {
        Point point = null;
        Random random = new Random();
        boolean selectedEarly = true;
        while (selectedEarly) {
            int row = random.nextInt(10);
            int col = random.nextInt(10);
            if (getGameOpponentGrid().getGrid()[row][col] == 8 || getGameOpponentGrid().getGrid()[row][col] == 9) {
                continue;
            } else {
                point = new Point(row, col);
                selectedEarly = false;
            }
        }
        return point;
    }
}
