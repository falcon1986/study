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

import com.wwq.tank.constant.Direction;
import com.wwq.tank.constant.TankContant;
import com.wwq.tank.entity.Actor;
import com.wwq.tank.entity.Enemy;
import com.wwq.tank.entity.Player;

public class TankMainFrame extends Frame {
	
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
		
		playChange(g);
		for(int i = 0; i < actors.size(); i++) {
			Actor actor = actors.get(i);
			if(actor.isLive()) {
				actor.paint(g);
			} else {
				actors.remove(i);
			}
		}
	}
	
	private void playChange(Graphics g) {
		if(play == null || play.getCurTank() == null) {
			return;
		}
		play.getCurTank().paint(g);
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
