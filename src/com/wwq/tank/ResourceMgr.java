package com.wwq.tank;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceMgr {

	public static BufferedImage tankL, tankR, tankU, tankD, tankLU, tankLD, tankRU, tankRD;
	
	public static BufferedImage bulletL, bulletR, bulletU, bulletD, bulletLU, bulletLD, bulletRU, bulletRD;
	
	public static BufferedImage[] explode;
	
	static {
		init();
	}
	
	public static void init() {
		try {
			tankL = ImageIO.read(ResourceMgr.class.getResourceAsStream("images/tankL.gif"));
			tankR = ImageIO.read(ResourceMgr.class.getResourceAsStream("images/tankR.gif"));
			tankU = ImageIO.read(ResourceMgr.class.getResourceAsStream("images/tankU.gif"));
			tankD = ImageIO.read(ResourceMgr.class.getResourceAsStream("images/tankD.gif"));
			tankLU = ImageIO.read(ResourceMgr.class.getResourceAsStream("images/tankLU.gif"));
			tankLD = ImageIO.read(ResourceMgr.class.getResourceAsStream("images/tankLD.gif"));
			tankRU = ImageIO.read(ResourceMgr.class.getResourceAsStream("images/tankRU.gif"));
			tankRD = ImageIO.read(ResourceMgr.class.getResourceAsStream("images/tankRD.gif"));
			
			bulletL = ImageIO.read(ResourceMgr.class.getResourceAsStream("images/bulletL.gif"));
			bulletR = ImageIO.read(ResourceMgr.class.getResourceAsStream("images/bulletR.gif"));
			bulletU = ImageIO.read(ResourceMgr.class.getResourceAsStream("images/bulletU.gif"));
			bulletD = ImageIO.read(ResourceMgr.class.getResourceAsStream("images/bulletD.gif"));
			bulletLU = ImageIO.read(ResourceMgr.class.getResourceAsStream("images/bulletL.gif"));
			bulletLD = ImageIO.read(ResourceMgr.class.getResourceAsStream("images/bulletL.gif"));
			bulletRU = ImageIO.read(ResourceMgr.class.getResourceAsStream("images/bulletR.gif"));
			bulletRD = ImageIO.read(ResourceMgr.class.getResourceAsStream("images/bulletR.gif"));
			
			explode = new BufferedImage[16];
			for(int i = 1; i <= 16; i++) {
				explode[i - 1] = ImageIO.read(ResourceMgr.class.getResourceAsStream("images/e" + i + ".gif"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
