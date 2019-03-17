package main.java.com.archu.battleships;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;

public class PlayAgainPanel extends JPanel implements ActionListener {
    private LocalDate localDate = LocalDate.now();
    private int computerScore, playerScore;
    private JButton playButton, exitButton, saveButton;
    private JLabel info;
    private PlayAgainFrame playAgainFrame;

    public PlayAgainPanel(int computerScore, int playerScore) {
        this.computerScore = computerScore;
        this.playerScore = playerScore;

        playAgainFrame = new PlayAgainFrame();
        playAgainFrame.add(this);

        setLayout(new GridLayout(4, 1));
        this.setFont(new Font("Sans Serif", Font.PLAIN, 12));

        playButton = new JButton("Zagraj jeszcze raz");
        exitButton = new JButton("Wyjdź z gry");
        saveButton = new JButton("Zapisz wynik");
        info = new JLabel("<html>Komputer " + computerScore + " : " + playerScore + " Gracz</html>", SwingConstants.CENTER);

        playButton.addActionListener(this);
        exitButton.addActionListener(this);
        saveButton.addActionListener(this);

        this.add(info);
        this.add(playButton);
        this.add(saveButton);
        this.add(exitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            GamePanel gamePanel = new GamePanel(computerScore, playerScore);
            playAgainFrame.dispose();
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        } else if (e.getSource() == saveButton) {
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(new File("C:\\Users\\Archu\\IdeaProjects\\main.java.com.archu.battleships.BattleShips\\src\\main.resources\\score.txt"), true);
                fileWriter.write("Komputer " + computerScore + " : " + playerScore + " Gracz" + " " + localDate);
                fileWriter.write(System.getProperty("line.separator"));
                fileWriter.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
