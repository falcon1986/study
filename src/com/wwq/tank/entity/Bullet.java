package com.wwq.tank.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.wwq.tank.ResourceMgr;
import com.wwq.tank.constant.Direction;
import com.wwq.tank.constant.TankContant;

public class Bullet extends Actor {
	
	protected Color color;
	
	@Override
	public void paint(Graphics g) {
		Color oldColor = g.getColor();
		g.setColor(color);
		
		g.drawImage(getImage(), x, y, null);
		
		if(moving) {
			x = dir.moveToX(x, xSpeed, movingCount);
			y = dir.moveToY(y, ySpeed, movingCount);
		}
		
		if(checkOutBound()) {
			doingAfterBound();
		}
		
		g.setColor(oldColor);
	}
	
	@Override
	protected void doingAfterBound() {
		this.live = false;
	}

	public Bullet(int x, int y, Direction dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.w = TankContant.BULLET_W;
		this.h = TankContant.BULLET_H;
		this.xSpeed = TankContant.BULLET_SPEED;
		this.ySpeed = TankContant.BULLET_SPEED;
		this.moving = true;
		this.color = Color.RED;
		this.live = true;
	}
	
	private BufferedImage getImage() {
		if(Direction.LEFT == this.dir) {
			return ResourceMgr.bulletL;
		} else if(Direction.RIGHT == this.dir) {
			return ResourceMgr.bulletR;
		} else if(Direction.UP == this.dir) {
			return ResourceMgr.bulletU;
		} else if(Direction.DOWN == this.dir) {
			return ResourceMgr.bulletD;
		} else if(Direction.LEFT_UP == this.dir) {
			return ResourceMgr.bulletLU;
		} else if(Direction.LEFT_DOWN == this.dir) {
			return ResourceMgr.bulletLD;
		} else if(Direction.RIGHT_UP == this.dir) {
			return ResourceMgr.bulletRU;
		} else if(Direction.RIGHT_DOWN == this.dir) {
			return ResourceMgr.bulletRD;
		}
		return ResourceMgr.bulletL;
	}
}