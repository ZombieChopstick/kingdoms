package com.zombiechopstick.kingdoms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zombiechopstick.kingdoms.components.*;
import com.zombiechopstick.kingdoms.components.Card.PlayState;

public class DeckSystem implements ComponentSystem {

	private static final long serialVersionUID = -4937125231741557943L;
	private EntityManager manager;
	private List<UUID> cardsInDeck = new ArrayList<UUID>();
	
	public DeckSystem(EntityManager manager, String deckOwner) {
		this.manager = manager;
		List<UUID> cards = manager.getAllEntitiesWithComponent(Card.class);
		for(UUID uid : cards) {
			Owner player = manager.getComponent(uid, Owner.class);
			System.out.println(player.getPlayerName());
			if(player.getPlayerName().equals(deckOwner)) {
				cardsInDeck.add(uid);
			}
		}
		Collections.shuffle(cardsInDeck);
		System.out.println(cardsInDeck.size());
	}
	
	@Override
	public void update(float delta, SpriteBatch batch) {
		List<UUID> clickables = manager.getAllEntitiesWithComponent(Clickable.class);
		
		for(UUID uid : clickables) {
			Clickable click = manager.getComponent(uid, Clickable.class);
			if(click.isClicked() && cardsInDeck.size() > 0) {
				System.out.println("dealing cards");
				//Change state of cards from INDECK to INHAND
				UUID card = cardsInDeck.get(0);
				Card c = manager.getComponent(card, Card.class);
				c.setCurrentState(PlayState.INHAND);
				c.setFaceUp(true);
				Name name = manager.getComponent(card, Name.class);
				System.out.println("Drawing " + name.getName() + " to hand.");
				cardsInDeck.remove(0);
				click.setClicked(false);
			}
		}
	}

	@Override
	public void addManager(EntityManager manager) {
		this.manager = manager;
	}
}
