package com.zombiechopstick.kingdoms.components;

public class Owner implements Component {

	private static final long serialVersionUID = -3130778107821262650L;
	private String playerName;
	
	public Owner(String player) {
		playerName = player;
	}
	
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	public String toString() {
		return playerName;
	}
	
}
