package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTools;

public class TileManager {
	
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		
		//MEMBUAT 10 SETIAP SEPULUH TILE DARI MASIN-MASING DATA ARRAY DI DALAM TILE
		tile = new Tile[60];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/world.txt");
		
	}
	
	
	
	public void getTileImage() {
		
		setup(0, "grass1", false);
		setup(1, "wall", true);
		setup(2, "water-center", true);
		//setup(3, "Dirt", false);
		setup(3, "tree", true);
		setup(4, "Sand", false);
		
		//DIRT
		setup(5, "dirt-top-left", false);
		setup(6, "dirt-top-center", false);
		setup(7, "dirt-top-right", false);
		setup(8, "dirt-center-left", false);
		setup(9, "dirt-center", false);
		setup(10, "dirt-center-right", false);
		setup(11, "dirt-bottom-left", false);
		setup(12, "dirt-bottom-center", false);
		setup(13, "dirt-bottom-right", false);
		
		//WATER
		setup(14, "water-top-left", true);
		setup(15, "water-top-center1", true);
		setup(16, "water-top-center2", true);
		setup(17, "water-top-right", true);
		setup(18, "water-center1-left", true);
		setup(19, "water-center1-right", true);
		setup(20, "water-center2-left", true);
		setup(21, "water-center2-center", true);
		setup(22, "water-center2-right", true);
		setup(23, "water-bottom-left", false);
		setup(24, "water-bottom-center1", true);
		setup(25, "water-bottom-center2", false);
		setup(26, "water-bottom-right", false);
		setup(27, "water-center-right2", true);
		setup(28, "water-corner-right", false);
		setup(29, "water-left-1", false);
		setup(30, "water-corner-right2", false);
		setup(31, "water-bottom-1", false);
		
		//rock
		setup(32, "rock1", true);
		
		//SEED
		setup(33, "seed1", false);
		setup(34, "seed2", false);
		setup(35, "seed3", true);
		
		//DIRT
		setup(36, "dirt-corner-left", false);
		setup(37, "dirt-center1", false);
		setup(38, "dirt-corner-right", false);
		setup(39, "dirt-line-top", false);
		setup(40, "dirt-line-center", false);
		setup(41, "dirt-line-bottom", false);
		
		//BRICK
		setup(42, "brick-top-left", true);
		setup(43, "brick-top-center1", true);
		setup(44, "brick-top-center2", true);
		setup(45, "brick-top-right", true);
		setup(46, "brick-center-left", true);
		setup(47, "brick-center-center1", false);
		setup(48, "brick-center-center2", false);
		setup(49, "brick-center-right", true);
		setup(50, "brick-bottom-left", true);
		setup(51, "brick-bottom-center1", false);
		setup(52, "brick-bottom-center2", false);
		setup(53, "brick-bottom-right", true);
	}
	
	public void setup(int index, String imageName, boolean collision) {
		UtilityTools uTool = new UtilityTools();
		
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath) {
		try {
			
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			//Mengambil data integer line by line dari map.txt dan mengubah nya menjadi dari string ke integer
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		}catch(Exception e) {
			
		}
	}
	
	
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;
		
		//CARA OTOMATIS MENGLOAD MAP
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
			   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
			   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
			   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY ) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}
		
			worldCol++;
			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
			
		}
		
		
		//CARA MANUAL SATU PERSATU DRAW MAP
//		// X = KANAN KIRI
//		// Y = ATAS BAWAH
//		//TOP BAR LINE = BARI PERTAMA		
//		g2.drawImage(tile[1].image, 0, 0, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[1].image, 96, 0, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[1].image, 144, 0, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[1].image, 192, 0, gp.tileSize, gp.tileSize, null);
//		
//		//LEFT = BAGIAN KIRI = BARIS KEDUA	
//		g2.drawImage(tile[1].image, 0, 48, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[0].image, 48, 48, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[0].image, 96, 48, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[0].image, 144, 48, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[0].image, 192, 48, gp.tileSize, gp.tileSize, null);
//		
//		//BARIS KETIGAA
//		g2.drawImage(tile[1].image, 0, 96, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[0].image, 48, 96, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[0].image, 96, 96, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[0].image, 144, 96, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[1].image, 192, 96, gp.tileSize, gp.tileSize, null);
//		
//		//BARIS KEEMPAT
//		g2.drawImage(tile[1].image, 0, 144, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[0].image, 48, 144, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[0].image, 96, 144, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[0].image, 144, 144, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[1].image, 192, 144, gp.tileSize, gp.tileSize, null);
//		
//		//BARIS KELIMA
//		g2.drawImage(tile[1].image, 0, 192, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[2].image, 48, 192, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[2].image, 96, 192, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[2].image, 144, 192, gp.tileSize, gp.tileSize, null);
//		g2.drawImage(tile[1].image, 192, 192, gp.tileSize, gp.tileSize, null);
//	
		
	}
}
