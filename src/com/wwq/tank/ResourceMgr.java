package com.wwq.tank;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.wwq.tank.constant.Direction;
import com.wwq.tank.util.ImageUtil;

public class ResourceMgr {

	public static BufferedImage[] baseTank;
	
	public static BufferedImage[] goodTank1;
	
	public static BufferedImage[] goodTank2;
	
	public static BufferedImage[] badTank1;
	
	public static BufferedImage[] badTank2;
	
	public static BufferedImage[] bullet;
	
	public static BufferedImage[] explode;
	
	static {
		init();
	}
	
	public static void init() {
		try {
			baseTank = new BufferedImage[8];
			baseTank[0] = ImageIO.read(ResourceMgr.class.getResourceAsStream("images/tankU.gif"));
			baseTank[1] = ImageIO.read(ResourceMgr.class.getResourceAsStream("images/tankRU.gif"));
			baseTank[2] = ImageIO.read(ResourceMgr.class.getResourceAsStream("images/tankR.gif"));
			baseTank[3] = ImageIO.read(ResourceMgr.class.getResourceAsStream("images/tankRD.gif"));
			baseTank[4] = ImageIO.read(ResourceMgr.class.getResourceAsStream("images/tankD.gif"));
			baseTank[5] = ImageIO.read(ResourceMgr.class.getResourceAsStream("images/tankLD.gif"));
			baseTank[6] = ImageIO.read(ResourceMgr.class.getResourceAsStream("images/tankL.gif"));
			baseTank[7] = ImageIO.read(ResourceMgr.class.getResourceAsStream("images/tankLU.gif"));
			
			bullet = new BufferedImage[8];
			for(int i = 0; i < 8; i++) {
				if(i == 0) {
					bullet[i] = ImageIO.read(ResourceMgr.class.getResourceAsStream("images/bulletU.png"));
				} else {
					bullet[i] = ImageUtil.rotateImage(ImageIO.read(ResourceMgr.class.getResourceAsStream("images/bulletU.png")), i * 45);
				}
			}
			
			explode = new BufferedImage[16];
			for(int i = 1; i <= 16; i++) {
				explode[i - 1] = ImageIO.read(ResourceMgr.class.getResourceAsStream("images/e" + i + ".gif"));
			}
			
			goodTank1 = new BufferedImage[8];
			for(int i = 0; i < 8; i++) {
				if(i == 0) {
					goodTank1[i] = ImageIO.read(ResourceMgr.class.getResourceAsStream("images/GoodTank1.png"));
				} else {
					goodTank1[i] = ImageUtil.rotateImage(ImageIO.read(ResourceMgr.class.getResourceAsStream("images/GoodTank1.png")), i * 45);
				}
			}
			
			goodTank2 = new BufferedImage[8];
			for(int i = 0; i < 8; i++) {
				if(i == 0) {
					goodTank2[i] = ImageIO.read(ResourceMgr.class.getResourceAsStream("images/GoodTank2.png"));
				} else {
					goodTank2[i] = ImageUtil.rotateImage(ImageIO.read(ResourceMgr.class.getResourceAsStream("images/GoodTank2.png")), i * 45);
				}
			}
			
			badTank1 = new BufferedImage[8];
			for(int i = 0; i < 8; i++) {
				if(i == 0) {
					badTank1[i] = ImageIO.read(ResourceMgr.class.getResourceAsStream("images/BadTank1.png"));
				} else {
					badTank1[i] = ImageUtil.rotateImage(ImageIO.read(ResourceMgr.class.getResourceAsStream("images/BadTank1.png")), i * 45);
				}
			}
			
			badTank2 = new BufferedImage[8];
			for(int i = 0; i < 8; i++) {
				if(i == 0) {
					badTank2[i] = ImageIO.read(ResourceMgr.class.getResourceAsStream("images/BadTank2.png"));
				} else {
					badTank2[i] = ImageUtil.rotateImage(ImageIO.read(ResourceMgr.class.getResourceAsStream("images/BadTank2.png")), i * 45);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static BufferedImage getImage(Direction dir, BufferedImage[] images) {
		if(Direction.UP == dir) {
			return images[0];
		} else if(Direction.RIGHT_UP == dir) {
			return images[1];
		} else if(Direction.RIGHT == dir) {
			return images[2];
		} else if(Direction.RIGHT_DOWN == dir) {
			return images[3];
		} else if(Direction.DOWN == dir) {
			return images[4];
		} else if(Direction.LEFT_DOWN == dir) {
			return images[5];
		} else if(Direction.LEFT == dir) {
			return images[6];
		} else if(Direction.LEFT_UP == dir) {
			return images[7];
		}
		
		return images[0];
	}
}
