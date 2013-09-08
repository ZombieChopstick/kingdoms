package com.zombiechopstick.kingdoms.components;

public class SnapBackPosition implements Component {

	private static final long serialVersionUID = -2647007760943528853L;
	
	private float x;
	private float y;
	
	public SnapBackPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}	
}
