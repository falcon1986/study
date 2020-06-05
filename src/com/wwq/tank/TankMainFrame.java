package com.wwq.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.wwq.tank.constant.Direction;
import com.wwq.tank.constant.Role;
import com.wwq.tank.constant.TankContant;
import com.wwq.tank.entity.Actor;
import com.wwq.tank.entity.Enemy;
import com.wwq.tank.entity.Player;
import com.wwq.tank.entity.Tank;
import com.wwq.tank.util.ExplodeUtil;

public class TankMainFrame extends Frame {

	private static final long serialVersionUID = 1815074540061535275L;

	private Image offScreenImage;
	
	private Player play;
	
	private Enemy enemy;
	
	public List<Actor> actors;

	
	public TankMainFrame() {
		this.setSize(TankContant.GAME_WIDTH, TankContant.GAME_HEIGHT);
		this.setResizable(false);
		this.setTitle("Tank War");
		this.setVisible(true);
		
		actors = new ArrayList<Actor>();
		
		//初始化我方信息
		play = new Player(3, this);
		actors.add(play);
		
		//初始化敌方信息
		enemy = new Enemy(10, 3, this);
		actors.add(enemy);
		new EnemyThread(enemy).start();
		
		this.addKeyListener(new MyKeyListener());
		
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	@Override
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(TankContant.GAME_WIDTH, TankContant.GAME_HEIGHT);
		}
		
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, TankContant.GAME_WIDTH, TankContant.GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.YELLOW);
		g.drawString("集合数量：" + actors.size(), 50, 50);
		g.setColor(c);
		
		//碰撞检测
		collisionChecking();
		
		for(int i = 0; i < actors.size(); i++) {
			Actor actor = actors.get(i);
			if(actor.isLive()) {
				actor.paint(g);
			} else {
				actors.remove(i);
			}
		}
	}
	
	private void collisionChecking() {
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
	
	class MyKeyListener extends KeyAdapter{
		
		boolean keyUp = false;
		boolean keyRight = false;
		boolean keyDown = false;
		boolean keyLeft = false;
		boolean moving = false;
		
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_UP: //上
				keyUp = true;
				break;
			case KeyEvent.VK_RIGHT: //右
				keyRight = true;
				break;
			case KeyEvent.VK_DOWN: //下
				keyDown = true;
				break;
			case KeyEvent.VK_LEFT: //左
				keyLeft = true;
				break;
			}
			
			setDir();
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_UP: //上
				keyUp = false;
				break;
			case KeyEvent.VK_RIGHT: //右
				keyRight = false;
				break;
			case KeyEvent.VK_DOWN: //下
				keyDown = false;
				break;
			case KeyEvent.VK_LEFT: //左
				keyLeft = false;
				break;
			case KeyEvent.VK_CONTROL: //ctrl
				if(play == null || play.getCurTank() == null) {
					return;
				}
				play.getCurTank().fire();
				break;
			}
			
			setDir();
		}
		
		private void setDir() {
			if(play == null || play.getCurTank() == null) {
				return;
			}
			play.getCurTank().setMoving(keyUp || keyDown || keyLeft || keyRight);
			play.getCurTank().setDir(Direction.parseDirection(keyUp, keyDown, keyLeft, keyRight));
		}
	}
}
