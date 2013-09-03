package com.zombiechopstick.kingdoms;

public class DeckSystem implements ComponentSystem {

	private static final long serialVersionUID = -4937125231741557943L;
	private EntityManager manager;

	@Override
	public void update(int delta) {
		
	}

	@Override
	public void addManager(EntityManager manager) {
		this.manager = manager;
	}

}
