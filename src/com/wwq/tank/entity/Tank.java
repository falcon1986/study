package com.wwq.tank.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.wwq.tank.ResourceMgr;
import com.wwq.tank.TankMainFrame;
import com.wwq.tank.constant.Role;

public class Tank extends Actor{
	
	protected BufferedImage[] images;
	
	public Tank(TankMainFrame frame) {
		this.frame = frame;
		this.live = true;
		this.group = new Group();
		this.role = Role.TANK;
		images = ResourceMgr.baseTank;
	}
	
	public Tank(TankMainFrame frame, Group group) {
		this.frame = frame;
		this.live = true;
		this.group = group;
		this.role = Role.TANK;
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
		Bullet bullet = new Bullet(rect.x, rect.y, dir);
		int nx = rect.x + rect.width / 2 - bullet.getRect().width / 2;
		int ny = rect.y + rect.height / 2 - bullet.getRect().height / 2;
		Rectangle rect1 = bullet.getRect();
		rect1.x = nx;
		rect1.y = ny;
		bullet.setGroup(group);
		frame.actors.add(bullet);
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