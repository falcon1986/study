package com.wwq.tank.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.wwq.tank.ResourceMgr;

public class Explode extends Actor {
	
	protected int index;

	public Explode(int x, int y) {
		super();
		this.live = true;
		this.rect.x = x;
		this.rect.y = y;
		this.moving = false;
		this.group = new Group();
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
	
	private BufferedImage getImage() {
		return ResourceMgr.explode[index];
	}
}