package com.wwq.tank.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.wwq.tank.ResourceMgr;
import com.wwq.tank.TankMainFrame;
import com.wwq.tank.constant.Direction;

public class Tank extends Actor{
	
	public Tank(TankMainFrame frame) {
		this.frame = frame;
		this.live = true;
	}
	
	@Override
	public void paint(Graphics g) {
		Color oldColor = g.getColor();
		g.setColor(Color.ORANGE);
		
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
	
	public void fire(){
		Bullet bullet = new Bullet(x, y, dir);
		int nx = x + w / 2 - bullet.getW() / 2;
		int ny = y + h / 2 - bullet.getH() / 2;
		bullet.setX(nx);
		bullet.setY(ny);
		frame.actors.add(bullet);
	}
	
	private BufferedImage getImage() {
		if(Direction.LEFT == this.dir) {
			return ResourceMgr.tankL;
		} else if(Direction.RIGHT == this.dir) {
			return ResourceMgr.tankR;
		} else if(Direction.UP == this.dir) {
			return ResourceMgr.tankU;
		} else if(Direction.DOWN == this.dir) {
			return ResourceMgr.tankD;
		} else if(Direction.LEFT_UP == this.dir) {
			return ResourceMgr.tankLU;
		} else if(Direction.LEFT_DOWN == this.dir) {
			return ResourceMgr.tankLD;
		} else if(Direction.RIGHT_UP == this.dir) {
			return ResourceMgr.tankRU;
		} else if(Direction.RIGHT_DOWN == this.dir) {
			return ResourceMgr.tankRD;
		}
		return ResourceMgr.tankL;
	}
}