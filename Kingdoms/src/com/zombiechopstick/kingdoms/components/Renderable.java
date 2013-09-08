package com.zombiechopstick.kingdoms.components;

public class Renderable implements Component{

	private static final long serialVersionUID = -2842071941715621242L;
	private String sprite;
	
	public Renderable(String sprite) {
		this.sprite = sprite;
	}
	
	public String getSprite() {
		return sprite;
	}
	public void setSprite(String sprite) {
		this.sprite = sprite;
	}

}
