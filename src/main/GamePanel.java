package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import entity.*;
import tile.TileManager;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	
	//Screen Settings
	final int originalTileSize = 32;
	final int scale = 2;
	
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 18;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize*maxScreenCol;
	public final int screenHeight = tileSize*maxScreenRow;

	
	int FPS = 60;
	
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this,keyH);
	
	//Set Player Default Position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	

	//Constructor
	public GamePanel() {
	    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
	    this.setBackground(new Color(101, 142, 156)); // Define a cor de fundo como vermelho (R=255, G=0, B=0)
	    this.setDoubleBuffered(true);
	    this.addKeyListener(keyH);
	    this.setFocusable(true);
	}

	public void startGameThread() {
		gameThread = new Thread(this); // This = GamePanel
		gameThread.start();
		
	}

// Game Loop simplificado
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS; // 0.01666 secs
		double nextDrawTime = System.nanoTime() + drawInterval;		
		
		while(gameThread != null) {
			
			update();
			
			repaint();
			
			try {
			double remainingTime = nextDrawTime - System.nanoTime();
			remainingTime = remainingTime/1000000;
			
			if(remainingTime < 0) {
				remainingTime = 0;
			}
			
			Thread.sleep((long)remainingTime);
			
			nextDrawTime += drawInterval;
			
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}	
	}
	public void update() {
		player.update();
	}
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g); //SubClasse do JPannel
		
		Graphics2D g2 = (Graphics2D)g;
		
		tileM.draw(g2);
		player.draw(g2);
		
		g2.dispose();
	}
}
