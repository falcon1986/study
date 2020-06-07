package com.wwq.tank.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.wwq.tank.ResourceMgr;
import com.wwq.tank.constant.Direction;
import com.wwq.tank.constant.Role;
import com.wwq.tank.constant.TankContant;

public class Bullet extends Actor {
	
	public Bullet() {
		this.group = new Group();
		this.role = Role.BULLET;
	}
	
	public Bullet(int x, int y, Direction dir) {
		this();
		rect.x = x;
		rect.y = y;
		this.dir = dir;
		rect.width = TankContant.BULLET_W;
		rect.height = TankContant.BULLET_H;
		this.xSpeed = TankContant.BULLET_SPEED;
		this.ySpeed = TankContant.BULLET_SPEED;
		this.moving = true;
		this.live = true;
		images = ResourceMgr.bullet;
	}
	
	@Override
	public void paint(Graphics g) {
		
		g.drawImage(getImage(), rect.x, rect.y, null);
		
		if(moving) {
			rect.x = dir.moveToX(rect.x, xSpeed, movingCount);
			rect.y = dir.moveToY(rect.y, xSpeed, movingCount);
		}
		
		if(checkOutBound()) {
			doingAfterBound();
		}
	}
	
	@Override
	protected void doingAfterBound() {
		this.live = false;
	}

	private BufferedImage getImage() {
		return ResourceMgr.getImage(this.dir, images);
	}
}