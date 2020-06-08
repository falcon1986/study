package com.wwq.tank.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.wwq.tank.ResourceMgr;
import com.wwq.tank.TankMainFrame;
import com.wwq.tank.constant.Role;
import com.wwq.tank.fire.FireGenerator;
import com.wwq.tank.fire.impl.SingleFireGenerator;

public class Tank extends Actor{
	
	protected BufferedImage[] images;
	
	protected FireGenerator<Actor> fireGenerator;
	
	public Tank(TankMainFrame frame) {
		this(frame, new Group());
	}
	
	public Tank(TankMainFrame frame, Group group) {
		this.frame = frame;
		this.live = true;
		this.group = group;
		this.role = Role.TANK;
		images = ResourceMgr.baseTank;
		this.fireGenerator = new SingleFireGenerator();
	}
	
	public Tank(TankMainFrame frame, Group group, BufferedImage[] images) {
		this(frame, group);
		this.images = images;
	}
	
	@Override
	public void paint(Graphics g) {
		Color oldColor = g.getColor();
		g.setColor(Color.ORANGE);
		
		g.drawImage(getImage(), rect.x, rect.y, null);
		
		if(moving) {
			rect.x = dir.moveToX(rect.x, xSpeed, movingCount);
			rect.y = dir.moveToY(rect.y, xSpeed, movingCount);
		}
		
		if(checkOutBound()) {
			doingAfterBound();
		}
		
		g.setColor(oldColor);
	}
	
	public void fire(){
		if(this.fireGenerator == null) {
			return;
		}
		this.fireGenerator.fire(frame.actors, this);
	}
	
	private BufferedImage getImage() {
		try {
			return ResourceMgr.getImage(this.dir, images);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("images:" + images);
			return null;
		}
		
	}
}