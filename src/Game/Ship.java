
package Game;

import Controller.KeyboardController;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;


public class Ship extends ControlledGameObject {

    ImageIcon ship = new ImageIcon("dist/images/ship1_1.gif");
    ImageIcon ship2 = new ImageIcon("dist/images/ship1_2.gif");
    ImageIcon ship3 = new ImageIcon("dist/images/ship1_3.gif");
    ImageIcon bonusEnemy = new ImageIcon("dist/images/bonusenemy1_3.gif");
    ImageIcon lifeCounterShip = new ImageIcon("dist/images/ship1_1_life.gif");
    ImageIcon lifeCounterShip2 = new ImageIcon("dist/images/ship1_2_life.gif");
    ImageIcon lifeCounterShip3 = new ImageIcon("dist/images/ship1_3_life.gif");

    // Costruttore per tutte le navi
    public Ship(int xPosition, int yPosition, Color color, KeyboardController control) {
        super(xPosition, yPosition, color, control);
    }

    //Disegna il nemico bonus
    public void bonusDraw(Graphics g) {

        bonusEnemy.paintIcon(null, g, this.getXPosition(), this.getYPosition());
    }

    // Disegna il contatore delle vite
    public void lifeDraw(Graphics g) {
    	if(GamePanel.level == 1) {
    		lifeCounterShip.paintIcon(null, g, this.getXPosition(), this.getYPosition());
    	} else if(GamePanel.level == 2) {
    		lifeCounterShip2.paintIcon(null, g, this.getXPosition(), this.getYPosition());
    	} else if(GamePanel.level == 3) {
    		lifeCounterShip3.paintIcon(null, g, this.getXPosition(), this.getYPosition());
    	}
    }

    // Disegna la navetta del giocatore
    @Override
    public void draw(Graphics g) {
    	if (GamePanel.level == 1) {
        	ship.paintIcon(null, g, this.getXPosition(), this.getYPosition());
    	}
    	if (GamePanel.level == 2) {
        	ship2.paintIcon(null, g, this.getXPosition(), this.getYPosition());
    	}
    	if (GamePanel.level == 3) {
        	ship3.paintIcon(null, g, this.getXPosition(), this.getYPosition());
    	}
    }

    // Gets the hit box for all ship objects
    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.getXPosition(), this.getYPosition(), 50, 50);
    }

    // Muove la navicella
    @Override
    public void move() {
        // Movimento a sinistra
        if (control.getKeyStatus(37)) {
            xPos -= 10;
        }
        // Movimento a destra
        if (control.getKeyStatus(39)) {
            xPos += 10;
        }
        
        // Move from edge to edge without stopping
        if (xPos > 800) {
            xPos = -50;
        }
        if (xPos < -50) {
            xPos = 800;
        }
    }
}
