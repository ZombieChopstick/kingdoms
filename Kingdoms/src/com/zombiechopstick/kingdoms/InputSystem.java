package com.zombiechopstick.kingdoms;

import java.util.List;
import java.util.UUID;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.zombiechopstick.kingdoms.components.Clickable;
import com.zombiechopstick.kingdoms.components.Dragable;
import com.zombiechopstick.kingdoms.components.Size;

public class InputSystem implements ComponentSystem {

	private static final long serialVersionUID = -7147488970589163317L;
	private EntityManager manager;
	
	public InputSystem(EntityManager manager) {
		this.manager = manager;
	}
	
	@Override
	public void update(float delta, SpriteBatch batch) {
		List<UUID> clickables = manager.getAllEntitiesWithComponent(Clickable.class);
		List<UUID> dragables = manager.getAllEntitiesWithComponent(Dragable.class);
		
		for(UUID uid : clickables) {
			Size size = manager.getComponent(uid, Size.class);
			Rectangle bbox = size.getBbox();
			if(bbox.contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()) && Gdx.input.justTouched()) {
				//System.out.println("clicking");
				Clickable click = manager.getComponent(uid, Clickable.class);
				click.setClicked(true);
			}
		}
		for(UUID uid : dragables) {
			Size size = manager.getComponent(uid, Size.class);
			Dragable drag = manager.getComponent(uid, Dragable.class);
			if(size != null) {
				Rectangle bbox = size.getBbox();
				if(bbox.contains(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()) && Gdx.input.isTouched()) {
					drag.setDragged(true);
					//System.out.println("dragging");
					break;
				}
				else {
					drag.setDragged(false);
				}
			}
		}
	}

	@Override
	public void addManager(EntityManager manager) {
		this.manager = manager;
	}
}
