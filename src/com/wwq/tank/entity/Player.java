package com.wwq.tank.entity;

import java.awt.Graphics;
import java.util.Stack;

import com.wwq.tank.TankMainFrame;

public class Player extends Actor {

	protected Stack<Tank> tanks;
	protected Tank curTank;
	protected TankMainFrame frame;
	
	public Player(int count, TankMainFrame frame) {
		this.frame = frame;
		tanks = new Stack<Tank>();
		for(int i = 0; i < count; i++) {
			tanks.push(new Tank(frame));
		}
		this.live = true;
	}

	public Tank getCurTank() {
		if(curTank == null) {
			if(tanks.size() > 0) {
				curTank = tanks.pop();
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
