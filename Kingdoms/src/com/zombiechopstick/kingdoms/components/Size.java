package com.zombiechopstick.kingdoms.components;

import com.badlogic.gdx.math.Rectangle;

public class Size implements Component {

	private static final long serialVersionUID = 4543230070874873221L;
	private Rectangle bbox;
	private int x, y, width, height;
	
	public Size(int x, int y, int width, int height) {
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
		bbox = new Rectangle(x,y,width,height);
	}
	
	public Rectangle getBbox() {
		return bbox;
	}
	public void setBbox(Rectangle bbox) {
		this.bbox = bbox;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public String toString() {
		return x + "," + y + "," + height + "," + width;
	}
}
