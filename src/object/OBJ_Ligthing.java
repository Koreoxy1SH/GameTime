package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Ligthing extends SuperObject{
	public OBJ_Ligthing() {
		
		name = "Ligthing";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/ligthing.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
