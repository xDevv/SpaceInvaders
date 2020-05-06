

package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends MovingGameObject {
    int diameter;
    int yVelocity;

    public Bullet(int xPosition, int yPosition, int diameter, Color color) {
        super(xPosition, yPosition, 0, 0, color);
        this.diameter = diameter;
    }

    public int getDiameter() {
        return this.diameter;
    }

    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillRect(this.getXPosition(), this.getYPosition(), 7, 15);
    }

    public Rectangle getBounds() {
        Rectangle bulletHitbox = new Rectangle(this.xPos, this.yPos, 7, 15);
        return bulletHitbox;
    }
}
