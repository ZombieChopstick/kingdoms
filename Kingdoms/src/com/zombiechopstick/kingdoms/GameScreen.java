package com.zombiechopstick.kingdoms;

import java.util.UUID;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.zombiechopstick.kingdoms.components.Card;
import com.zombiechopstick.kingdoms.components.Clickable;
import com.zombiechopstick.kingdoms.components.Dragable;
import com.zombiechopstick.kingdoms.components.Name;
import com.zombiechopstick.kingdoms.components.Owner;
import com.zombiechopstick.kingdoms.components.Position;
import com.zombiechopstick.kingdoms.components.Renderable;
import com.zombiechopstick.kingdoms.components.Size;
import com.zombiechopstick.kingdoms.components.Stat;
import com.zombiechopstick.kingdoms.components.StatModifier;

public class GameScreen implements Screen {
	private SpriteBatch batch;
	private EntityManager manager = new EntityManager();
	private RenderSystem render = new RenderSystem(manager);
	private InputSystem input = new InputSystem(manager);
	private DeckSystem deck;
	private HandSystem hand;
	
	@Override
	public void render(float delta) {		
		render.update(delta, batch);
		input.update(delta, batch);
		deck.update(delta, batch);		
		hand.update(delta, batch);
	}

	@Override
	public void resize(int width, int height) {
				
	}

	@Override
	public void show() {
		batch = new SpriteBatch();

		UUID potionCard = manager.createEntity();
		//manager.addEntityToScreen(potionCard);
		manager.addComponents(potionCard, 
				new Name("Potion"), 
				new StatModifier("Health", 20),
				new Renderable("potioncard.png"), 
				new Card(false,Card.PlayState.INDECK), 
				new Dragable(),
				new Owner("Player 1"));
		
		UUID attackBoostCard = manager.createEntity();
		//manager.addEntityToScreen(attackBoostCard);
		manager.addComponents(attackBoostCard, 
				new Name("Attack Boost"),
				new StatModifier("Attack", 10),
				new Renderable("attackboostcard.png"),
				new Card(false, Card.PlayState.INDECK),
				new Dragable(),
				new Owner("Player 1"));
		
		UUID leader1 = manager.createEntity();
		//manager.addEntityToScreen(leader1);
		manager.addComponents(leader1, 
				new Name("Dwarf Leader"), 
				new Stat("Health", 50), 
				new Stat("Attack", 60), 
				new Renderable("dwarfleader.png"),
				new Position(10,Gdx.graphics.getHeight()-240),
				new Size(10,Gdx.graphics.getHeight()-240,175,230),
				new Owner("Player 1"));
		
		UUID leader2 = manager.createEntity();
		//manager.addEntityToScreen(leader2);
		manager.addComponents(leader2, 
				new Name("Centaur Leader"), 
				new Stat("Health", 100), 
				new Stat("Attack", 60), 
				new Renderable("centaurleader.png"),
				new Position(Gdx.graphics.getWidth()-185,Gdx.graphics.getHeight()-240),
				new Size(Gdx.graphics.getWidth()-185,Gdx.graphics.getHeight()-240,175,230),
				new Owner("Player 2"));
		
		UUID deck = manager.createEntity();
		//manager.addEntityToScreen(deck);
		manager.addComponents(deck, 
				new Renderable("deck.png"),
				new Position(Gdx.graphics.getWidth()-185,0),
				new Size(Gdx.graphics.getWidth()-185,0,175,230),
				new Clickable(),
				new Owner("Player 1"));
		
		this.deck = new DeckSystem(manager, "Player 1");
		this.hand = new HandSystem(manager, "Player 1");
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		//batch.dispose();
		//texture.dispose();
	}

}
