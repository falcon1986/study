package com.wwq.tank.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.UUID;

import com.wwq.tank.TankMainFrame;
import com.wwq.tank.constant.Direction;
import com.wwq.tank.constant.Role;
import com.wwq.tank.constant.TankContant;

public class Actor {
	protected String id;
	protected Role role;
	
	protected Rectangle rect = null;
	protected Direction dir = Direction.DOWN;
	protected int xSpeed = TankContant.TANK_SPEED;
	protected int ySpeed = TankContant.TANK_SPEED;
	protected boolean moving = false;
	protected int movingCount = 0;
	protected boolean live;
	protected TankMainFrame frame;
	protected Group group;
	
	public Actor() {
		id = UUID.randomUUID().toString().replaceAll("-","");
		rect = new Rectangle(TankContant.TANK_X, TankContant.TANK_Y, TankContant.TANK_W, TankContant.TANK_H);
	}
	
	public void paint(Graphics g){
		g.fillRect(rect.x, rect.y, rect.width, rect.height);
		
		if(moving) {
			rect.x = dir.moveToX(rect.x, xSpeed, movingCount);
			rect.y = dir.moveToY(rect.y, xSpeed, movingCount);
		}
		
		if(checkOutBound()) {
			doingAfterBound();
		}
	}
	
	protected void doingAfterBound() {
		
	}

	protected boolean checkOutBound() {
		if(rect.x + rect.width < 0) { //超出左侧边界
			return true;
		}
		if(rect.x > TankContant.GAME_WIDTH) { //超出右侧边界
			return true;
		}
		if(rect.y + rect.height < 0) { //超出上侧边界
			return true;
		}
		if(rect.y > TankContant.GAME_HEIGHT) { //超出下侧边界
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
	

	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
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

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
