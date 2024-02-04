import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class Virus1 implements MouseListener {

    private Image virus;
    private int x;
    private int y;
    private double xVelocity;
    private double yVelocity;
    private boolean isVisible;
    JLabel hitBox;


    public Virus1(double xVelocity, double yVelocity) {
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.x = (int) (Math.random() * 825 + 125);
        this.y = (int) (Math.random() * 145+5);
        this.isVisible = true;
        this.virus = new ImageIcon("/Users/sachitt/IdeaProjects/Pollution Simulator/Images/virus2.png").getImage();
    }

    public double getX_Velocity() {
        return this.xVelocity;
    }

    public double getY_Velocity() {
        return this.yVelocity;
    }

    public void velocityFaster(){
        this.xVelocity += 0.3;
        this.yVelocity += 0.3;
    }

    public Image getImage() {
        return virus;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void reverseXVelocity() {
        this.xVelocity = -this.xVelocity;
    }

    public void reverseYVelocity() {
        this.yVelocity = -this.yVelocity;
    }

    public void moveX() {
        this.x += this.xVelocity;
    }

    public void moveY() {
        this.y += this.yVelocity;
    }

    public int getWidth() {
        return virus.getWidth(null);
    }

    public JLabel getHitBox(){
        return hitBox;
    }

    public void setVelocity(){
        this.yVelocity = (int)(Math.random()*3+1);
        this.xVelocity = (int)(Math.random()*3+1);
//        this.xVelocity = xVelocity + 0.3;
//        this.yVelocity = yVelocity + 0.3;
    }



    @Override
    public void mouseClicked(MouseEvent e) {

    }


    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse pressed!");
        int mouseX = e.getX();
        int mouseY = e.getY();

        // Check if the mouse press is within the bounds of the virus image
        if (mouseX >= x && mouseX <= x + getWidth() && mouseY >= y && mouseY <= y + virus.getHeight(null)) {
            isVisible = false;
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
}
