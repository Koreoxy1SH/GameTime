package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;


public class OBJ_Hole extends SuperObject {
	GamePanel gp;
	public OBJ_Hole(GamePanel gp) {
		
		name = "Hole";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/hole.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
