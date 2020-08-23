package com.wwq.tank.entity;

import java.awt.Graphics;
import java.util.Enumeration;
import java.util.ListIterator;
import java.util.Stack;

import com.wwq.tank.ResourceMgr;
import com.wwq.tank.constant.Direction;
import com.wwq.tank.constant.Role;
import com.wwq.tank.constant.TankContant;
import com.wwq.tank.util.Util;

public class Enemy extends Player {
	
	protected int maxCount;
	private Stack<Tank> aliveTanks;

	public Enemy(int count, int maxCount, GameModel gm) {
		super(count, gm, ResourceMgr.badTank1);
		this.role = Role.ENEMY;
		this.maxCount = maxCount;
		aliveTanks = new Stack<Tank>();
		this.live = true;
		
		ListIterator<Tank> allTank = tanks.listIterator();
		while(allTank.hasNext()) {
			allTank.next().setImages(ResourceMgr.badTank1);
		}
	}
	
	@Override
	public void paint(Graphics g) {
		Enumeration<Tank> enums = aliveTanks.elements();
		while(enums.hasMoreElements()) {
			Tank tank = enums.nextElement();
			if(tank.isLive()) {
				tank.paint(g);
			} else {
				aliveTanks.remove(tank);
			}
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
		int x = Math.abs(Util.rand.nextInt()) % (TankContant.GAME_WIDTH - tank.getRect().width);
		tank.getRect().x = x;
		int y = Math.abs(Util.rand.nextInt()) % (TankContant.GAME_HEIGHT - tank.getRect().height);
		tank.getRect().y = y;
		randomTaskDir(tank);
	}

	public void randomTaskDir(Tank tank) {
		Direction[] dirs = Direction.values();
		int r = Math.abs(Util.rand.nextInt());
		int index = r % dirs.length;
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
