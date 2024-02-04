import java.awt.event.KeyEvent;

public class Virus extends Virus1 {

    int damage;

    public Virus(int xVelocity, int yVelocity) {
        super(xVelocity, yVelocity);
        this.damage = 10;

    }

    public int getDamage(){

        return damage;
    }
}
