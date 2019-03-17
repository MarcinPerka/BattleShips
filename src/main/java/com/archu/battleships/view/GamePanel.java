package main.java.com.archu.battleships.view;

import main.java.com.archu.battleships.model.Computer;
import main.java.com.archu.battleships.model.Player;
import main.java.com.archu.battleships.model.Point;
import main.java.com.archu.battleships.model.Ship;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GamePanel extends JPanel implements ActionListener {

    private JPanel playerButtonsPanel, answerButtonsPanel, infoPanel;
    private JButton[][] playerButtons, answerButtons;
    private Player player;
    private Computer computer;
    private JLabel info, score, statusComputer, statusPlayer;
    private int computerScore, playerScore;
    private Timer timer;
    private GameFrame gameFrame;

    public GamePanel(int computerScore, int playerScore) {
        gameFrame = new GameFrame();
        gameFrame.add(this);

        player = new Player();
        computer = new Computer();
        player.setGameOpponentGrid(computer.getGameGrid());
        computer.setGameOpponentGrid(player.getGameGrid());

        this.computerScore = computerScore;
        this.playerScore = playerScore;
        setLayout(new BorderLayout());

        playerButtonsPanel = new JPanel();
        answerButtonsPanel = new JPanel();
        infoPanel = new JPanel();

        playerButtons = new JButton[10][10];
        answerButtons = new JButton[10][10];

        playerButtonsPanel.setLayout(new GridLayout(11, 11));
        answerButtonsPanel.setLayout(new GridLayout(11, 11));

        info = new JLabel("<html><div style='text-align: center;'>Witaj w grze Statki!!!<br>Rozpocznij grę wykonując ruch.</div></html>");
        score = new JLabel("<html><div style='text-align: center;'>Komputer " + computerScore + " : " + playerScore + " Gracz</div></html>");
        statusComputer = new JLabel("<html><div style='text-align: center;'>Ruch komputera</div></html>");
        statusPlayer = new JLabel("<html><div style='text-align: center;'>Ruch gracza</div></html>");

        infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10));
        this.add(infoPanel, BorderLayout.CENTER);
        this.add(playerButtonsPanel, BorderLayout.WEST);
        this.add(answerButtonsPanel, BorderLayout.EAST);

        infoPanel.add(info);
        infoPanel.add(score);
        infoPanel.add(statusComputer);
        infoPanel.add(statusPlayer);

        initButtons();
        addIconsToShips();
    }

    public void initButtons() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                playerButtons[i][j] = new JButton();
                playerButtons[i][j].setActionCommand("" + i + j);
                answerButtons[i][j] = new JButton();
                answerButtons[i][j].setActionCommand("" + i + j);
                playerButtons[i][j].setPreferredSize(new Dimension(30, 30));
                answerButtons[i][j].setPreferredSize(new Dimension(30, 30));
                answerButtons[i][j].addActionListener(this);
                playerButtonsPanel.add(playerButtons[i][j]);
                answerButtonsPanel.add(answerButtons[i][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pressed = (JButton) e.getSource();
        pressed.removeActionListener(this);
        int x = Integer.parseInt(pressed.getActionCommand().substring(0, 1));
        int y = Integer.parseInt(pressed.getActionCommand().substring(1, 2));
        processPlayerRound(x, y);
    }


    public void processPlayerRound(int x, int y) {
        Image fire = null;
        Image water = null;
        try {
            fire = ImageIO.read(getClass().getResource("/main/resources/fire.png"));
            water = ImageIO.read(getClass().getResource("/main/resources/water.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Ship hitShip = computer.getHit(new Point(x, y));
        if (hitShip instanceof Ship) {
            player.processHit(true, x, y);
            if (hitShip.isSunk()) {
                destroyComputerShip(hitShip);
                statusPlayer.setText("Trafiłes i zatopiłeś statek");
            } else {
                answerButtons[x][y].setIcon(resizeIcon(new ImageIcon(fire)));
                statusPlayer.setText("Trafiłes statek");
            }
        } else {
            player.processHit(false, x, y);
            answerButtons[x][y].setIcon(resizeIcon(new ImageIcon(water)));
            statusPlayer.setText("Spudłowałeś");
        }

        if (computer.hasShips()) {
            processComputerRound();
        } else {
            JOptionPane.showMessageDialog(this, "Wygrałeś, gratulacje !!!");
            PlayAgainPanel playAgainPanel = new PlayAgainPanel(computerScore, ++playerScore);
            gameFrame.dispose();

        }

    }

    public void processComputerRound() {
        Image fire = null;
        Image water = null;
        try {
            fire = ImageIO.read(getClass().getResource("/main/resources/fire.png"));
            water = ImageIO.read(getClass().getResource("/main/resources/water.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Point point = computer.randomShot();
        int x = point.getRow();
        int y = point.getColumn();
        Ship hitShip = player.getHit(point);

        if (hitShip instanceof Ship) {
            computer.processHit(true, x, y);
            playerButtons[x][y].setIcon(resizeIcon(new ImageIcon(fire)));
            if (hitShip.isSunk()) {
                destroyPlayerShip(hitShip);
                statusComputer.setText("Komputer trafił i zatopił Twój statek");
            } else {
                statusComputer.setText("Komputer trafił Twój statek");
            }
            if (!player.hasShips()) {
                JOptionPane.showMessageDialog(this, "Przegrałeś.");//nie tutaj
                PlayAgainPanel playAgainPanel = new PlayAgainPanel(++computerScore, playerScore);
                gameFrame.dispose();
            }
        } else {
            computer.processHit(true, x, y);
            playerButtons[x][y].setIcon(resizeIcon(new ImageIcon(water)));
            statusComputer.setText("Komputer spudłował");
        }
    }

    public void destroyComputerShip(Ship ship) {
        for (Point p : ship.getCoordinates()) {
            int x = p.getRow();
            int y = p.getColumn();
            try {
                Image sunk = ImageIO.read(getClass().getResource("/main/resources/sunk.png"));
                answerButtons[x][y].setIcon(resizeIcon(new ImageIcon(sunk)));
            } catch (Exception ex) {
                ex.printStackTrace();;
            }
        }
    }

    public void destroyPlayerShip(Ship ship) {
        for (Point p : ship.getCoordinates()) {
            int x = p.getRow();
            int y = p.getColumn();
            try {
                Image sunk = ImageIO.read(getClass().getResource("/main/resources/sunk.png"));
                playerButtons[x][y].setIcon(resizeIcon(new ImageIcon(sunk)));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void addIconsToShips() {
        for (Ship ship : player.getShips()) {
            for (Point point : ship.getCoordinates()) {
                try {
                    Image battleShip = ImageIO.read(getClass().getResource("/main/resources/battleship.png"));
                    playerButtons[point.getRow()][point.getColumn()].setIcon(resizeIcon(new ImageIcon(battleShip)));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private static Icon resizeIcon(ImageIcon icon) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}

