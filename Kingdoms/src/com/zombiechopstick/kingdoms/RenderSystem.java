package com.zombiechopstick.kingdoms;

import java.util.List;
import java.util.UUID;

import com.zombiechopstick.kingdoms.components.Name;
import com.zombiechopstick.kingdoms.components.Renderable;

public class RenderSystem implements ComponentSystem {

	private static final long serialVersionUID = -126108005435048472L;
	private EntityManager manager;
	
	public RenderSystem(EntityManager manager) {
		this.manager = manager;
	}
	
	@Override
	public void update(int delta) {
		List<UUID> entities = manager.getAllEntitiesWithComponent(Renderable.class);
		for(UUID uid : entities) {
			Name name = manager.getComponent(uid, Name.class);
			System.out.println(name.getName());
		}
	}

	@Override
	public void addManager(EntityManager manager) {
		this.manager = manager;
	}
	
}
