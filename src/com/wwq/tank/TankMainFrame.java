package com.wwq.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.wwq.tank.constant.Direction;
import com.wwq.tank.constant.TankContant;
import com.wwq.tank.entity.GameModel;
import com.wwq.tank.entity.Tank;

public class TankMainFrame extends Frame {

	private static final long serialVersionUID = 1815074540061535275L;

	private Image offScreenImage;
	
	private GameModel gm;
	
	public TankMainFrame() {
		this.setSize(TankContant.GAME_WIDTH, TankContant.GAME_HEIGHT);
		this.setResizable(false);
		this.setTitle("Tank War");
		this.setVisible(true);
		
		gm = new GameModel();
		gm.init();
		
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
		if(gm == null) {
			return;
		}
		gm.paint(g);
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
				Tank tank = gm.getMyTank();
				if(tank == null) {
					return;
				}
				tank.fire();
				break;
			}
			
			setDir();
		}
		
		private void setDir() {
			Tank tank = gm.getMyTank();
			if(tank == null) {
				return;
			}
			tank.setMoving(keyUp || keyDown || keyLeft || keyRight);
			tank.setDir(Direction.parseDirection(keyUp, keyDown, keyLeft, keyRight));
		}
	}
}
