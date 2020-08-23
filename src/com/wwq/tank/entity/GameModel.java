package com.wwq.tank.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Stack;

import com.wwq.tank.EnemyThread;
import com.wwq.tank.constant.Role;
import com.wwq.tank.constant.TankContant;
import com.wwq.tank.util.ExplodeUtil;

public class GameModel {

	private Player play;
	
	private Enemy enemy;
	
	private List<Actor> actors;
	
	public void init() {
		actors = new ArrayList<Actor>();
		
		//初始化我方信息
		play = new Player(3, this);
		actors.add(play);
		
		//初始化敌方信息
		enemy = new Enemy(10, 3, this);
		actors.add(enemy);
		new EnemyThread(enemy).start();
	}
	
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.YELLOW);
		g.drawString("集合数量：" + (actors != null ?actors.size() : 0), 50, 50);
		g.setColor(c);
		
		//碰撞检测
		collisionChecking();
		
		//边缘检测
		boundChecking();
		
		if(actors != null) {
			for(int i = 0; i < actors.size(); i++) {
				Actor actor = actors.get(i);
				if(actor.isLive()) {
					actor.paint(g);
				} else {
					actors.remove(i);
				}
			}
		}
	}
	
	private void boundChecking() {
		for(int i = 0; i < actors.size(); i++) {
			Actor actor = actors.get(i);
			if(Role.PLAY == actor.getRole()) {
				Actor ac = ((Player)actor).getCurTank();
				if(ac == null) {
					continue;
				}
				boundChecking(ac, false);
			} else if(Role.ENEMY == actor.getRole()) {
				Stack<Tank> tanks = ((Enemy)actor).getAliveTanks();
				if(tanks == null || tanks.size() == 0) {
					continue;
				}
				Enumeration<Tank> tankIter = tanks.elements();
				while(tankIter.hasMoreElements()) {
					boundChecking(tankIter.nextElement(), true);
				}
			}
		}
	}
	
	private void boundChecking(Actor actor, boolean changeDir) {
		boolean flag = false;
		if(actor.getRect().x < 0) {
			actor.getRect().x = 0;
			flag = true;
		} else if(actor.getRect().x + actor.getRect().width > TankContant.GAME_WIDTH) {
			actor.getRect().x = TankContant.GAME_WIDTH - actor.getRect().width;
			flag = true;
		}
		
		if(actor.getRect().y < 30) {
			actor.getRect().y = 30;
			flag = true;
		} else if(actor.getRect().y + actor.getRect().height > TankContant.GAME_HEIGHT) {
			actor.getRect().y = TankContant.GAME_HEIGHT - actor.getRect().height;
			flag = true;
		}
		if(flag && changeDir) {
			actor.randomDir();
		}
	}
	
	private void collisionChecking() {
		if(actors == null) {
			return;
		}
		for(int i = 0; i < actors.size(); i++) {
			Actor actor = actors.get(i);
			for(int j = 0; j < actors.size(); j++) {
				Actor actor1 = actors.get(j);
				if(actor.getId().equals(actor1.getId())) {
					continue;
				}
				if(!actor.isLive()) {
					continue;
				}
				if(!actor1.isLive()) {
					continue;
				}
				if(actor.getGroup().getId().equals(actor1.getGroup().getId())) { //队友伤害免疫
					continue;
				}
				Actor ac1 = actor;
				Actor ac2 = actor1;
				List<Actor> acList1 = new ArrayList<Actor>();
				List<Actor> acList2 = new ArrayList<Actor>();
				if(Role.PLAY == ac1.getRole()) {
					ac1 = ((Player)ac1).getCurTank();
					if(ac1 == null || !ac1.isLive()) {
						continue;
					}
					acList1.add(ac1);
				} else if(Role.ENEMY == ac1.getRole()) {
					Stack<Tank> tanks = ((Enemy)ac1).getAliveTanks();
					if(tanks == null || tanks.size() == 0) {
						continue;
					}
					acList1.addAll(tanks);
				} else {
					acList1.add(ac1);
				}
				if(Role.PLAY == ac2.getRole()) {
					ac2 = ((Player)ac2).getCurTank();
					if(ac2 == null || !ac2.isLive()) {
						continue;
					}
					acList2.add(ac2);
				} else if(Role.ENEMY == ac2.getRole()) {
					Stack<Tank> tanks = ((Enemy)ac2).getAliveTanks();
					if(tanks == null || tanks.size() == 0) {
						continue;
					}
					acList2.addAll(tanks);
				} else {
					acList2.add(ac2);
				}
				for(Actor a1 : acList1) {
					for(Actor a2 : acList2) {
						collisionChecking(a1, a2);
					}
				}
			}
		}
	}
	
	private void collisionChecking(Actor ac1, Actor ac2) {
		if(Role.TANK == ac1.getRole() && Role.BULLET == ac2.getRole()
				|| Role.TANK == ac2.getRole() && Role.BULLET == ac1.getRole()
				|| Role.BULLET == ac2.getRole() && Role.BULLET == ac1.getRole()
		) { //子弹和坦克碰撞、子弹和子弹碰撞
			if(ac1.getRect().intersects(ac2.getRect())) { //碰撞
				ac1.setLive(false);
				ac2.setLive(false);
				
				//爆炸效果
				int x = ac1.getRect().x + ac1.getRect().width / 2;
				int y = ac1.getRect().y + ac1.getRect().height / 2;
				ExplodeUtil.showExplode(actors, x, y);
			}
		} else if(Role.TANK == ac1.getRole() && Role.TANK == ac2.getRole()){
//			if(actor.getRect().intersects(actor1.getRect())) { //碰撞
//				
//			}
		}
		
	}
	
	public Tank getMyTank() {
		if(!play.isLive()) {
			return null;
		}
		return play.getCurTank();
	}

	public Player getPlay() {
		return play;
	}

	public void setPlay(Player play) {
		this.play = play;
	}

	public Enemy getEnemy() {
		return enemy;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}
}
