package com.wwq.tank;

import com.wwq.tank.util.Audio;

public class TankFrame {

	public static void main(String[] args) throws InterruptedException {
		TankMainFrame mainFrame = new TankMainFrame();
		
		new Thread(() -> new Audio("audio/war1.wav").loop()).start();
		
		while(true) {
			Thread.sleep(50);
			mainFrame.repaint();
		}
	}
}
