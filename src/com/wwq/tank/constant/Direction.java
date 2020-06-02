package com.wwq.tank.constant;

public enum Direction {
	
	LEFT(false, false, true, false)
	, RIGHT(false, false, false, true)
	, UP(true, false, false, false)
	, DOWN(false, true, false, false)
	, LEFT_UP(true, false, true, false)
	, RIGHT_UP(true, false, false, true)
	, LEFT_DOWN(false, true, true, false)
	, RIGHT_DOWN(false, true, false, true)
	;
	
	public static Direction parseDirection(boolean up, boolean down, boolean left, boolean right) {
		for(Direction dir : Direction.values()) {
			if(dir.down == down && dir.up == up && dir.left == left && dir.right == right) {
				return dir;
			}
		}
		
		return null;
	}
	
	public int moveToX(int x, int speed, int movingCount) {
		if(this.left) {
			x -= varSpeed(speed, movingCount);
		} 
		if(this.right) {
			x += varSpeed(speed, movingCount);
		}
		return x;
	}
	
	public int moveToY(int y, int speed, int movingCount) {
		if(this.up) {
			y -= varSpeed(speed, movingCount);
		} 
		if(this.down) {
			y += varSpeed(speed, movingCount);
		}
		return y;
	}
	
	private int varSpeed(int speed, int movingCount) {
		if(movingCount <= 0) {
			return speed;
		}
		int xSpeed = movingCount * 1;
		if(xSpeed > speed) {
			xSpeed = speed;
		}
		return speed + xSpeed;
	}

	private Direction(boolean up, boolean down, boolean left, boolean right) {
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
	}
	
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	
	public boolean isUp() {
		return up;
	}
	public void setUp(boolean up) {
		this.up = up;
	}
	public boolean isDown() {
		return down;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	public boolean isLeft() {
		return left;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	public boolean isRight() {
		return right;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
}