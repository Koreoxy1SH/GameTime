package main;

import entity.NPC_OldMan;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Hole;
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
		gp.obj[0].worldX = 36 * gp.tileSize;
		gp.obj[0].worldY = 25 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Hole(gp);
		gp.obj[1].worldX = 48 * gp.tileSize;
		gp.obj[1].worldY = 16 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Hole(gp);
		gp.obj[2].worldX = 37 * gp.tileSize;
		gp.obj[2].worldY = 5 * gp.tileSize;
	}
	
	public void setNPC() {
		gp.npc[0] = new NPC_OldMan(gp);
		gp.npc[0].worldX = gp.tileSize * 20;
		gp.npc[0].worldY = gp.tileSize * 19;
	}
}
