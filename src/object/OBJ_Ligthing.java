package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Ligthing extends Entity{
	
	public OBJ_Ligthing(GamePanel gp) {
		
		super(gp);
		
		name = "Ligthing";
		down1 = setup("/objects/ligthing");
	}
}
