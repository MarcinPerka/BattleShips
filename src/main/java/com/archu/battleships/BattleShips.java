package main.java.com.archu.battleships;

import java.awt.*;

public class BattleShips {
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {

                GamePanel gamePanel = new GamePanel(0, 0);
            }
        });
    }
}
