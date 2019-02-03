import javax.swing.*;
import java.awt.*;

public class PlayAgainFrame extends JFrame {


    public PlayAgainFrame() {
        this.setTitle("Battleships");
        this.setForeground(Color.WHITE);
        this.setBounds(150, 150, 150, 150);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
