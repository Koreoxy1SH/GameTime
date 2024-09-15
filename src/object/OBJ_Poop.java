package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Poop extends SuperObject{
	GamePanel gp;
	public OBJ_Poop(GamePanel gp) {
		
		name = "Poop";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/poop.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
