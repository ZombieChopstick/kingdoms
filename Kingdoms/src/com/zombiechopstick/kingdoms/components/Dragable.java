package com.zombiechopstick.kingdoms.components;

public class Dragable implements Component {

	private static final long serialVersionUID = -4948645535284066832L;
	private boolean dragged;
	
	public boolean isDragged() {
		return dragged;
	}
	public void setDragged(boolean dragged) {
		this.dragged = dragged;
	}
	
	public String toString() {
		return "Dragable";
	}
}
