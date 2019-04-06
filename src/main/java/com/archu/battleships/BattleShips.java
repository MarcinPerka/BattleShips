package main.java.com.archu.battleships;

import main.java.com.archu.battleships.view.GamePanel;

import java.awt.*;

/**
 * @author Marcin Perka
 * @version 1.0
 */

public class BattleShips {
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {

                GamePanel gamePanel = new GamePanel(0, 0);
            }
        });
    }
}
