
package Game;

import java.awt.Color;
import java.awt.Rectangle;


public abstract class GameObject implements Drawable {

    int xPos;
    int yPos;
    Color color;
    boolean isColliding;
    
    public GameObject(){};
    
    // Costruttore per tutti gli oggetti di gioco
    public GameObject(int xPosition, int yPosition, Color color) {
        this.xPos = xPosition;
        this.yPos = yPosition;
        this.color = color;
    }

    public abstract Rectangle getBounds();

    // Ottiene la coordinata X di tutti gli oggetti
    public int getXPosition() {
        return xPos;
    }

    // Ottiene la coordinata Y di tutti gli oggetti
    public int getYPosition() {
        return yPos;
    }

    // Ottiene il colore degli oggetti di gioco
    public Color getColor() {
        return color;
    }

    // Imposta la coordinata X di ogni oggetto di gioco
    public void setXPosition(int xPosition) {
        this.xPos = xPosition;
    }

    // Imposta la coordinata Y di ogni oggetto di gioco
    public void setYPosition(int yPosition) {
        this.yPos = yPosition;
    }

    // Imposta il colore degli oggetti di gioco
    public void setColor(Color color) {
        this.color = color;
    }

    // Controlla le intersezioni tra le hitbox
    public boolean isColliding(GameObject other) {
        isColliding = other.getBounds().intersects(this.getBounds());
        return isColliding;
    }
}
