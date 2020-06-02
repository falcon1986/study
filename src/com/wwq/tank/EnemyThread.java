package com.wwq.tank;

import java.util.Enumeration;

import com.wwq.tank.entity.Enemy;
import com.wwq.tank.entity.Tank;

public class EnemyThread extends Thread {
	
	private Enemy enemy;

	public EnemyThread(Enemy enemy) {
		this.enemy = enemy;
	}
	
	@Override
	public void run() {
		if(enemy == null || enemy.getTanks() == null) {
			return;
		}
		while(true) {
			if(enemy.getTanks().size() > 0) {
				if(enemy.getAliveTanks().size() < enemy.getMaxCount()) {
					//生成新的
					enemy.enter();
				}
			}
			
			Enumeration<Tank> enums = enemy.getAliveTanks().elements();
			while(enums.hasMoreElements()) {
				Tank t = enums.nextElement();
				if(t.getMovingCount() >= 100) {
					enemy.randomTaskDir(enums.nextElement());
				}
			}
			
			if(enemy.getTanks().size() == 0 && enemy.getAliveTanks().size() == 0) {
				break;
			}
		}
	}
}
