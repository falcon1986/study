package com.wwq.tank.entity;

import java.awt.Graphics;
import java.util.Enumeration;
import java.util.Random;
import java.util.Stack;

import com.wwq.tank.TankMainFrame;
import com.wwq.tank.constant.Direction;
import com.wwq.tank.constant.TankContant;

public class Enemy extends Player {
	
	protected int maxCount;
	private Stack<Tank> aliveTanks;

	public Enemy(int count, int maxCount, TankMainFrame frame) {
		super(count, frame);
		this.maxCount = maxCount;
		aliveTanks = new Stack<Tank>();
		this.live = true;
	}
	
	@Override
	public void paint(Graphics g) {
		Enumeration<Tank> enums = aliveTanks.elements();
		while(enums.hasMoreElements()) {
			enums.nextElement().paint(g);
		}
	}

	public int getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}
	
	public void enter() {
		if(this.tanks == null || this.tanks.size() == 0) {
			return;
		}
		
		Tank tank = this.tanks.pop();
		randomTask(tank);
		aliveTanks.push(tank);
	}
	
	public void randomTask(Tank tank){
		Random r = new Random();
		int x = Math.abs(r.nextInt()) % (TankContant.GAME_WIDTH - tank.getW());
		tank.setX(x);
		int y = Math.abs(r.nextInt()) % (TankContant.GAME_HEIGHT - tank.getH());
		tank.setY(y);
		randomTaskDir(tank);
	}

	public void randomTaskDir(Tank tank) {
		Random r = new Random();
		Direction[] dirs = Direction.values();
		int index = Math.abs(r.nextInt()) % dirs.length;
		tank.setMoving(true);
//		tank.setxSpeed(1);
//		tank.setySpeed(1);
		tank.setDir(dirs[index]);
		this.movingCount = 0;
	}

	public Stack<Tank> getAliveTanks() {
		return aliveTanks;
	}

	public void setAliveTanks(Stack<Tank> aliveTanks) {
		this.aliveTanks = aliveTanks;
	}

}
