package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	//SET SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; //16 * 3 = 48x48 tile
	public final int maxScreenCol = 16; //Terdapat 16 kolom
	public final int maxScreenRow = 12; //Tedapat 12 baris
	public final int screenWidth =  tileSize * maxScreenCol; //48 * 16 = 768 pixels;
	public final int screenHeight = tileSize * maxScreenRow; //48 * 12 = 576 pixels;
	
	//FPS
	int FPS = 60;
	
	TileManager tileM = new TileManager(this); 
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this,keyH);
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

//	@Override
//	public void run() {
//
//		
//		double drawInterval = 1000000000/FPS; //0.0166666 seconds
//		double nextDrawTime = System.nanoTime() + drawInterval;
//				
//		while(gameThread != null) {
//			//jika gamethread tidak sama dengan null maka akan berjalan apa?
//			
//			//Mendapatkan currentTime sistem agar untuk tidak hilang object di window nya saat digerakan	
//			//long currentTime = System.nanoTime();
//			//System.out.println("Current time: " + currentTime);
//			
//			// 1. UPDATE: update information such as character positions
//			update();
//			
//			// 2. DRAW : draw the screen with the updated information 
//			repaint();
//			
//			try {
//				double remainingTime = nextDrawTime - System.nanoTime();
//				remainingTime = remainingTime/1000000;
//				
//				if(remainingTime < 0) {
//					remainingTime = 0;
//				}
//				
//				Thread.sleep((long) remainingTime);
//				
//				nextDrawTime += drawInterval;
//				
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	
	public void run() {
		
		double drawInterval = 1000000000/FPS; //0.0166666 seconds
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			
			//SHOW FPS			
			timer += (currentTime - lastTime);
			
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			//SHOW FPS
			if(timer >= 1000000000) {
				System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
			
			
			
		}
		
	}
	
	
	//Method for update information to run gamethread
	public void update() {
		
		player.update();
		
	}
	
	//Method for draw or paint graphic
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		tileM.draw(g2);
		
		player.draw(g2);
		
		g2.dispose();
	}
	
	
 }
