package com.wwq.tank;

public class TankFrame {

	public static void main(String[] args) throws InterruptedException {
		TankMainFrame mainFrame = new TankMainFrame();
		
		while(true) {
			Thread.sleep(50);
			mainFrame.repaint();
		}
	}
}
