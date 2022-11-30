package tile;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

	GamePanel gp;
	Tile[] tile;
	
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[10];
		
		getTileImage();
	}
	
	public void getTileImage() {
		
		try {
			
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		int i = 0;
		int x = gp.tileSize;
		int y = gp.tileSize * 15;
		
		//Draw tile setiap kali bola jalan
		
		for(i = 0; i < 15; i++) {
			g2.drawImage(tile[0].image, 0, gp.tileSize * i, gp.tileSize, gp.tileSize, null);
		}
		
		for (i = 0; i < 32; i++) {
			g2.drawImage(tile[0].image, x * i, y, gp.tileSize, gp.tileSize, null);
		}
		
	}
	
}
