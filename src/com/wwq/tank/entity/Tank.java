package com.wwq.tank.entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.wwq.tank.ResourceMgr;
import com.wwq.tank.TankMainFrame;
import com.wwq.tank.constant.Role;
import com.wwq.tank.constant.TankContant;
import com.wwq.tank.fire.FireGenerator;
import com.wwq.tank.fire.impl.SingleFireGenerator;

public class Tank extends Actor{
	
	protected FireGenerator<Actor> fireGenerator;
	
	public Tank(TankMainFrame frame) {
		this(frame, new Group());
	}
	
	public Tank(TankMainFrame frame, Group group) {
		this(frame, group, ResourceMgr.baseTank, new SingleFireGenerator());
	}
	
	public Tank(TankMainFrame frame, Group group, BufferedImage[] images, FireGenerator<Actor> fireGenerator) {
		super();
		rect = new Rectangle(TankContant.TANK_X, TankContant.TANK_Y, TankContant.TANK_W, TankContant.TANK_H);
		this.frame = frame;
		this.group = group;
		this.images = images;
		this.fireGenerator = fireGenerator;
		this.live = true;
		this.role = Role.TANK;
	}
	
	public void fire(){
		if(this.fireGenerator == null) {
			return;
		}
		this.fireGenerator.fire(frame.actors, this);
	}
	
	@Override
	protected BufferedImage getImage() {
		try {
			return ResourceMgr.getImage(this.dir, images);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public FireGenerator<Actor> getFireGenerator() {
		return fireGenerator;
	}

	public void setFireGenerator(FireGenerator<Actor> fireGenerator) {
		this.fireGenerator = fireGenerator;
	}
}