
package Game;

import java.awt.Color;


public abstract class MovingGameObject extends GameObject implements Moveable{
    
    int xVel;
    int yVel;
    
    // Costruttore per tutti gli oggetti non controllabili
    public MovingGameObject(int xPosition, int yPosition, int xVelocity, int yVelocity, Color color)
    {
        super(xPosition, yPosition, color);
        this.xVel = xVelocity;
        this.yVel = yVelocity;
    
    }
    
    // get e set per le parti del costruttore
    public int getXVelocity()
    {
        return xVel;
    }
    public int getYVelocity()
    {
        return yVel;
    }
    public void setXVelocity(int xVelocity)
    {
        this.xVel = xVelocity;
    }
    public void setYVelocity(int yVelocity)
    {
        this.yVel = yVelocity;
    }
    @Override
    
    // Muove gli oggetti non controllabili
    public void move()
    {
        this.xPos += xVel;
        this.yPos += yVel;
    }
    
}
