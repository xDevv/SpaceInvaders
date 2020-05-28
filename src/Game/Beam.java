//Proiettili del nemico
package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Beam extends MovingGameObject {

    //Proietti sparati dai nemici
    public Beam(int xPosition, int yPosition, Color color) {
        super(xPosition, yPosition, 0, 0, color);
    }
    
// Disegna proiettili
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(this.getXPosition(), this.getYPosition(), 7, 15);
    }
    
    // Hitbox del proiettile
    @Override
    public Rectangle getBounds() {
        Rectangle beamHitbox = new Rectangle(xPos, yPos, 7, 15);
        return beamHitbox;
    }
    
}