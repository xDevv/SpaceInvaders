
package Game;

import java.awt.Color;
import javax.swing.JFrame;


public class GameFrame extends JFrame
{
    private GamePanel game;
    
    public GameFrame()
    {
        // Titolo 
        super("Space Invaders PC");
        
        // Chiusura programma
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        // Crea un'istanza di gioco e attiva il double buffering che rende più fluido il gioco
        game = new GamePanel();
        game.setDoubleBuffered(true);
        
        // Aggiunge un'istanza di breakout per visualizzare il gioco
        this.getContentPane().add(game); 
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        // Inizializza il gioco
        game.start();  
    }
    
    public static void main(String[] args) 
    {
         java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameFrame().setVisible(true);
            }
        });
        
    }
}
