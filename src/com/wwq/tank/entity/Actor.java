package com.wwq.tank.entity;

import java.awt.Graphics;

import com.wwq.tank.TankMainFrame;
import com.wwq.tank.constant.Direction;
import com.wwq.tank.constant.TankContant;

public class Actor {
	protected int x = TankContant.TANK_X;
	protected int y = TankContant.TANK_Y;
	protected int w = TankContant.TANK_W;
	protected int h = TankContant.TANK_H;
	protected Direction dir = Direction.DOWN;
	protected int xSpeed = TankContant.TANK_SPEED;
	protected int ySpeed = TankContant.TANK_SPEED;
	protected boolean moving = false;
	protected int movingCount = 0;
	protected boolean live;
	protected TankMainFrame frame;
	
	public void paint(Graphics g){
		g.fillRect(x, y, w, h);
		
		if(moving) {
			x = dir.moveToX(x, xSpeed, movingCount);
			y = dir.moveToY(y, ySpeed, movingCount);
		}
		
		if(checkOutBound()) {
			doingAfterBound();
		}
	}
	
	protected void doingAfterBound() {
		
	}

	protected boolean checkOutBound() {
		if(x + w < 0) { //超出左侧边界
			return true;
		}
		if(x > TankContant.GAME_WIDTH) { //超出右侧边界
			return true;
		}
		if(y + h < 0) { //超出上侧边界
			return true;
		}
		if(y > TankContant.GAME_HEIGHT) { //超出下侧边界
			return true;
		}
		return false;
	}

	public void setDir(Direction dir) {
		if(dir != null) {
			if(this.dir != dir) {
				movingCount = 0;
			} else {
				movingCount++;
			}
			this.dir = dir;
		}
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public Direction getDir() {
		return dir;
	}

	public int getxSpeed() {
		return xSpeed;
	}
	public void setxSpeed(int xSpeed) {
		this.xSpeed = xSpeed;
	}
	public int getySpeed() {
		return ySpeed;
	}
	public void setySpeed(int ySpeed) {
		this.ySpeed = ySpeed;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public int getMovingCount() {
		return movingCount;
	}

	public void setMovingCount(int movingCount) {
		this.movingCount = movingCount;
	}
}
