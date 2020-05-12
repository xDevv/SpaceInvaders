
package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;


public class Enemy extends MovingGameObject {
	//Mondo 1
    ImageIcon enemy1_1 = new ImageIcon("dist/images/enemy1_1.gif");
    ImageIcon enemy2_1 = new ImageIcon("dist/images/enemy2_1.gif");
    ImageIcon enemy3_1 = new ImageIcon("dist/images/enemy3_1.gif");
    //Mondo 2
    ImageIcon enemy1_2 = new ImageIcon("dist/images/enemy1_2.gif");
    ImageIcon enemy2_2 = new ImageIcon("dist/images/enemy2_2.gif");
    ImageIcon enemy3_2 = new ImageIcon("dist/images/enemy3_2.gif");
    //Mondo 3
    ImageIcon enemy1_3 = new ImageIcon("dist/images/enemy1_3.gif");
    ImageIcon enemy2_3 = new ImageIcon("dist/images/enemy2_3.gif");
    ImageIcon enemy3_3 = new ImageIcon("dist/images/enemy3_3.gif");
    
    private int enemytype, width, height;

    
    // Costuttore dei nemici
    public Enemy(int xPosition, int yPosition, int xVelocity, int yVelocity, int enemyType, Color color, int width, int height) {
        super(xPosition, yPosition, xVelocity, yVelocity, color);
        this.enemytype = enemyType;
        this.width = width;
        this.height = height;
    }
    
    @Override
    // Disegna i nemici
    public void draw(Graphics g) {
        
    	if (GamePanel.level == 1) {
    	
    		// Variante 1
    		if (this.enemytype % 3 == 0) {
    			enemy1_1.paintIcon(null, g, this.getXPosition(), this.getYPosition());
    		// Variante 2
    		} else if (this.enemytype % 3 == 1) {
    			enemy2_1.paintIcon(null, g, this.getXPosition(), this.getYPosition());
    		// Variante 3
    		} else if (this.enemytype % 3 == 2) {
    			enemy3_1.paintIcon(null, g, this.getXPosition(), this.getYPosition());
    		} 
    	} else if (GamePanel.level == 2) {
    		// Variante 1
        	if (this.enemytype % 3 == 0) {
                enemy1_2.paintIcon(null, g, this.getXPosition(), this.getYPosition());
            // Variante 2
            } else if (this.enemytype % 3 == 1) {
            	enemy2_2.paintIcon(null, g, this.getXPosition(), this.getYPosition());
            // Varieante 3
            } else if (this.enemytype % 3 == 2) {
            	enemy3_2.paintIcon(null, g, this.getXPosition(), this.getYPosition());
            } 	
    	} else if (GamePanel.level == 3) {
    		// Variante 1
        	if (this.enemytype % 3 == 0) {
                enemy1_3.paintIcon(null, g, this.getXPosition(), this.getYPosition());
            // Variante 2
            } else if (this.enemytype % 3 == 1) {
            	enemy2_3.paintIcon(null, g, this.getXPosition(), this.getYPosition());
            // Variante 3
            } else if (this.enemytype % 3 == 2) {
            	enemy3_3.paintIcon(null, g, this.getXPosition(), this.getYPosition());
            }
    	} 
    }

    // Ottiene le hitbox dei nemici
    @Override
    public Rectangle getBounds() {
        Rectangle enemyHitBox = new Rectangle(this.getXPosition(), this.getYPosition(), width, height);
        return enemyHitBox;
    }

    // Muove tutti i nemici
    @Override
    public void move() {
        xPos += xVel;
    }

}
