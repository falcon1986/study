package com.wwq.tank.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Stack;

import com.wwq.tank.ResourceMgr;
import com.wwq.tank.TankMainFrame;
import com.wwq.tank.constant.Role;

public class Player extends Actor {

	protected Stack<Tank> tanks;
	protected Tank curTank;
	protected TankMainFrame frame;
	protected Random rand = null;
	
	public Player(int count, TankMainFrame frame, BufferedImage[] images) {
		rand = new Random();
		this.group = new Group();
		this.role = Role.PLAY;
		this.frame = frame;
		tanks = new Stack<Tank>();
		for(int i = 0; i < count; i++) {
			Tank tank = new Tank(frame, group, images);
			tanks.push(tank);
		}
		this.live = true;
	}
	
	public Player(int count, TankMainFrame frame) {
		this(count, frame, ResourceMgr.goodTank1);
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
}
