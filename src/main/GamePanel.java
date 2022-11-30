package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import object.Ball;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	final int originalTileSize = 16;
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; //48X48 tile;
	public final int maxScreenCol = 32;
	public final int maxScreenRow = 16;
	public final int screenWidth = tileSize * maxScreenCol; //1536 pixels
	public final int screenHeight = tileSize * maxScreenRow; //768 pixels

	int FPS = 90;
	
	public long startTime;
	public long timeElapsed;
	
	KeyHandler keyH = new KeyHandler();
	TileManager tileM = new TileManager(this);
	Thread gameThread;
	
	public Ball ball = new Ball(this, keyH);
	
	public GamePanel() {
		Color color = new Color(51, 153, 255);
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(color);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);;
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		startTime = System.currentTimeMillis();
		gameThread.start();
	}
	
	/*
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			
			lastTime = currentTime;
			
			if(delta >= 1) {
				
				//1. UPDATE: Update information such as character positions
				update();
				
				//2. DRAW: Draw the screen with the updated information
				repaint();
				delta--;
			}
			
		}
	}
	*/
	
	public void run() {
		while(true) {
			//System.out.println(System.currentTimeMillis());
			this.timeElapsed = System.currentTimeMillis() - this.startTime;
			update();
			repaint();
			try {
				Thread.sleep(1000 / FPS);
			} catch (InterruptedException ex) {}
		}
		
	}
	
	
	public void update() {
		
		ball.update(this);
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		tileM.draw(g2);
		
		ball.draw(g2);
		
		g2.dispose();
	}
	
}
