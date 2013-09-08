package com.zombiechopstick.kingdoms;

import com.badlogic.gdx.Game;

public class KingdomsGame extends Game {
	String player = null;
	public KingdomsGame(String player) {
		this.player = player;
	}
	
	@Override
	public void create() {
		setScreen(new TestScreen(player));		
	}
	
	public void dispose() {
		System.out.println("Exiting");
	}
}
