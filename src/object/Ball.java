package object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Ball extends Object{

	GamePanel gp;
	KeyHandler keyH;
	
	final int screenX;
	
	double x, y;
	double initSpeedX, initSpeedY;
	double lastSpeedY;
	int radius;
	Color color;
	double gravity = 9.8;
	int startDegree = 40;
	double endDegree;
	int speed = 6;
	
	
	public Ball(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth / 2;
		
		
		
		this.initSpeedX = (float)(speed * Math.cos(Math.toRadians(startDegree)));
		this.initSpeedY = (float)(-speed * (float)Math.sin(Math.toRadians(startDegree)));

		this.lastSpeedY = initSpeedY;		
		
		setDefaultValues();
		getPlayerImage();
		
		
	}
	
	public void setDefaultValues() {
		
		this.x = 2 * gp.tileSize;
		this.y = gp.screenHeight - gp.tileSize;
		
		this.radius = gp.tileSize - 16;
		fase = "diam";
	}
	
	public void getPlayerImage(){
		
		try {
			
			ball1 = ImageIO.read(getClass().getResourceAsStream("/ball/ball01.png"));			
			ball2 = ImageIO.read(getClass().getResourceAsStream("/ball/ball02.png"));			
			ball3 = ImageIO.read(getClass().getResourceAsStream("/ball/ball03.png"));			
			ball4 = ImageIO.read(getClass().getResourceAsStream("/ball/ball04.png"));			

			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update(GamePanel gp) {
		
		fase = "diam";
		spriteCounter++;
		if(spriteCounter > 12) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}
			else if(spriteNum == 2) {
				spriteNum = 3;
			}
			else if(spriteNum == 3) {
				spriteNum = 4;
			}
			else if(spriteNum == 4) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		
		
		
		
		int limitY = gp.screenHeight - gp.tileSize;
		
		x += initSpeedX;
		y += initSpeedY + (gravity*((double)(gp.timeElapsed*gp.timeElapsed)/1000000)*0.5);
		
		lastSpeedY = initSpeedY - (gravity * ((double)(gp.timeElapsed/1000)));
		
		if(y > limitY) {
			y = limitY;
			gp.startTime += gp.timeElapsed;
			gp.timeElapsed = 1000;
			initSpeedY = initSpeedY * 8 / 10;
			initSpeedX = initSpeedX * 8 / 10;
			
		}
		
		
		
		
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(fase){
		case "diam":
			if(spriteNum == 1) {
				image = ball1;
			}
			if(spriteNum == 2) {
				image = ball2;
			}
			if(spriteNum == 3) {
				image = ball3;
			}
			if(spriteNum == 4) {
				image = ball4;
			}
			break;
		
		}
		
		g2.drawImage(image, (int)(x - gp.tileSize), (int)(y - gp.tileSize), gp.tileSize, gp.tileSize, null);
		
		//g2.drawImage(image, screenX, 0, gp.tileSize, gp.tileSize, null);
		
		
		
	}
	
}
