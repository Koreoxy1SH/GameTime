package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	int standCounter = 0;
	boolean  moving = false;
	int pixelCounter = 0;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
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
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImage() {
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/7.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/8.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/3.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/4.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/5.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/6.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
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
		} 
}

			
	public void pickUpObject(int i) {
		
		if(i != 999) {
			String objectName = gp.obj[i].name;
			
			switch(objectName) {
			case "Key":
				gp.playSoundEffect(1);
				hasKey++;
				gp.obj[i] = null;
				gp.ui.showMessage("You got a key!");
				break;
			case "Door":
				if(hasKey > 0) {
					gp.playSoundEffect(2);
					gp.obj[i]= null;
					hasKey--;
					gp.ui.showMessage("You opened the door!");
				}else {
					gp.ui.showMessage("You need a key!");
				}
				
				break;
			case "Poop":
				gp.playSoundEffect(3);
				speed -= 2;
				gp.obj[i] = null;
				gp.ui.showMessage("Get slowed");
				break;
			case "Ligthing":
				gp.playSoundEffect(3);
				speed += 2;
				gp.obj[i] = null;
				gp.ui.showMessage("Get speed up!");
				break;
			case "Chest":
				gp.ui.gameFinished = true;
				gp.stopMusic();
				gp.playSoundEffect(4);
				break;
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
		
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
}
