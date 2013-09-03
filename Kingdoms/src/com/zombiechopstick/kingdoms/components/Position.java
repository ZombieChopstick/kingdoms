package com.zombiechopstick.kingdoms.components;

public class Position implements Component {

	private static final long serialVersionUID = -7477554226071032048L;
	
	private float x;
	private float y;
	
	public Position(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
}
