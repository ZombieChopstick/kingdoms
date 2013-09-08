package com.zombiechopstick.kingdoms;

//import java.util.UUID;

//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.zombiechopstick.kingdoms.components.*;

public class ECSTester {

	public static void main(String[] args) {
		ECSTester test = new ECSTester();
		test.show();
	}

	//EntityManager manager = new EntityManager();
	//RenderSystem render = new RenderSystem(manager);

	public void show() {
		/*UUID potionCard = manager.createEntity();
		manager.addComponents(potionCard, 
				new Name("Potion"), 
				new StatModifier("Health", 20),
				new Renderable("potioncard.png"), 
				new Card(true,Card.PlayState.INDECK), 
				new Dragable(),
				new Owner("Player 1"));
		
		UUID attackBoostCard = manager.createEntity();
		manager.addComponents(attackBoostCard, 
				new Name("Attack Boost"),
				new StatModifier("Attack", 10),
				new Renderable("attackboostcard.png"), 
				new Card(true, Card.PlayState.INDECK),
				new Dragable(),
				new Owner("Player 1"));
		
		UUID leader1 = manager.createEntity();
		manager.addComponents(leader1, 
				new Name("Dwarf Leader"), 
				new Stat("Health", 50), 
				new Stat("Attack", 60), 
				new Renderable("dwarfleader.png"),
				new Owner("Player 1"));
		
		UUID leader2 = manager.createEntity();
		manager.addComponents(leader2, 
				new Name("Centaur Leader"), 
				new Stat("Health", 100), 
				new Stat("Attack", 60), 
				new Renderable("centaurleader.png"),
				new Owner("Player 2"));
		
		UUID deck = manager.createEntity();
		manager.addComponents(deck, 
				new Renderable("deck.png"), 
				new Clickable(),
				new Owner("Player 1"));
		
		render(60);*/
	}

	public void render(int delta) {
		//render.update(delta, new SpriteBatch());
	}
}
