package com.wwq.tank.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Stack;

import com.wwq.tank.ResourceMgr;
import com.wwq.tank.constant.Role;
import com.wwq.tank.fire.impl.SingleFireGenerator;

public class Player extends Actor {

	protected Stack<Tank> tanks;
	protected Tank curTank;
	
	public Player(int count, GameModel gm, BufferedImage[] images) {
		this.group = new Group();
		this.role = Role.PLAY;
		this.gm = gm;
		tanks = new Stack<Tank>();
		for(int i = 0; i < count; i++) {
			Tank tank = new Tank(gm, group, images, new SingleFireGenerator());
			tanks.push(tank);
		}
		this.live = true;
	}
	
	public Player(int count, GameModel gm) {
		this(count, gm, ResourceMgr.goodTank1);
	}

	public Tank getCurTank() {
		if(curTank == null) {
			if(tanks.size() > 0) {
				curTank = tanks.pop();
			}
		} else {
			if(!curTank.isLive()) {
				curTank = null;
				return getCurTank();
			}
		}
		return curTank;
	}

	public void setCurTank(Tank curTank) {
		this.curTank = curTank;
	}
	
	@Override
	public void paint(Graphics g) {
		Tank tank = getCurTank();
		if(tank != null) {
			tank.paint(g);
		}
	}

	public Stack<Tank> getTanks() {
		return tanks;
	}

	public void setTanks(Stack<Tank> tanks) {
		this.tanks = tanks;
	}

	@Override
	protected BufferedImage getImage() {
		return null;
	}
}
