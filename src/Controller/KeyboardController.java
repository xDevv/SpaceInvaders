
package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;


public class KeyboardController implements KeyListener {
    public boolean[] keyStatus;


    public KeyboardController() {
        keyStatus = new boolean[256];
    }
    
    public boolean getKeyStatus(int keyCode)
    {
        	
    	if(keyCode < 0 || keyCode > 255 )
        {
            System.out.print(keyStatus[keyCode]);
            return false;
        }
        else
        {
            return keyStatus[keyCode]; 
        }

    }

    public void resetController()
    {
        keyStatus = new boolean[256]; 
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        keyStatus[ke.getKeyCode()] = true; 
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        keyStatus[ke.getKeyCode()] = false; 
    }
}
    
    
