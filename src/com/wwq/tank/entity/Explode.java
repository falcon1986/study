package com.wwq.tank.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.wwq.tank.ResourceMgr;
import com.wwq.tank.constant.TankContant;
import com.wwq.tank.util.Audio;

public class Explode extends Actor {
	
	protected int index;

	public Explode(int x, int y) {
		super();
		rect = new Rectangle(x, y, TankContant.EXPLODE_W, TankContant.EXPLODE_H);
		this.live = true;
		this.moving = false;
		this.group = new Group();
		
		//爆炸音效
		new Thread(() -> new Audio("audio/explode.wav").play()).start();
	}
	
	@Override
	public void paint(Graphics g) {
		if(index >= ResourceMgr.explode.length) {
			this.live = false;
			return;
		}
		
		g.drawImage(getImage(), rect.x, rect.y, null);
		index++;
	}
	
	@Override
	protected BufferedImage getImage() {
		return ResourceMgr.explode[index];
	}
}