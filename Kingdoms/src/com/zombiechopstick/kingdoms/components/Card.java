package com.zombiechopstick.kingdoms.components;

public class Card implements Component {

	private static final long serialVersionUID = -7526390048540321685L;
	private boolean faceUp;
	public static enum PlayState {INDECK, INHAND, INPLAY, INDISCARD};
	private PlayState currentState;
	
	public Card(boolean faceUp, PlayState state) {
		this.faceUp = faceUp;
		currentState = state;
	}
	
	public boolean isFaceUp() {
		return faceUp;
	}
	
	public void setFaceUp(boolean faceUp) {
		this.faceUp = faceUp;
	}
	
	public PlayState getCurrentState() {
		return currentState;
	}
	
	public void setCurrentState(PlayState currentState) {
		this.currentState = currentState;
	};	
	
	public String toString() {
		return "Card";
	}
}
