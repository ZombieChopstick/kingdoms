package com.zombiechopstick.kingdoms.components;

public class Clickable implements Component {

	private static final long serialVersionUID = 6074246985661738966L;
	private boolean clicked;
	
	public boolean isClicked() {
		return clicked;
	}
	public void setClicked(boolean clicked) {
		this.clicked = clicked;
	}
	
	public String toString() {
		return "Clickable";
	}
}
