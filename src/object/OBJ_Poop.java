package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Poop extends Entity{

	public OBJ_Poop(GamePanel gp) {
		
		super(gp);
		
		name = "Poop";
		down1 = setup("/objects/poop");
	}
}
