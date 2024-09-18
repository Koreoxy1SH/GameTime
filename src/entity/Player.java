package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTools;

public class Player extends Entity {

	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	int standCounter = 0;
	boolean  moving = false;
	int pixelCounter = 0;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		super(gp);
		
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		// Solid Area for character		
		solidArea = new Rectangle();
		solidArea.x = 1;
		solidArea.y = 1;
		solidArea.width = 46;
		solidArea.height = 46;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY= solidArea.y;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		
		//STRATING POSITION FOR PLAYER 
		worldX = gp.tileSize * 30;
		worldY = gp.tileSize * 21;
		
		speed = 4;
		direction = "down";
		
		//PLAYER STATUS
		maxLife = 6;
		life = maxLife;
	}
	
	public void getPlayerImage() {
		
		up1 = setup("/player/7");
		up2 = setup("/player/8");
		down1 = setup("/player/1");
		down2 = setup("/player/2");
		left1 = setup("/player/3");
		left2 = setup("/player/4");
		right1 = setup("/player/5");
		right2 = setup("/player/6");
	}
	
	
	public void update() {
		
		if(moving == false) {
			if(keyH.upPressed == true || keyH.downPressed == true || 
					keyH.leftPressed == true || keyH.rightPressed == true) {
				
				if(keyH.upPressed == true) {
					direction = "up";
				} else if (keyH.downPressed == true) {
					direction = "down";
				} else if (keyH.leftPressed == true) {
					direction = "left";
				} else if (keyH.rightPressed == true) {
					direction = "right";
				}
				
				moving = true;
				
				//Check tile collision
				collisionOn = false;
				gp.cChecker.checkTile(this);
				
				//CHECK OBJECT COLLISION
				int objIndex = gp.cChecker.checkObject(this, true);
				pickUpObject(objIndex);
			}
				
				else {
					standCounter++;
					
					if(standCounter == 20) {
						spriteNum = 1;
						standCounter = 0;
					}
					
				}
				
		}	
		
		if(moving == true) {
			//CHECK NPC COLLISION
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			
			//CHECK EVENT
			gp.eHandler.checkEvent();
			
			gp.keyH.enterPressed = false;
			
			//IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(collisionOn == false ) {
				switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
			
			spriteCounter++;
			if(spriteCounter > 12) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
			
			pixelCounter += speed;
			
			if(pixelCounter == 48) {
				moving = false;
				pixelCounter = 0;
			}
		} else {
			standCounter++;
			
			if(standCounter == 20) {
				spriteNum = 1;
				standCounter = 0;
			}
			
		}
}

			
	public void pickUpObject(int i) {
		
		if(i != 999) {
			
		}
	}
	
	public void interactNPC(int i) {
		if(i != 999) {
			
			if(gp.keyH.enterPressed == true) {
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
		}
		
	}
	
	public void draw(Graphics2D g2) {
		//	g2.setColor(Color.white);
		//	g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
		}
		
		g2.drawImage(image, screenX, screenY, null);
	}
}
