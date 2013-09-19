package com.zombiechopstick.kingdoms;

import java.util.List;
import java.util.UUID;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zombiechopstick.kingdoms.components.BoardHex;
import com.zombiechopstick.kingdoms.components.Clickable;
import com.zombiechopstick.kingdoms.components.Position;
import com.zombiechopstick.kingdoms.components.Renderable;
import com.zombiechopstick.kingdoms.components.Size;

public class BoardSystem implements ComponentSystem {

	private static final long serialVersionUID = -7803752834342404852L;
	private EntityManager manager;
	
	public BoardSystem(EntityManager manager) {
		addManager(manager);
	}
	
	@Override
	public void addManager(EntityManager manager) {
		this.manager = manager;
	}
	
	public void create() {
		for(int i = 0; i<=5; i++) {
			UUID hex = manager.createEntity();
			manager.addComponents(hex, new BoardHex(i,0));
			manager.addComponents(hex, new Position(20+i*63,400));
			manager.addComponents(hex, new Size(20+i*63,400,63,72));
			manager.addComponents(hex, new Renderable("hex.png"));
			manager.addComponents(hex, new Clickable());
		}
	}

	@Override
	public void update(float delta, SpriteBatch batch) {
		List<UUID> clickables = manager.getAllEntitiesWithComponent(Clickable.class);
		
		for(UUID uid : clickables) {
			Clickable click = manager.getComponent(uid, Clickable.class);
			if(click.isClicked()) {
				Renderable sprite = manager.getComponent(uid, Renderable.class);
				if(sprite.getSprite().equals("hex.png")) {
					sprite.setSprite("hexhover.png");
				} else {
					sprite.setSprite("hex.png");
				}
				click.setClicked(false);
			}
		}
	}

}
