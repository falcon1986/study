package com.wwq.tank.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.UUID;

import com.wwq.tank.TankMainFrame;
import com.wwq.tank.constant.Direction;
import com.wwq.tank.constant.Role;
import com.wwq.tank.constant.TankContant;
import com.wwq.tank.util.Util;

public abstract class Actor {
	/** 角色ID **/
	protected String id;
	/** 角色类型 **/
	protected Role role;
	/** 位置 **/
	protected Rectangle rect = null;
	/** 当前方向 **/
	protected Direction dir = Direction.DOWN;
	/** x轴速度 **/
	protected int xSpeed = TankContant.TANK_SPEED;
	/** y轴速度 **/
	protected int ySpeed = TankContant.TANK_SPEED;
	/** 是否移动 **/
	protected boolean moving = false;
	/** 当前方向移动次数 **/
	protected int movingCount = 0;
	/** 是否存活 **/
	protected boolean live;
	/** 关联主窗口 **/
	protected TankMainFrame frame;
	/** 当前角色所属组别 **/
	protected Group group;
	/** 当前角色关联图片集合 **/
	protected BufferedImage[] images;
	
	public Actor() {
		id = UUID.randomUUID().toString().replaceAll("-","");
	}
	
	public void paint(Graphics g){
		g.drawImage(getImage(), rect.x, rect.y, null);
		
		if(moving) {
			rect.x = dir.moveToX(rect.x, xSpeed, movingCount);
			rect.y = dir.moveToY(rect.y, xSpeed, movingCount);
		}
		
		if(checkOutBound()) {
			doingAfterBound();
		}
	}
	
	protected void doingAfterBound() { }
	
	protected abstract BufferedImage getImage();

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
	
	public void randomDir() {
		Direction[] dirs = Direction.values();
		int r = Math.abs(Util.rand.nextInt());
		int index = r % dirs.length;
		this.setMoving(true);
//		tank.setxSpeed(1);
//		tank.setySpeed(1);
		this.setDir(dirs[index]);
		this.movingCount = 0;
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

	public BufferedImage[] getImages() {
		return images;
	}

	public void setImages(BufferedImage[] images) {
		this.images = images;
	}
}
