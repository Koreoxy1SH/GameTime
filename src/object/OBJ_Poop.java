package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Poop extends SuperObject{
	public OBJ_Poop() {
		
		name = "Poop";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/poop.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
