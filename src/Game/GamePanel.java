
package Game;

import Controller.KeyboardController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.sound.sampled.*;


public class GamePanel extends JPanel{

    // Timer
    private Timer gameTimer;


    public KeyboardController controller;


   
    // Dimensioni schermata e fps
    private final int gameWidth = 800; 
    private final int gameHeight = 800;
    private final int framesPerSecond = 120;

    // Contatori
    Random r = new Random();
    private int score = 0;
    public static int level = 1;
    private int numberOfLives = 3;
    private int highScore;
    private int markerX, markerY;
    File f = new File("Highscore.txt");
    int count = 0;

    // Oggetti
    public Ship playerShip;
    public Ship singleLife;
    public Ship bonusEnemy;
    public Enemy enemy;
    public Shield shield;
    public Bullet bullet;
    public Beam beam;

    // Booleani
    private boolean newBulletCanFire = true;
    private boolean newBeamCanFire = true;
    private boolean newBonusEnemy = true;
    private boolean hitMarker = false;
    private boolean gameStatus = false;
    public static boolean en = false;
    // Liste Array 
    public ArrayList<Ship> lifeList = new ArrayList();
    public ArrayList<Ship> bonusEnemyList = new ArrayList();
    public ArrayList<Enemy> enemyList = new ArrayList();
    public ArrayList<Shield> shieldList = new ArrayList();
    public ArrayList<Beam> beamList = new ArrayList();
    public ImageIcon background = new ImageIcon("dist/images/backgroundSkin.jpg");
    public ImageIcon background2 = new ImageIcon("dist/images/bonusBackground.jpg");

    public File sound1 = new File("sounds/FX2.wav");
    public File sound2 = new File("sounds/FX9.wav");
    public File sound3 = new File("sounds/FX13.wav");
    public File sound4 = new File("sounds/Perc3.wav");
    public File musica = new File("sounds/Musica.wav");
    public File win = new File("sounds/Win.wav");
    public File loss = new File("sounds/Loss.wav");
    
    
    public boolean enemy(boolean en){
        if (enemyList.size() == 0){
            en = true;
        }else{
            en = false;
        }
        return en;
    }


    public final void setupGame() {

        // Nemici
    		for (int row = 0; row < 6; row++) {
               
                for (int column = 0; column < 5; column++) {
                    enemy = new Enemy((20 + (row * 100)), (20 + (column * 60)), level, 0, column, null, 40, 40);
                    enemyList.add(enemy);
               
            }
        }



        // Controllo movimenti
        controller.resetController();

        // Valori giocatore   
        playerShip = new Ship(375, 730, null, controller);

        // Valori nemici
        for (int column = 0; column < numberOfLives; column++) {
            singleLife = new Ship(48 + (column * 20), 10, Color.WHITE, null);
            lifeList.add(singleLife);
        }

        // Creazione hitbox
        for (int row = 0;
                row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                shield = new Shield(100 + (column * 250), 650 - (row * 10), 70, 10, Color.RED);
                shieldList.add(shield);
            }
        }
    }
    
    
    
    @Override
    public void paint(Graphics g) {

        background.paintIcon(null, g, 0, -150);


      if (count == 2) {
           background2.paintIcon(null, g, 0, 0);
       }

      if (count < 2) {
           background.paintIcon(null, g, 0, -150);
        }

    	
        //Assegnazione punteggio
        if (bullet != null) {
            if (hitMarker) {
                g.setColor(Color.WHITE);

                    g.drawString("+ 100", markerX + 20, markerY -= 1);
            }
        }
       
        
        // Navicella giocatore
        playerShip.draw(g);

        // Scudi
        for (int index = 0; index < shieldList.size(); index++) {
            shieldList.get(index).draw(g);
        }

        //Navicelle nemiche
        try {
            for (int index = 0; index < enemyList.size(); index++) {
                enemyList.get(index).draw(g);
            }

        } catch (IndexOutOfBoundsException e) {
        }

        // Disegna il proiettile 
        if (controller.getKeyStatus(32)) {
            if (newBulletCanFire) {
                bullet = new Bullet(playerShip.getXPosition() + 22, playerShip.getYPosition() - 20, 0, Color.RED);
                newBulletCanFire = false;
            }
        }
        
        if (bullet != null) {
            bullet.draw(g);
        }

        // Genera colpi casuali dai nemici
            if (newBeamCanFire) {
                for (int index = 0; index < enemyList.size(); index++) {
                    if (r.nextInt(30) == index) {
                        beam = new Beam(enemyList.get(index).getXPosition(), enemyList.get(index).getYPosition(), 0, Color.YELLOW);
                        beamList.add(beam);
                       
                    }
                    newBeamCanFire = false;
                }
            }
        
        
        for (int index = 0; index < beamList.size(); index++) {
            beamList.get(index).draw(g);
        }
        if (newBonusEnemy) {
            if (r.nextInt(3000) == 1500) {
                bonusEnemy = new Ship(-50, 30, Color.RED, null);
                bonusEnemyList.add(bonusEnemy);
                newBonusEnemy = false;
            }
        }
        for (int index = 0; index < bonusEnemyList.size(); index++) {
            bonusEnemyList.get(index).bonusDraw(g);
        }

        // Contatore di punti
        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 260, 20);

        // Contatore di vite
        g.setColor(Color.WHITE);
        g.drawString("Lives:", 11, 20);
        for (int index = 0; index < lifeList.size(); index++) {
            lifeList.get(index).lifeDraw(g);
        }
        // Livello
        g.setColor(Color.WHITE);
        g.drawString("Level " + level, 750, 20);

        // Highscore 
        g.setColor(Color.WHITE);
        g.drawString("Highscore: " + highScore, 440, 20);

        
    }
    
    public void updateGameState(int frameNumber) {

        // Movimento del giocatore
        playerShip.move();

        // Usa fileScan per aggiornare l'highscore
        try {
            Scanner fileScan = new Scanner(f);
            while (fileScan.hasNextInt()) {
                String nextLine = fileScan.nextLine();
                Scanner lineScan = new Scanner(nextLine);
                highScore = lineScan.nextInt();
            }
        } catch (FileNotFoundException e) {
        }
      
        // Aggiorna l'highscore se viene superato
        try {
            if (score > highScore) {
                String scoreString = Integer.toString(score);
                PrintWriter pw = new PrintWriter(new FileOutputStream(f, false));
                pw.write(scoreString);
                pw.close();
            }
        } catch (FileNotFoundException e) {
        }
        
        

        // Quando la fila di nemici arriva alla fine o all'inizio della schermata, velocità di x *-1 e piu giu di 10
        if ((enemyList.get(enemyList.size() - 1).getXPosition() + enemyList.get(enemyList.size() - 1).getXVelocity()) > 770 || (enemyList.get(0).getXPosition() + enemyList.get(0).getXVelocity()) < 0) {
            for (Enemy value : enemyList) {
                value.setXVelocity(value.getXVelocity() * -1);
                value.setYPosition(value.getYPosition() + 10);
            }
        } else { //Movimento dei nemici in orizzontale
            for (Enemy value : enemyList) {
                value.move();
            }
        }
        
               

        // Movimento del proiettile 
        if (bullet != null) {

            bullet.setYPosition(bullet.getYPosition() - 15 );
            if (bullet.getYPosition() < 0) {
                newBulletCanFire = true;
            }

            // Collisioni dei nemici
            for (int index = 0; index < enemyList.size(); index++) {
                if (bullet.isColliding(enemyList.get(index))) {
                   
                    bullet = new Bullet(0, 0, 0, null);
                    newBulletCanFire = true;

                    // Hitmarker + punteggio per kill
                        score += 100;
                        hitMarker = true;
                        markerX = enemyList.get(index).getXPosition(); 
                        markerY = enemyList.get(index).getYPosition();
                        enemyList.remove(index);

                    //Suono hitmarker
                        Sounds.sound(sound1);


                }
            }

            // Collisioni con gli scudi
            for (int index = 0; index < shieldList.size(); index++) {
                if (bullet.isColliding(shieldList.get(index))) {
                    // Stati di rottura degli scudi
                    // Massima
                    if (shieldList.get(index).getColor() == Color.RED) {
                        shieldList.get(index).setColor(Color.ORANGE);
                       
                        bullet = new Bullet(0, 0, 0, null);
                        newBulletCanFire = true;
                    // Buona
                    } else if (shieldList.get(index).getColor() == Color.ORANGE) {
                        shieldList.get(index).setColor(Color.YELLOW);
                       
                        bullet = new Bullet(0, 0, 0, null);
                        newBulletCanFire = true;
                    // Scarsa
                    } else if (shieldList.get(index).getColor() == Color.YELLOW) {
                        shieldList.get(index).setColor(Color.WHITE);
                       
                        bullet = new Bullet(0, 0, 0, null);
                        newBulletCanFire = true;
                    //Debole
                    } else if (shieldList.get(index).getColor() == Color.WHITE) {
                        shieldList.remove(index);
                        
                        bullet = new Bullet(0, 0, 0, null);
                        newBulletCanFire = true;
                    }
                }
            }
        }
       
        // Muove i nemici bonus
        if (!bonusEnemyList.isEmpty()) {
            for (int index = 0; index < bonusEnemyList.size(); index++) {
                bonusEnemyList.get(index).setXPosition(bonusEnemyList.get(index).getXPosition() + (2));
                if (bonusEnemyList.get(index).getXPosition() > 800) {
                    bonusEnemyList.remove(index);
                    newBonusEnemy = true;
                }
            }
           
            //Collisioni dei nemici bonus
            for (int index = 0; index < bonusEnemyList.size(); index++) {
                if (bullet != null) {
                    if (bonusEnemyList.get(index).isColliding(bullet)) {
                        bonusEnemyList.remove(index);
                        bullet = new Bullet(0, 0, 0, null);
                        newBulletCanFire = true;
                        newBonusEnemy = true;
                        score += 5000; 
                    }
                }
            }
        }

        //Proiettili nemici piu veloci ogni livello
            if (beam != null) {
                for (int index = 0; index < beamList.size(); index++) {
                    beamList.get(index).setYPosition(beamList.get(index).getYPosition() + (4 + level));
                    if (beamList.get(index).getYPosition() > 800) {
                        beamList.remove(index);
                    }
                }
            }
        

        // Collisioni tra proiettili e scudi
        try {
            for (int j = 0; j < shieldList.size(); j++) {
                for (int index = 0; index < beamList.size(); index++) {
                    if (beamList.get(index).isColliding(shieldList.get(j))) {
                       //Suono scudo colpito
                        Sounds.sound(sound2);

                        // Massima
                        if (shieldList.get(j).getColor() == Color.RED) {
                            shieldList.get(j).setColor(Color.ORANGE);
                         
                            beamList.remove(index);
                        // Buona
                        } else if (shieldList.get(j).getColor() == Color.ORANGE) {
                            shieldList.get(j).setColor(Color.YELLOW);
                            
                            beamList.remove(index);
                        // SCarsa
                        } else if (shieldList.get(j).getColor() == Color.YELLOW) {
                            shieldList.get(j).setColor(Color.WHITE);
                            
                            beamList.remove(index);
                        // Debole
                        } else if (shieldList.get(j).getColor() == Color.WHITE) {
                            shieldList.remove(j);
                           
                            beamList.remove(index);
                        }
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
        }

        // Collisioni tra proiettili e giocatore
        for (int index = 0; index < beamList.size(); index++) {
            if (beamList.get(index).isColliding(playerShip)) {
                beamList.remove(index);

                //Suono giocatore colpito
                Sounds.sound(sound4);

                //Toglie una vita
                lifeList.remove(lifeList.size() - 1); //Toglie una vita
            }
        }

        // Controllo della cadenza di fuoco
        if (beamList.isEmpty()) {
            newBeamCanFire = true;
        }

        //Distruzione degli scudi tramite collisone
        for (int input = 0; input < enemyList.size(); input++) {
            for (int j = 0; j < shieldList.size(); j++) {
                if (enemyList.get(input).isColliding(shieldList.get(j))) {
                    shieldList.remove(j);
                }
            }
            // Conquista dei nemici
            if (enemyList.get(input).getYPosition() + 50 >= 750) {
                enemyList.clear();
                shieldList.clear();
                lifeList.clear();
                beamList.clear();
                numberOfLives -= 1;
               
                setupGame();
            }
        }

        //Aggiorna le vite del giocatore
        if (playerShip.isColliding) {
            int index = lifeList.size() - 1;
            lifeList.remove(index);
        } 
        
        //In caso di vittoria
        if (enemyList.isEmpty()) {

            //Suono vittoria
            Sounds.sound(win);

            //Controllo numero vite
            //System.out.print (lifeList.size());

            //Contatore livelli perfetti
            if (lifeList.size() == 3) {
                count++;
            }

        	if (level == 3) {
        		JOptionPane.showConfirmDialog(null, "COMPLIMENTI!\nHAI TOTALIZZATO " + score + " PUNTI", "VITTORIA!", -1);
        		System.exit(0);
        	}

        	if (level != 3) {




                // Opzioni post-partita
                int answer = JOptionPane.showConfirmDialog(null, "HAI TOTALIZZATO " + score + " PUNTI\nPassare al nuovo livello?", "VITTORIA!", 0);
                // Riavvia la partita
                if (answer == 0) {
                    lifeList.clear();
                    enemyList.clear();
                    shieldList.clear();
                    beamList.clear();
                    bonusEnemyList.clear();
                    score = score;
                    level++;
                    numberOfLives = 3;
                    newBulletCanFire = true;
                    newBeamCanFire = true;
                    newBonusEnemy = true;
                    setupGame();
                }
                // Chiudi il gioco
                if (answer == 1) {
                    System.exit(0);
                }
            }
        }
        
        
        
        
        // Termina partita se il giocatore perde tutte le vite
        else if (lifeList.isEmpty()) {

            //Suono sconfitta
            Sounds.sound(loss);

            // Opzioni post-partita
            int answer = JOptionPane.showConfirmDialog(null, "HAI TOTALIZZATO " + score + " PUNTI\nVuoi giocare di nuovo?", "SCONFITTA", 0);
            // Se si riavvia la partita
            if (answer == 0) {
                lifeList.clear();
                enemyList.clear();
                shieldList.clear();
                beamList.clear();
                bonusEnemyList.clear();
                score = 0;
                level = 1;
                numberOfLives = 3;
                newBulletCanFire = true;
                newBeamCanFire = true;
                newBonusEnemy = true;
                setupGame();
            }
            // Se no chiudi il gioco
            if (answer == 1) {
                System.exit(0);
            }
        }



    }
    
   
    
    public GamePanel() {
        // Dimensioni del pannello
        this.setSize(gameWidth, gameHeight);
        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        this.setBackground(Color.BLACK);

      // Contollo tastiera
        this.controller =new KeyboardController();
        this.addKeyListener(controller);


        
        // Call setupGame to initialize fields
        this.setupGame();
        this.setFocusable(true);
        this.requestFocusInWindow();

        //Musica in sottofondo
         Sounds.music(musica);
    }


    public void start() {
        // Timer del gioco 
        gameTimer = new Timer(1000 / framesPerSecond, new ActionListener() {

            private int frameNumber = 0;

            //Aggiorna lo stato del gioco ed effettua il repaint
            public void actionPerformed(ActionEvent e) {
                updateGameState(frameNumber++);
                repaint();
            }
        });
        Timer gameTimerHitMarker = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	//Aggiorna lo stato del gioco ed effettua il repaint
                hitMarker = false;
            }
        });

        gameTimer.setRepeats(true);
        gameTimer.start();
        gameTimerHitMarker.setRepeats(true);
        gameTimerHitMarker.start();
    }

}
