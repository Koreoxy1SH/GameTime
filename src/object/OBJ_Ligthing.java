package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Ligthing extends SuperObject{
	
	GamePanel gp;
	
	public OBJ_Ligthing(GamePanel gp) {
		
		name = "Ligthing";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/ligthing.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
