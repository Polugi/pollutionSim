import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame{

    GamePanel panel;
    Menu menu;
    Button startingScreen;
    ImageIcon logo = new ImageIcon("/Users/sachitt/IdeaProjects/Pollution Simulator/Images/game_icon.png");



    GameFrame(){

        panel = new GamePanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Pollution Simulator");
        this.setVisible(true);
        this.setIconImage(logo.getImage());
    }
}




