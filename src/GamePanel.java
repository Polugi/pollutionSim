import org.w3c.dom.css.RGBColor;
import org.w3c.dom.ls.LSOutput;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.RGBImageFilter;
import java.util.ArrayList;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GamePanel extends JPanel implements ActionListener, MouseListener {


//    GUI
    private final int PANEL_WIDTH = 1000;
    private final int PANEL_HEIGHT = 600;
    Image background = new ImageIcon("/Users/sachitt/IdeaProjects/Pollution Simulator/Images/background.png").getImage();


//    Menu
    Rectangle menu = new Rectangle(0,0,125,600);
    JButton reset;
    boolean pause = false;

//    GAME OVER
    private Rectangle gameover = new Rectangle(250,150,500+125,300);
    private boolean gameoverVis = false;





//    Health bar
    private int healthBarX = 280;
    private int healthBarWidth = 500;
    private Rectangle healthBar = new Rectangle(healthBarX,60,healthBarWidth,30);
    boolean hbvisible = true;

//    Victim
    private Image victim;
    private int victim_x = 400;
    private int victim_y = 300;
    private int victim_yv = 1;

//  Enemy
    Image enemy;
    int xVelocity = 2;
    int yVelocity = 1;
    int x = (int)(Math.random()*825 + 125);
    int y = (int)(Math.random()*100+1);
    boolean evis = true;


    private Timer timer;

    private Virus1[] virus1s = {new Virus1(1.5, 1.7),new Virus1(1.5, 1.7),new Virus1(1.5, 1.7),new Virus1(1.5, 1.7),new Virus1(1.5, 1.7)};


    GamePanel(){

        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
        this.addMouseListener(this);

        timer = new Timer(10,this);
        enemy = new ImageIcon("/Users/sachitt/IdeaProjects/Pollution Simulator/Images/virus.png").getImage();
        victim = new ImageIcon("/Users/sachitt/IdeaProjects/Pollution Simulator/Images/Victim.png").getImage();
        timer.start();
        reset = new JButton();
        this.add(reset);
        reset.addActionListener(null);
        reset.setBounds(25,200,50,20);
        reset.setVisible(true);

//        gameover = new Rectangle(100,100,100,100);
//        gameover.setLocation(null);

    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2D = (Graphics2D) g;

        g2D.drawImage(background,0,0,null);

        //healthbar

        if(hbvisible == true) {
            g2D.setPaint(new Color(140, 8, 41));
            g2D.draw(healthBar);
            g2D.fill(healthBar);

        }
        //menu
        g2D.setPaint(Color.DARK_GRAY);
        g2D.setStroke(new BasicStroke(5));
        g2D.fill(menu);
        g2D.drawImage(victim, victim_x, victim_y, null);

        if(evis == true) {
            g2D.drawImage(enemy, x, y, null);
        }

//      virus
        for(int i = 0; i < virus1s.length; i++){
            if(virus1s[i].isVisible() == true) {
                g2D.drawImage(virus1s[i].getImage(), virus1s[i].getX(), virus1s[i].getY(), null);
            }
        }

//      gameover

        if(gameoverVis == true){
            g2D.setPaint(new Color(143, 50, 50, 255));
            g2D.draw(gameover);
            g2D.fill(gameover);

            g2D.setFont(new Font("ink Free", Font.BOLD,70));
            g2D.setPaint(Color.BLACK);
            g2D.drawString("GAME OVER",350,300);
            g2D.setFont(new Font("ink Free",Font.BOLD,15));
            g2D.drawString("Click to play again!",490,380);
        }


//        POLLUTION INFO
        g2D.drawImage(virus1s[0].getImage(),37,160,null);
        g2D.setFont(new Font("ink Free",Font.BOLD,14));
        g2D.setPaint(Color.black);
        g2D.drawString("Avoid the bad",2,110);
        g2D.drawString("air from coming",2,125);
        g2D.drawString("to you:",2,140);

//        HEALING INFO
        g2D.drawString("Use the antedote",0,250);
        g2D.drawString("to heal yourself:",0,265);
        g2D.drawImage(enemy,37,285,null);

        g2D.drawString("Dont let your",0,375);
        g2D.drawString("Health bar drain!",0,390);




    }


    @Override
    public void actionPerformed(ActionEvent e) {
//        Checks for yVelocity

        if(y >= PANEL_HEIGHT-enemy.getWidth(null) || y < 0){
            yVelocity = yVelocity*-1;
        }
        y += yVelocity;
        //Checks for the xVelocity
        if(x >= PANEL_WIDTH-enemy.getWidth(null) || x <= 125){
            xVelocity = xVelocity*-1;
        }
        x += xVelocity;

        if(victim_y > 310 || victim_y < 290){
            victim_yv = victim_yv*-1;
        }
        victim_y += victim_yv;

        for(Virus1 virus : virus1s){
            if(virus.isVisible() == true && virus.getX()+virus.getWidth() <= victim_x + victim.getWidth(null) && virus.getX() >= victim_x &&
                    virus.getY() + virus.getWidth() <= victim_y + victim.getHeight(null) && virus.getY() >= victim_y){
                reduceHealthBar();
            }
        }


        for(int i = 0; i < virus1s.length; i++){
            if(virus1s[i].getX() > PANEL_WIDTH - virus1s[i].getWidth() || virus1s[i].getX() < 125){
                virus1s[i].reverseXVelocity();
            }
            if(virus1s[i].getY() > PANEL_HEIGHT-virus1s[i].getWidth() || virus1s[i].getY() < 0){
                virus1s[i].reverseYVelocity();
            }
            virus1s[i].moveY();
            virus1s[i].moveX();
        }

        check();
        repaint();

    }

    public int getPANEL_HEIGHT(){
        return PANEL_HEIGHT;
    }

    public int getPANEL_WIDTH(){
        return PANEL_WIDTH;
    }

    public void pause(){
        for(int i = 0; i < virus1s.length; i++){
            virus1s[i].setVisible(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        // Check if the mouse press is within the bounds of any virus image
        for (Virus1 virus : virus1s) {
            if (virus.isVisible() && mouseX >= virus.getX() && mouseX <= virus.getX() + 50
                    && mouseY >= virus.getY() && mouseY <= virus.getY() + 50) {
                virus.setVisible(false);
            }
        }

        if (evis == true && mouseX >= x && mouseX <= x + 50
                && mouseY >= y && mouseY <= y + 50) {
            evis = false;
            healthBar.width += 30;
        }

        if(gameoverVis == true && gameover.contains(mouseX,mouseY)){
            healthBar.width = healthBarWidth;
            hbvisible = true;
            gameoverVis = false;
            evis = true;
            for(Virus1 virus : virus1s){
                virus.setVisible(true);
                virus.setX((int) (Math.random() * 825 + 125));
                virus.setY((int) (Math.random() * 145+5));

            }
        }


    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    public void reduceHealthBar(){
        healthBar.width -= 1;
        if(healthBar.width == 1){
            healthBar.width = 0;
        }
    }



    public void check(){

//        reset to see if it is invisible it resets it to the starting point
        int count = 0;

        for(Virus1 virus : virus1s){
            if(virus.isVisible() == true || evis == true){
                count--;
            }
        }

        if(count == 0){
            for(Virus1 virus : virus1s){
                virus.setVelocity();
                virus.setX((int)(Math.random()*825+125));
                virus.setY((int)(Math.random()*100+1));
                virus.setVisible(true);
            }

            x = (int)(Math.random()*825+125);
            y = (int)(Math.random()*100+1);
            evis = true;
        }



//        game over
        if(healthBar.width < 1){
            for(Virus1 virus : virus1s){
                virus.setVisible(false);
                hbvisible = false;
                evis = false;
            }

            gameoverVis = true;
            victim_yv = 0;
        }




    }


}
