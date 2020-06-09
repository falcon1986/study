package com.wwq.tank.entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.wwq.tank.ResourceMgr;
import com.wwq.tank.constant.Direction;
import com.wwq.tank.constant.Role;
import com.wwq.tank.constant.TankContant;

public class Bullet extends Actor {
	
	public Bullet() {
		this.role = Role.BULLET;
	}
	
	public Bullet(int x, int y, Direction dir, Group group, BufferedImage[] images) {
		this();
		rect = new Rectangle(x, y, TankContant.BULLET_W, TankContant.BULLET_H);
		this.dir = dir;
		this.group = group;
		this.images = images;
		this.xSpeed = TankContant.BULLET_SPEED;
		this.ySpeed = TankContant.BULLET_SPEED;
		this.moving = true;
		this.live = true;
	}
	
	public Bullet(int x, int y, Direction dir, Group group) {
		this(x, y, dir, group, ResourceMgr.bullet);
	}
	
	@Override
	protected void doingAfterBound() {
		this.live = false;
	}

	@Override
	protected BufferedImage getImage() {
		return ResourceMgr.getImage(this.dir, images);
	}
}