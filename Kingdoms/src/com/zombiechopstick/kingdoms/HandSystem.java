package com.zombiechopstick.kingdoms;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.zombiechopstick.kingdoms.components.Card;
import com.zombiechopstick.kingdoms.components.Dragable;
import com.zombiechopstick.kingdoms.components.Owner;
import com.zombiechopstick.kingdoms.components.Position;
import com.zombiechopstick.kingdoms.components.Size;
import com.zombiechopstick.kingdoms.components.SnapBackPosition;

public class HandSystem implements ComponentSystem {

	private static final long serialVersionUID = -3177878730569600408L;
	private EntityManager manager;
	private List<UUID> cardsInHand = new ArrayList<UUID>();
	private String handOwner;

	public HandSystem(EntityManager manager, String handOwner) {
		this.manager = manager;
		this.handOwner = handOwner;
	}
	
	@Override
	public void update(float delta, SpriteBatch batch) {
		List<UUID> cards = manager.getAllEntitiesWithComponent(Card.class);
		
		for(UUID uid : cards) {
			Card card = manager.getComponent(uid, Card.class);
			Owner player = manager.getComponent(uid, Owner.class);
			
			if(card.getCurrentState() == Card.PlayState.INHAND && !cardsInHand.contains(uid) && player.getPlayerName().equals(handOwner)) {
				manager.addComponents(uid, new Position(5 + cardsInHand.size()*180, 0));
				manager.addComponents(uid, new Size(5 + cardsInHand.size()*180,0,175,230));
				cardsInHand.add(uid);
			}
		}
		
		for(UUID uid : cardsInHand) {
			Dragable drag = manager.getComponent(uid, Dragable.class);
			Size size = manager.getComponent(uid, Size.class);
			Position pos = manager.getComponent(uid, Position.class);
			
			if(manager.getComponent(uid, SnapBackPosition.class) == null) {
				manager.addComponents(uid, new SnapBackPosition(pos.getX(),pos.getY()));
			}
			
			Rectangle bbox = size.getBbox();
			
			if(drag.isDragged()) {
				pos.setX(Gdx.input.getX()-size.getWidth()/2);
				pos.setY(Gdx.graphics.getHeight()-Gdx.input.getY()-size.getHeight()/2);
				bbox.setX(pos.getX());
				bbox.setY(pos.getY());
				break;
			}
			else {
				SnapBackPosition snap = manager.getComponent(uid, SnapBackPosition.class);
				pos.setX(snap.getX());
				pos.setY(snap.getY());
				bbox.setX(pos.getX());
				bbox.setY(pos.getY());
			}
		}
		
	}
	
	@Override
	public void addManager(EntityManager manager) {
		this.manager = manager;
	}
	
}
