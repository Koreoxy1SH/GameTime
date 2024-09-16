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
		gp.obj[0].worldX = 47 * gp.tileSize;
		gp.obj[0].worldY = 16 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Key(gp);
		gp.obj[1].worldX = 20 * gp.tileSize;
		gp.obj[1].worldY = 40 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Door(gp);
		gp.obj[2].worldX = 43 * gp.tileSize;
		gp.obj[2].worldY = 40 * gp.tileSize;
		
		gp.obj[3] = new OBJ_Chest(gp);
		gp.obj[3].worldX = 35 * gp.tileSize;
		gp.obj[3].worldY = 4 * gp.tileSize;
		
		gp.obj[4] = new OBJ_Poop(gp);
		gp.obj[4].worldX = 36 * gp.tileSize;
		gp.obj[4].worldY = 16 * gp.tileSize;
		
		gp.obj[5] = new OBJ_Ligthing(gp);
		gp.obj[5].worldX = 25 * gp.tileSize;
		gp.obj[5].worldY = 17 * gp.tileSize;
		
		gp.obj[6] = new OBJ_Ligthing(gp);
		gp.obj[6].worldX = 35 * gp.tileSize;
		gp.obj[6].worldY = 23 * gp.tileSize;
		
		gp.obj[7] = new OBJ_Key(gp);
		gp.obj[7].worldX = 43 * gp.tileSize;
		gp.obj[7].worldY = 39 * gp.tileSize;
	}
}
