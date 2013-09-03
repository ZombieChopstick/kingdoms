package com.zombiechopstick.kingdoms;

import java.util.List;
import java.util.UUID;

import com.zombiechopstick.kingdoms.components.Clickable;
import com.zombiechopstick.kingdoms.components.Component;
import com.zombiechopstick.kingdoms.components.Renderable;

public class InputSystem implements ComponentSystem {

	private static final long serialVersionUID = -7147488970589163317L;
	private EntityManager manager;
	
	public InputSystem(EntityManager manager) {
		this.manager = manager;
	}
	
	@Override
	public void update(int delta) {
		List<UUID> entities = manager.getAllEntitiesWithComponent(Renderable.class);
		for(UUID uid : entities) {
			@SuppressWarnings("unused")
			Component c = manager.getComponent(uid, Clickable.class);
			//if mouse clicked inside entity, perform event
		}
	}

	@Override
	public void addManager(EntityManager manager) {
		this.manager = manager;
	}

}
