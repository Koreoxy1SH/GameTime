package main;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Ligthing;
import object.OBJ_Poop;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.obj[0] = new OBJ_Key(gp);
		gp.obj[0].worldX = 23 * gp.tileSize;
		gp.obj[0].worldY = 7 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Key(gp);
		gp.obj[1].worldX = 23 * gp.tileSize;
		gp.obj[1].worldY = 40 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Door(gp);
		gp.obj[2].worldX = 29 * gp.tileSize;
		gp.obj[2].worldY = 12 * gp.tileSize;
		
		gp.obj[3] = new OBJ_Chest(gp);
		gp.obj[3].worldX = 29 * gp.tileSize;
		gp.obj[3].worldY = 9 * gp.tileSize;
		
		gp.obj[4] = new OBJ_Poop(gp);
		gp.obj[4].worldX = 36 * gp.tileSize;
		gp.obj[4].worldY = 16 * gp.tileSize;
		
		gp.obj[5] = new OBJ_Ligthing(gp);
		gp.obj[5].worldX = 25 * gp.tileSize;
		gp.obj[5].worldY = 15 * gp.tileSize;
	}
}
